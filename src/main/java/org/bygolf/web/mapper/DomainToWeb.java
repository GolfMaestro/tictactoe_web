package org.bygolf.web.mapper;

import org.bygolf.domain.model.CurrentGame;
import org.bygolf.domain.model.GameField;
import org.bygolf.domain.model.User;
import org.bygolf.web.model.WebCurrentGame;
import org.bygolf.web.model.WebGameField;
import org.bygolf.web.model.WebUser;

public interface DomainToWeb {

    WebCurrentGame domainToCurrentGame(CurrentGame currentGame);
    WebGameField domainToGameField(GameField gameField);
    WebUser domainToUser(User user);

}
