package org.bygolf.datasource.mapper;

import org.bygolf.datasource.model.DSCurrentGame;
import org.bygolf.datasource.model.DSGameField;
import org.bygolf.datasource.model.DSUser;
import org.bygolf.domain.model.CurrentGame;
import org.bygolf.domain.model.GameField;
import org.bygolf.domain.model.User;

public class DSToDomainImpl implements DSToDomain {

    @Override
    public CurrentGame dsToCurrentGame(DSCurrentGame dsCurrentGame) {
        return new CurrentGame(dsCurrentGame.getId(),
                new GameField(dsCurrentGame.getDSgameField().getId(), dsCurrentGame.getDSgameField().getFieldText()),
                dsCurrentGame.getGameStatus(),
                dsCurrentGame.getGameType(),
                dsToUser(dsCurrentGame.getHost()),
                dsToUser(dsCurrentGame.getMember()));
    }

    @Override
    public GameField dsToGameField(DSGameField dsGameField) {
        return new GameField(dsGameField.getFieldText());
    }

    @Override
    public User dsToUser(DSUser dsUser) {
        if (dsUser == null) {
            return null;
        }
        else {
            return new User(dsUser.getId(), dsUser.getLogin(), dsUser.getPassword());
        }
    }

}
