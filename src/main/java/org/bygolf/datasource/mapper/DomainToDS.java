package org.bygolf.datasource.mapper;

import org.bygolf.datasource.model.DSCurrentGame;
import org.bygolf.datasource.model.DSGameField;
import org.bygolf.domain.model.CurrentGame;
import org.bygolf.domain.model.GameField;

public interface DomainToDS {

    DSCurrentGame currentGameToDS(CurrentGame currentGame);
    DSGameField gameFieldToDS(GameField gameField);

}
