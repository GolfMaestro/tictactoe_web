package org.bygolf.domain.service;

import org.bygolf.domain.model.CurrentGame;
import org.bygolf.domain.model.GameField;

public class GameServiceImpl implements GameService {

    @Override
    public int[][] nextTurn(CurrentGame currentGame) {
        return new int[0][];
    }

    @Override
    public boolean isCorrectField(GameField gameField) {
        return false;
    }

    @Override
    public boolean isGameEnds(GameField gameField) {
        return false;
    }

    private static void minimax(GameField gameField) {

    }

}
