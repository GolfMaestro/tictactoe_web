package org.bygolf.domain.service;

import org.bygolf.datasource.mapper.DSToDomain;
import org.bygolf.datasource.repository.TicTacToeRepository;
import org.bygolf.domain.ai.BotAi;
import org.bygolf.domain.model.CurrentGame;
import org.bygolf.domain.model.GameField;

import java.util.UUID;

public class GameServiceImpl implements GameService {

    private final BotAi botAi;
    private final TicTacToeRepository ticTacToeRepository;
    private final DSToDomain dsToDomain;

    public GameServiceImpl(BotAi botAi, TicTacToeRepository ticTacToeRepository, DSToDomain dsToDomain) {
        this.botAi = botAi;
        this.ticTacToeRepository = ticTacToeRepository;
        this.dsToDomain = dsToDomain;
    }

    @Override
    public int[][] nextTurn(CurrentGame currentGame) {

        if (!checkIsNull(currentGame)) {

            int[][] field = currentGame.getGameField().getField();
            int[] next = botAi.selectBestMove(field);
            field[next[0]][next[1]] = 2;
            return field;

        }
        else {
            throw new IllegalArgumentException("Expecting not null");
        }

    }

    @Override
    public boolean isCorrectGame(CurrentGame currentGame) {

        if (!checkIsNull(currentGame)) {
            UUID tempId = currentGame.getId();

            CurrentGame tempGame = dsToDomain.dsToCurrentGame(ticTacToeRepository.loadGame(tempId));

            int difAmount = 0;

            int sizeN = tempGame.getGameField().getField().length;

            int[][] sourceField = tempGame.getGameField().getField();
            int[][] newField = currentGame.getGameField().getField();

            for (int i = 0; i < sizeN; i++) {
                for (int j = 0; j < sizeN; j++) {
                    if (sourceField[i][j] != newField[i][j] && sourceField[i][j] == 0) {
                        difAmount++;
                    }
                    else if (sourceField[i][j] != newField[i][j]) {
                        difAmount = 9;
                        break;
                    }
                    if (newField[i][j] == 0 || newField[i][j] == 1 || newField[i][j] == 2) {
                        ;
                    }
                    else {
                        difAmount = 9;
                        break;
                    }
                }
            }

            if (difAmount > 1) {
                return false;
            }
            else {
                return true;
            }
        }
        else {
            throw new IllegalArgumentException("Expecting not null");
        }

    }

    @Override
    public Integer isGameEnds(GameField gameField) {

        if (gameField != null) {
            return botAi.whoWin(gameField.getField());
        }
        else {
            throw new IllegalArgumentException("Expecting not null");
        }

    }

    public boolean checkIsNull(CurrentGame currentGame) {
        if (currentGame == null) {
            return true;
        }
        if (currentGame.getGameField() == null) {
            return true;
        }
        if (currentGame.getId() == null) {
            return true;
        }
        return false;
    }


}
