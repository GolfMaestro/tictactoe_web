package org.bygolf.datasource.mapper;

import org.bygolf.datasource.model.DSCurrentGame;
import org.bygolf.datasource.model.DSGameField;
import org.bygolf.domain.model.CurrentGame;
import org.bygolf.domain.model.GameField;

public class DSToDomainImpl implements DSToDomain {

    @Override
    public CurrentGame dsToCurrentGame(DSCurrentGame dsCurrentGame) {
        return new CurrentGame(dsCurrentGame.getId(),
                                new GameField(dsCurrentGame.getDsGameField().getField()));
    }

    @Override
    public GameField dsToGameField(DSGameField dsGameField) {
        return new GameField(dsGameField.getField());
    }

}
