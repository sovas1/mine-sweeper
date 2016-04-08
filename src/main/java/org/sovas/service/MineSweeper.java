package org.sovas.service;

public interface MineSweeper {

    void setMineField(String mineField) throws IllegalArgumentException;

    String getHintField() throws IllegalStateException;
}
