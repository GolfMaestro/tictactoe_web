package org.bygolf.web.mapper;

import org.bygolf.domain.model.CurrentGame;
import org.bygolf.domain.model.GameField;
import org.bygolf.web.model.WebCurrentGame;
import org.bygolf.web.model.WebGameField;

public class WebToDomainImpl implements WebToDomain {

    @Override
    public CurrentGame webToCurrentGame(WebCurrentGame webCurrentGame) {
        return new CurrentGame(webCurrentGame.getId(),
                                new GameField(webCurrentGame.getWebGameField().getField()));
    }

    @Override
    public GameField webToGameField(WebGameField webGameField) {
        return new GameField(webGameField.getField());
    }
}
