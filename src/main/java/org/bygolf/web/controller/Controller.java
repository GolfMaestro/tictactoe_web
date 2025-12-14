package org.bygolf.web.controller;

import org.bygolf.datasource.mapper.DSToDomain;
import org.bygolf.datasource.mapper.DomainToDS;
import org.bygolf.datasource.repository.TicTacToeRepository;
import org.bygolf.domain.model.CurrentGame;
import org.bygolf.domain.model.GameField;
import org.bygolf.domain.service.GameService;
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
        ticTacToeRepository.saveGame(domainToDS.currentGameToDS(tempGame));
        return tempGame;
    }

    @GetMapping("game/{gameId}")
    public CurrentGame getGame(@PathVariable UUID gameId) {
        return dsToDomain.dsToCurrentGame(ticTacToeRepository.loadGame(gameId));
    }

    @PostMapping("game/{gameId}")
    public CurrentGame turn(@PathVariable UUID gameId, @RequestBody CurrentGame currentGame) {

        currentGame.setGameField(new GameField(gameService.nextTurn(currentGame)));

        ticTacToeRepository.saveGame(domainToDS.currentGameToDS(currentGame));

        return currentGame;
    }

}
