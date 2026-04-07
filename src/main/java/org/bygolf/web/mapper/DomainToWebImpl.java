package org.bygolf.web.mapper;

import org.bygolf.domain.model.CurrentGame;
import org.bygolf.domain.model.GameField;
import org.bygolf.domain.model.User;
import org.bygolf.web.model.WebCurrentGame;
import org.bygolf.web.model.WebGameField;
import org.bygolf.web.model.WebUser;

public class DomainToWebImpl implements DomainToWeb {

    @Override
    public WebCurrentGame domainToCurrentGame(CurrentGame currentGame) {

        if (currentGame.getMember() == null) {
            return new WebCurrentGame(currentGame.getId(),
                    currentGame.getGameField().getField(),
                    currentGame.getHost().getId(),
                    null, currentGame.getGameStatus());
        }

        return new WebCurrentGame(currentGame.getId(),
                currentGame.getGameField().getField(),
                currentGame.getHost().getId(),
                currentGame.getMember().getId(), currentGame.getGameStatus());
    }

    @Override
    public WebGameField domainToGameField(GameField gameField) {
        return new WebGameField(gameField.getField());
    }

    @Override
    public WebUser domainToUser(User user) {
        if (user == null) {
            return null;
        }
        else {
            return new WebUser(user.getId());
        }
    }
}
