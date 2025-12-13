package org.bygolf.domain.service;

import org.bygolf.domain.model.CurrentGame;
import org.bygolf.domain.model.GameField;

public interface GameService {

    int[][] nextTurn(CurrentGame currentGame);
    boolean isCorrectField(GameField gameField);
    boolean isGameEnds(GameField gameField);

}
