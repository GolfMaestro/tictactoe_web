package org.bygolf.domain.ai;

public interface BotAi {

    Integer whoWin(int[][] field);
    int minimax(int[][] field, boolean isMaximizing);
    int[] selectBestMove(int[][] field);

}
