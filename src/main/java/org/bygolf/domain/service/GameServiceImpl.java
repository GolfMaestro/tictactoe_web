package org.bygolf.domain.service;

import org.bygolf.domain.model.CurrentGame;
import org.bygolf.domain.model.GameField;

public class GameServiceImpl implements GameService {

    @Override
    public int[][] nextTurn(CurrentGame currentGame) {

        int[][] field = currentGame.getGameField().getField();

        int[] next = selectBestMove(field);

        field[next[0]][next[1]] = 2;

        return field;
        
    }

    @Override
    public boolean isCorrectField(GameField gameField) {
        return false;
    }

    @Override
    public boolean isGameEnds(GameField gameField) {
        return false;
    }

    // 0 - tie
    // 1 - player
    // 2 - AI
    // null - if not end

    public Integer whoWin(int[][] field) {

        int[][] winnable = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6}
        };

        for (int[] line : winnable) {
            if (field[line[0] / 3][line[0] % 3] == 2
                    && field[line[1] / 3][line[1] % 3] == 2
                    && field[line[2] / 3][line[2] % 3] == 2) {
                return 2;
            }
            if (field[line[0] / 3][line[0] % 3] == 1
                    && field[line[1] / 3][line[1] % 3] == 1
                    && field[line[2] / 3][line[2] % 3] == 1) {
                return 1;
            }
        }

        boolean isEnd = true;

        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[row].length; col++) {
                if (field[row][col] == 0) {
                    isEnd = false;
                    break;
                }
            }
        }

        if (isEnd) {
            return 0;
        }

        return null;

    }

    public int minimax(int[][] field, boolean isMaximizing) {

        Integer winner = whoWin(field);

        if (winner != null) {
            if (winner == 1) {
                return -10;
            }
            else if (winner == 2) {
                return 10;
            }
            else if (winner == 0) {
                return 0;
            }
        }

        if (isMaximizing) {
            int best = Integer.MIN_VALUE;
            for (int row = 0; row < field.length; row++) {
                for (int col = 0; col < field[row].length; col++) {
                    if (field[row][col] == 0) {
                        field[row][col] = 2;
                        int score = minimax(field, false);
                        field[row][col] = 0;
                        best = Math.max(best, score);
                    }
                }
            }

            return best;
        }
        else {
            int best = Integer.MAX_VALUE;
            for (int row = 0; row < field.length; row++) {
                for (int col = 0; col < field[row].length; col++) {
                    if (field[row][col] == 0) {
                        field[row][col] = 1;
                        int score = minimax(field, true);
                        field[row][col] = 0;
                        best = Math.min(best, score);
                    }
                }
            }

            return best;

        }

    }

    // 0 - empty
    // 1 - player
    // 2 - AI
    public int[] selectBestMove(int[][] field) {

        int[][] tempField = copyField(field);

        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};

        for (int row = 0; row < tempField.length; row++) {
            for (int col = 0; col < tempField[row].length; col++) {
                if (tempField[row][col] == 0) {
                    tempField[row][col] = 2;
                    int score = minimax(tempField, false);
                    tempField[row][col] = 0;
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = row;
                        bestMove[1] = col;
                    }
                }
            }
        }

        return bestMove;

    }

    private int[][] copyField(int[][] src) {
        int n = src.length;
        int[][] copyField = new int[n][n];
        for (int i = 0; i < n; i++) {
            copyField[i] = java.util.Arrays.copyOf(src[i], n);
        }
        return copyField;
    }

}
