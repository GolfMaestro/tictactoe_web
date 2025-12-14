package org.bygolf.datasource.repository;

import org.bygolf.datasource.model.DSCurrentGame;
import org.bygolf.datasource.model.DSGameField;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class Storage {

    private CopyOnWriteArrayList<DSCurrentGame> games;

    public Storage() {
        this.games = new CopyOnWriteArrayList<>();
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

    public boolean contain(UUID id) {
        for (DSCurrentGame game : games) {
            if (game.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void updateById(UUID id, int[][] field) {
        for (DSCurrentGame game : games) {
            if (game.getId().equals(id)) {
                game.setDsGameField(new DSGameField(field));
                break;
            }
        }
    }

}
