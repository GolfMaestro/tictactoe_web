package org.bygolf.datasource.repository;

import org.bygolf.datasource.model.DSCurrentGame;

import java.util.UUID;

public class TicTacToeRepositoryImpl implements TicTacToeRepository {

    private final Storage storage;

    public TicTacToeRepositoryImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void saveGame(DSCurrentGame dsCurrentGame) {
        if (!storage.contain(dsCurrentGame.getId())) {
            storage.saveGame(dsCurrentGame);
        }
        else {
            storage.updateById(dsCurrentGame.getId(), dsCurrentGame.getDsGameField().getField());
        }
    }

    @Override
    public DSCurrentGame loadGame(UUID id) {

        return storage.loadGame(id);
        
    }
}
