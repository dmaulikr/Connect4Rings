package com.dabami.connect4.controller;

import com.dabami.connect4.model.Slot;
import com.dabami.connect4.util.GameConstants;

public class Table {

    private Slot[][] slots;

    public Table() {
        initializeEmptyTable();
    }

    private void initializeEmptyTable() {
        this.slots = new Slot[GameConstants.MAX_ROWS][GameConstants.MAX_COLUMNS];
        for (int row = GameConstants.FIRST_ROW; row < GameConstants.MAX_ROWS; row++) {
            for (int column = GameConstants.FIRST_COLUMN; column < GameConstants.MAX_COLUMNS; column++) {
                Slot slot = new Slot();
                this.slots[row][column] = slot;
            }
        }
    }

    public boolean isColumnAvailable(int column) {
        return (this.slots[GameConstants.FIRST_ROW][column].getType() == GameConstants.VOID_SLOT);
    }

    public void dropCoin(int column, int coin) {
        boolean inserted = false;
        for (int row = GameConstants.FIRST_ROW; row < GameConstants.MAX_ROWS; row++) {
            if (this.slots[row][column].getType() != GameConstants.VOID_SLOT) {
                this.slots[row - 1][column].setType(coin);
                inserted = true;
                break;
            }
        }
        if (!inserted) {
            this.slots[GameConstants.MAX_ROWS - 1][column].setType(coin);
        }
    }

    private boolean checkRows(int insertedCoinType) {
        for (int row = GameConstants.FIRST_ROW; row < GameConstants.MAX_ROWS; row++) {
            int count = 0;
            for (int column = GameConstants.FIRST_COLUMN; column < GameConstants.MAX_COLUMNS; column++) {
                if (this.slots[row][column].getType() == insertedCoinType) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == GameConstants.WIN_NUMBER) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkColumns(int insertedCoinType) {
        for (int column = GameConstants.FIRST_COLUMN; column < GameConstants.MAX_COLUMNS; column++) {
            int count = 0;
            for (int row = GameConstants.FIRST_ROW; row < GameConstants.MAX_ROWS; row++) {
                if (this.slots[row][column].getType() == insertedCoinType) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == GameConstants.WIN_NUMBER) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonals(int insertedCoinType) {
        String victoryChain;
        if (GameConstants.AI_COIN == insertedCoinType) {
            victoryChain = GameConstants.VICTORY_CHAIN_AI;
        } else {
            victoryChain = GameConstants.VICTORY_CHAIN_USER;
        }

        for (int i = 0; i < 3; i++) {
            String str = "";
            for (int k = 0; k < 6 - i; k++)
                str += Integer.toString(this.slots[i + k][k].getType());
            if (str.contains(victoryChain))
                return true;
        }

        for (int j = 1; j < 4; j++) {
            String str = "";
            for (int k = 0; k < 7 - j; k++)
                str += Integer.toString(this.slots[k][j + k].getType());
            if (str.contains(victoryChain))
                return true;
        }
        return false;
    }

    public boolean checkTableState(int insertedCoinType) {
        if (checkRows(insertedCoinType) || checkColumns(insertedCoinType) || checkDiagonals(insertedCoinType)) {
            return true;
        } else {
            return false;
        }
    }

    public Slot getSlot(int row, int column) {
        return this.slots[row][column];
    }
}
