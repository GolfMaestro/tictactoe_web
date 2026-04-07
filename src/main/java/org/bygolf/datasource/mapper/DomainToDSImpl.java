package org.bygolf.datasource.mapper;

import org.bygolf.datasource.model.DSCurrentGame;
import org.bygolf.datasource.model.DSGameField;
import org.bygolf.datasource.model.DSUser;
import org.bygolf.domain.model.CurrentGame;
import org.bygolf.domain.model.GameField;
import org.bygolf.domain.model.User;

public class DomainToDSImpl implements DomainToDS {

    @Override
    public DSCurrentGame currentGameToDS(CurrentGame currentGame) {

        return new DSCurrentGame(currentGame.getId(),
                new DSGameField(currentGame.getGameField().getId(), currentGame.getGameField().getFieldText()),
                currentGame.getGameStatus(),
                currentGame.getGameType(),
                userToDS(currentGame.getHost()),
                userToDS(currentGame.getMember()));

    }

    @Override
    public DSGameField gameFieldToDS(GameField gameField) {
        return new DSGameField(gameField.getFieldText());
    }

    @Override
    public DSUser userToDS(User user) {
        if (user == null) {
            return null;
        }
        else {
            return new DSUser(user.getId(), user.getLogin(), user.getPassword());
        }
    }
}
