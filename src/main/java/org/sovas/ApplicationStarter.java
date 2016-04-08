package org.sovas;

import org.sovas.service.MineSweeperImpl;

public class ApplicationStarter {

    public static void main(String[] args) {

        MineSweeperImpl mineSweeper = new MineSweeperImpl();

        String mineField = "*..*.\n...*.\n....*";


        System.out.println("\nINPUT:\n" + mineField);

        mineSweeper.setMineField(mineField);
        String solved = mineSweeper.getHintField();

        System.out.println("\nOUTPUT:\n" + solved);

    }

}
