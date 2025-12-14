package org.bygolf.datasource.mapper;

import org.bygolf.datasource.model.DSCurrentGame;
import org.bygolf.datasource.model.DSGameField;
import org.bygolf.domain.model.CurrentGame;
import org.bygolf.domain.model.GameField;

public class DomainToDSImpl implements DomainToDS {

    @Override
    public DSCurrentGame currentGameToDS(CurrentGame currentGame) {
        return new DSCurrentGame(currentGame.getId(),
                                new DSGameField(currentGame.getGameField().getField()));
    }

    @Override
    public DSGameField gameFieldToDS(GameField gameField) {
        return new DSGameField(gameField.getField());
    }

}
