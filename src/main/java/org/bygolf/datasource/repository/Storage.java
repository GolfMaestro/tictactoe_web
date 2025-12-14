package org.bygolf.datasource.repository;

import org.bygolf.datasource.model.DSCurrentGame;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class Storage {

    private CopyOnWriteArrayList<DSCurrentGame> games = new CopyOnWriteArrayList<>();

    public Storage() {
    }

    public void saveGame(DSCurrentGame dsCurrentGame) {
        games.add(dsCurrentGame);
    }

    public DSCurrentGame loadGame(UUID id) {

        for (DSCurrentGame game : games) {
            if (game.getId().equals(id)) {
                return game;
            }
        }

        return null;

    }

}
