package org.bygolf.datasource.model;

import org.bygolf.domain.model.GameField;

import java.util.UUID;

public class DSCurrentGame {

    private UUID id;
    private DSGameField dsGameField;

    public DSCurrentGame(UUID id, DSGameField dsGameField) {
        this.id = id;
        this.dsGameField = dsGameField;
    }

    public UUID getId() {
        return id;
    }

    public DSGameField getDsGameField() {
        return dsGameField;
    }

    public void setDsGameField(DSGameField dsGameField) {
        this.dsGameField = dsGameField;
    }
}
