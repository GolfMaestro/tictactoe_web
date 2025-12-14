package org.bygolf.domain.model;

public class GameField {

    int[][] field;

    public GameField() {
        this.field = new int[3][3];
    }

    public GameField(int[][] field) {
        this.field = field;
    }

    public int[][] getField() {
        return field;
    }

}
