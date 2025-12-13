package org.bygolf.domain.model;

import javax.annotation.processing.Generated;
import java.util.UUID;

public class CurrentGame {

    private UUID id;
    private GameField field;

    public CurrentGame(UUID id, GameField field) {
        this.id = id;
        this.field = field;
    }

    public UUID getId() {
        return id;
    }

    public GameField getField() {
        return field;
    }
}
