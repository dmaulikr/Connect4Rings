package dabami.connect4.controller;

import java.util.Random;

import dabami.connect4.util.GameConstants;

public class Game {

    private int[][] table = new int[GameConstants.MAX_ROWS][GameConstants.MAX_COLUMNS];

    public Game() {
        initializeEmptyTable();
    }

    private void initializeEmptyTable() {
        for (int row = GameConstants.FIRST_ROW; row < GameConstants.MAX_ROWS; row++) {
            for (int column = GameConstants.FIRST_COLUMN; column < GameConstants.MAX_COLUMNS; column++) {
                this.table[row][column] = GameConstants.VOID_POSITION;
            }
        }
    }

    public boolean isColumnAvailable(int column) {
        if (this.table[GameConstants.FIRST_ROW][column] == GameConstants.VOID_POSITION) {
            return true;
        }
        return false;
    }

    public void aiMovement() {
        Random randomGenerator = new Random();
        int column = randomGenerator.nextInt(GameConstants.MAX_COLUMNS);
        while (!isColumnAvailable(column)) {
            column = randomGenerator.nextInt(GameConstants.MAX_COLUMNS);
        }
        dropCoin(column, GameConstants.AI_COIN);
    }

    public void dropCoin(int column, int coin) {
        boolean inserted = false;
        for (int row = GameConstants.FIRST_ROW; row < GameConstants.MAX_ROWS; row++) {
            if (this.table[row][column] != GameConstants.VOID_POSITION) {
                this.table[row - 1][column] = coin;
                inserted = true;
            }
        }
        if(!inserted) {
            this.table[GameConstants.MAX_ROWS - 1][column] = coin;
        }

    }

    private boolean checkRows(int insertedCoinType){
        for (int row = GameConstants.FIRST_ROW; row < GameConstants.MAX_ROWS; row++) {
            int count = 0;
            for(int column = GameConstants.FIRST_COLUMN; column < GameConstants.MAX_COLUMNS; column++){
                if(this.table[row][column] == insertedCoinType){
                    count++;
                }else{
                    count = 0;
                }
                if(count == GameConstants.WIN_NUMBER){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkColumns(int insertedCoinType){
        for(int column = GameConstants.FIRST_COLUMN; column < GameConstants.MAX_COLUMNS; column++){
            int count = 0;
            for(int row = GameConstants.FIRST_ROW; row < GameConstants.MAX_ROWS; row++){
                if(this.table[row][column] == insertedCoinType){
                    count++;
                }else{
                    count = 0;
                }
                if(count == GameConstants.WIN_NUMBER){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonals(int insertedCoinType){
        return false;
    }

    public int[][] getTable() {
        return table;
    }

    public void setTable(int[][] table) {
        this.table = table;
    }
}
