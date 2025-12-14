package org.bygolf.web.model;

import org.bygolf.domain.model.GameField;

import java.util.UUID;

public class WebCurrentGame {

    private UUID id;
    private WebGameField webGameField;

    public WebCurrentGame(UUID id, WebGameField webGameField) {
        this.id = id;
        this.webGameField = webGameField;
    }

    public UUID getId() {
        return id;
    }

    public WebGameField getWebGameField() {
        return webGameField;
    }
}
