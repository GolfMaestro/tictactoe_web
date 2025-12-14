package org.bygolf.datasource.repository;

import org.bygolf.datasource.model.DSCurrentGame;

import java.util.UUID;

public interface TicTacToeRepository {

    void saveGame(DSCurrentGame dsCurrentGame);
    public DSCurrentGame loadGame(UUID id);

}
