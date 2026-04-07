package org.bygolf.domain.model;

import java.util.Arrays;

public class GameField {

    private Long id;

    private String fieldText;

    int[][] field;

    public void setFieldText() {
        this.fieldText = Arrays.deepToString(field);
    }

    public GameField() {
        this.field = new int[3][3];
    }

    public GameField(int[][] field) {
        this.field = field;
    }

    public GameField(String fieldText) {
        this.fieldText = fieldText;
    }

    public GameField(Long id, String fieldText) {
        this.id = id;
        this.fieldText = fieldText;
    }

    public int[][] getField() {
        return field;
    }

    public String getFieldText() {
        return fieldText;
    }

    public void convertTextToArray() {
        
        char[] chars = fieldText.toCharArray();

        int curMatrixPos = 0;

        field = new int[3][3];

        for (char c : chars) {
            if (c != ',' && c != '[' && c != ']' && c != ' ') {
                int tempInt = Character.getNumericValue(c);
                field[curMatrixPos / 3][curMatrixPos % 3] = tempInt;
                curMatrixPos++;;
            }

        }

    }

    public void setField(int[][] field) {
        this.field = field;
    }

    public Long getId() {
        return id;
    }

}
