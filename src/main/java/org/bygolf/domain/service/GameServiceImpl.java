package org.bygolf.domain.service;

import org.bygolf.domain.ai.BotAi;
import org.bygolf.domain.model.CurrentGame;
import org.bygolf.domain.model.GameField;

public class GameServiceImpl implements GameService {

    private final BotAi botAi;

    public GameServiceImpl(BotAi botAi) {
        this.botAi = botAi;
    }

    @Override
    public int[][] nextTurn(CurrentGame currentGame) {

        int[][] field = currentGame.getGameField().getField();

        int[] next = botAi.selectBestMove(field);

        field[next[0]][next[1]] = 2;

        return field;

    }

    @Override
    public boolean isCorrectField(GameField gameField) {
        return false;
    }

    @Override
    public boolean isGameEnds(GameField gameField) {
        if (botAi.whoWin(gameField.getField()) == null) {
            return false;
        }
        else {
            return true;
        }
    }



}
