package org.bygolf.web.controller;

import org.bygolf.domain.model.GameType;
import org.bygolf.domain.service.GameService;
import org.bygolf.web.dto.CurrentGamesDto;
import org.bygolf.web.dto.GetGameDto;
import org.bygolf.web.model.WebCurrentGame;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
public class Controller {

    private final GameService gameService;

    public Controller(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/main")
    public String mainPage() {
        return "Tic tac toe app";
    }

    @GetMapping("game/ai")
    public ResponseEntity<?> createNewAIGame(Principal principal) {

        WebCurrentGame webCurrentGame = gameService.createNewGame(principal, GameType.AI);

        return ResponseEntity.ok().body(webCurrentGame);

    }

    @GetMapping("game/player")
    public ResponseEntity<?> createNewPlayerGame(Principal principal) {

        WebCurrentGame webCurrentGame = gameService.createNewGame(principal, GameType.PLAYER);

        return ResponseEntity.ok().body(webCurrentGame);

    }

    @GetMapping("game/{gameId}")
    public ResponseEntity<?> getGame(@PathVariable UUID gameId, Principal principal) {

        GetGameDto getGameDto = gameService.getGameDtoById(principal, gameId);

        return ResponseEntity.ok(getGameDto);

    }

    @PostMapping("game/{gameId}")
    public ResponseEntity<?> turn(@PathVariable UUID gameId, @RequestBody WebCurrentGame webCurrentGameInput, Principal principal) {

        WebCurrentGame webCurrentGame = gameService.turn(gameId, webCurrentGameInput, principal);

        return ResponseEntity.ok().body(webCurrentGame);

    }

    @GetMapping("games")
    public ResponseEntity<?> getAllOpenGames() {

        List<CurrentGamesDto> games = gameService.getOpenGames();

        return ResponseEntity.ok(games);

    }

    @PostMapping("join/{gameId}")
    public ResponseEntity<?> joinOpenGame(@PathVariable UUID gameId, Principal principal) {

        WebCurrentGame webCurrentGame = gameService.joinGame(principal, gameId);

        return ResponseEntity.ok().body(webCurrentGame);

    }

    @GetMapping("user/{userId}")
    public ResponseEntity<?> getUserInfo(@PathVariable UUID userId) {

        int gamesCount = gameService.getUserInfo(userId);

        return ResponseEntity.ok().body("userId: " + userId + "\nGames count: " + gamesCount);

    }

}