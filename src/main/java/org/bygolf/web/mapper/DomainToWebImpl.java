package org.bygolf.web.mapper;

import org.bygolf.domain.model.CurrentGame;
import org.bygolf.domain.model.GameField;
import org.bygolf.web.model.WebCurrentGame;
import org.bygolf.web.model.WebGameField;

public class DomainToWebImpl implements DomainToWeb {

    @Override
    public WebCurrentGame domainToCurrentGame(CurrentGame currentGame) {
        return new WebCurrentGame(currentGame.getId(),
                                new WebGameField(currentGame.getGameField().getField()));
    }

    @Override
    public WebGameField domainToGameField(GameField gameField) {
        return new WebGameField(gameField.getField());
    }
}
