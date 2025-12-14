package org.bygolf.web.controller;

import org.bygolf.domain.model.CurrentGame;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/main")
    public String mainPage() {
        return "Tic tac toe app";
    }

    @GetMapping("game")
    public CurrentGame createNewGame() {
        return new CurrentGame();
    }

}
