package org.bygolf.datasource.mapper;

import org.bygolf.datasource.model.DSCurrentGame;
import org.bygolf.datasource.model.DSGameField;
import org.bygolf.domain.model.CurrentGame;
import org.bygolf.domain.model.GameField;

public interface DSToDomain {

    CurrentGame dsToCurrentGame(DSCurrentGame dsCurrentGame);
    GameField dsToGameField(DSGameField dsGameField);

}
