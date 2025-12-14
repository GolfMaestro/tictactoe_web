package org.bygolf.web.model;

import org.bygolf.domain.model.GameField;

import java.util.UUID;

public class WebCurrentGame {

    private UUID id;
    private GameField gameField;

    public WebCurrentGame(UUID id, GameField gameField) {
        this.id = id;
        this.gameField = gameField;
    }

    public UUID getId() {
        return id;
    }

    public GameField getGameField() {
        return gameField;
    }

}
