package org.sovas.service;

public class MineSweeperImpl implements MineSweeper {

    private boolean state;
    private char [][]mineFieldCharArray2D;
    private int COL;
    private int ROW;

    public void setMineField(String mineField) throws IllegalArgumentException {

        state = true;

        if(mineField == null || mineField.equals("")){
            throw new IllegalArgumentException();
        }

        char[] mineFieldCharArray = mineField.toCharArray();
        final int len = mineFieldCharArray.length;

        this.ROW=1;

        for (char c : mineFieldCharArray) {
            if (c == '\n') ROW++; // convertTo2DIndex row
            if (c != '*' && c != '\n' && c != '.') {
                throw new IllegalArgumentException();
            }
        }

        mineField = mineField.replace("\n", "");
        mineFieldCharArray = mineField.toCharArray();

        this.COL = len/ROW;

        this.mineFieldCharArray2D = new char[ROW+2][COL+2];

        // convert to 2D
        int convertTo2DIndex = 0;
        for(int i = 1; i <= ROW; i++) {
            for(int j = 1; j <= COL; j++) {
                try {
                    mineFieldCharArray2D[i][j] = mineFieldCharArray[convertTo2DIndex++];
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Incorrect dimension");
                    e.getMessage();
                }
            }
        }

    }

    public String getHintField() throws IllegalStateException {
        if(!state) {
            throw new IllegalStateException();
        }

        char[][] solved = new char[ROW+2][COL+2];

        for(int i = 1; i <= ROW; i++) {
            for(int j = 1; j <= COL; j++) {
                if(mineFieldCharArray2D[i][j] != '*') {
                    solved[i][j] = '0';
                }
                else {
                    solved[i][j] = '*';
                }
            }
        }

        for(int i = 1; i <= ROW; i++) {
            for(int j = 1; j <= COL; j++) {
                // neighboring cells
                for (int ii = i - 1; ii <= i + 1; ii++)
                    for (int jj = j - 1; jj <= j + 1; jj++)
                        if (mineFieldCharArray2D[ii][jj] == '*') solved[i][j]++;
            }
        }




        String goBackToString = new String();

        for(int i=1; i<= ROW; i++) {
            goBackToString += String.copyValueOf(solved[i]) + "\n";
        }

        goBackToString = goBackToString.replaceAll("\u0000", ""); // replace nulls
        goBackToString = goBackToString.replaceAll("[^0-9\\n]","*"); // replace anything except numbers and new lines


        return goBackToString;
    }
}
