package org.bygolf.domain.model;

import javax.annotation.processing.Generated;
import java.util.UUID;

public class CurrentGame {

    private UUID id;
    private GameField gameField;

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
}
