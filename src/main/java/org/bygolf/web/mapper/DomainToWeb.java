package org.bygolf.web.mapper;

import org.bygolf.domain.model.CurrentGame;
import org.bygolf.domain.model.GameField;
import org.bygolf.web.model.WebCurrentGame;
import org.bygolf.web.model.WebGameField;

public interface DomainToWeb {

    WebCurrentGame domainToCurrentGame(CurrentGame currentGame);
    WebGameField domainToGameField(GameField gameField);

}
