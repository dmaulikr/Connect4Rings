package com.dabami.connect4.controller;

import com.dabami.connect4.util.GameConstants;

import java.util.Random;

public class Game {

    private Table table;

    public Game() {
        this.table = new Table();
    }

    public boolean userMovement(int column) {
        if (this.table.isColumnAvailable(column)) {
            this.table.dropCoin(column, GameConstants.USER_COIN);
            return true;
        }
        return false;
    }

    public void aiMovement() {
        Random randomGenerator = new Random();
        int column = randomGenerator.nextInt(GameConstants.MAX_COLUMNS);
        while (!this.table.isColumnAvailable(column)) {
            column = randomGenerator.nextInt(GameConstants.MAX_COLUMNS);
        }
        this.table.dropCoin(column, GameConstants.AI_COIN);
    }

    public int getSlotType(int row, int column) {
        return this.table.getSlot(row, column).getType();
    }

    public boolean checkGameStatus(int coin) {
        return this.table.checkTableState(coin);
    }
}
