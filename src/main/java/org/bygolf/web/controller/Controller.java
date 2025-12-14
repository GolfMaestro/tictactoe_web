package org.bygolf.web.controller;

import org.bygolf.datasource.mapper.DSToDomain;
import org.bygolf.datasource.mapper.DomainToDS;
import org.bygolf.datasource.model.DSCurrentGame;
import org.bygolf.datasource.repository.TicTacToeRepository;
import org.bygolf.domain.model.CurrentGame;
import org.bygolf.domain.model.GameField;
import org.bygolf.domain.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class Controller {

    private final GameService gameService;
    private final TicTacToeRepository ticTacToeRepository;
    private final DomainToDS domainToDS;
    private final DSToDomain dsToDomain;

    public Controller(GameService gameService,
                      TicTacToeRepository ticTacToeRepository,
                      DomainToDS domainToDS,
                      DSToDomain dsToDomain) {
        this.gameService = gameService;
        this.ticTacToeRepository = ticTacToeRepository;
        this.domainToDS = domainToDS;
        this.dsToDomain = dsToDomain;
    }

    @GetMapping("/main")
    public String mainPage() {
        return "Tic tac toe app";
    }

    @GetMapping("game")
    public CurrentGame createNewGame() {
        CurrentGame tempGame = new CurrentGame();
        tempGame.setGameField(new GameField(gameService.nextTurn(tempGame)));
        ticTacToeRepository.saveGame(domainToDS.currentGameToDS(tempGame));
        return tempGame;
    }

    @GetMapping("game/{gameId}")
    public ResponseEntity<?> getGame(@PathVariable UUID gameId) {

        DSCurrentGame tempGame = ticTacToeRepository.loadGame(gameId);

        if (tempGame != null) {
            return ResponseEntity.ok().body(dsToDomain.dsToCurrentGame(tempGame));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found");
        }

    }

    @PostMapping("game/{gameId}")
    public ResponseEntity<?> turn(@PathVariable UUID gameId, @RequestBody CurrentGame currentGame) {

        DSCurrentGame tempGame = ticTacToeRepository.loadGame(gameId);

        if (tempGame != null) {
            if (gameService.isCorrectGame(currentGame)) {
                currentGame.setGameField(new GameField(gameService.nextTurn(currentGame)));
                ticTacToeRepository.saveGame(domainToDS.currentGameToDS(currentGame));
                return ResponseEntity.ok().body(currentGame);
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect move");
            }

        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found");
        }

    }

}