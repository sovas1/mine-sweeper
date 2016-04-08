package org.sovas.service;

public class MineSweeperImpl implements MineSweeper {

    private boolean state;
    private char [][]field;
    private int COL;
    private int ROW;

    public void setMineField(String mineField) throws IllegalArgumentException {

        state = true;

        if(mineField == null){
            throw new IllegalArgumentException();
        }

        char[] charArray = mineField.toCharArray();
        final int len = charArray.length;

        this.ROW=1;

        for(int i = 0; i < len; i++) {
            if(charArray[i] == '\n') ROW++;
            if(charArray[i] != '*' && charArray[i] != '\n' && charArray[i] != '.') {
                throw new IllegalArgumentException();
            }
        }

        mineField = mineField.replace("\n", "");
        charArray = mineField.toCharArray();
        int count = 0;


        this.COL = len/ROW;

        this.field = new char[ROW+2][COL+2];


        for(int i = 1; i <= ROW; i++) {
            for(int j = 1; j <= COL; j++) {
                field[i][j] = charArray[count];
                count++;
            }
        }

        for(int i = 1; i <= ROW; i++) {
            for(int j = 1; j <= COL; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }


    }

    public String getHintField() throws IllegalStateException {
        if(!state) {
            throw new IllegalStateException();
        }

        char[][] solved = new char[ROW+2][COL+2];

        for(int i = 1; i <= ROW; i++) {
            for(int j = 1; j <= COL; j++) {
                if(field[i][j] != '*') {
                    solved[i][j] = '0';
                }
                else {
                    solved[i][j] = '*';
                }
            }
        }

        for(int i = 1; i <= ROW; i++) {
            for(int j = 1; j <= COL; j++) {
                // (ii, jj) indexes neighboring cells
                for (int ii = i - 1; ii <= i + 1; ii++)
                    for (int jj = j - 1; jj <= j + 1; jj++)
                        if (field[ii][jj] == '*') solved[i][j]++;
            }
        }




        String goBackToString = new String();

        for(int i=1; i<= ROW; i++) {
            goBackToString += String.copyValueOf(solved[i]) + "\n";
        }

        System.out.println("\nOUTPUT BEFORE REGEX:");
        System.out.println(goBackToString);

        goBackToString = goBackToString.replaceAll("\u0000", "");
        goBackToString = goBackToString.replaceAll("[^0-9\\n]","*");


        return goBackToString;
    }
}
