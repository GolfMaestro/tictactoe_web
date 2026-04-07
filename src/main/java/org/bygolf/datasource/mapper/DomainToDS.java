package org.bygolf.datasource.mapper;

import org.bygolf.datasource.model.DSCurrentGame;
import org.bygolf.datasource.model.DSGameField;
import org.bygolf.datasource.model.DSUser;
import org.bygolf.domain.model.CurrentGame;
import org.bygolf.domain.model.GameField;
import org.bygolf.domain.model.User;

public interface DomainToDS {

    DSCurrentGame currentGameToDS(CurrentGame currentGame);
    DSGameField gameFieldToDS(GameField gameField);
    DSUser userToDS(User user);

}
