package org.sovas;

import org.sovas.service.MineSweeperImpl;

public class ApplicationStarter {

    public static void main(String[] args) {

        MineSweeperImpl mineSweeper = new MineSweeperImpl();

        String mineField = "*..*.\n...*.\n....*";

        mineSweeper.setMineField(mineField);
        String solved = mineSweeper.getHintField();

        System.out.println("OUTPUT:\n" + solved);

    }

}
