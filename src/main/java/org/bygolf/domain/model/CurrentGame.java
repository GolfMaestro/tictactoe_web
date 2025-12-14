package org.bygolf.domain.model;

import java.util.UUID;

public class CurrentGame {

    private UUID id;
    private GameField gameField;

    public CurrentGame() {
        this.id = UUID.randomUUID();
        this.gameField = new GameField();
    }

    public CurrentGame(UUID id, GameField gameField) {
        this.id = id;
        this.gameField = gameField;
    }

    public UUID getId() {
        return id;
    }

    public GameField getGameField() {
        return gameField;
    }

    public void setGameField(GameField gameField) {
        this.gameField = gameField;
    }
}
