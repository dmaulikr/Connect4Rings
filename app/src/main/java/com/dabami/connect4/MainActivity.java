package com.dabami.connect4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dabami.connect4.controller.Game;
import com.dabami.connect4.util.GameConstants;

import dabami.connect4.R;


public class MainActivity extends Activity {

    private final int[][] ids =
                   {{R.id.b_0_0, R.id.b_0_1, R.id.b_0_2, R.id.b_0_3, R.id.b_0_4, R.id.b_0_5, R.id.b_0_6},
                    {R.id.b_1_0, R.id.b_1_1, R.id.b_1_2, R.id.b_1_3, R.id.b_1_4, R.id.b_1_5, R.id.b_1_6},
                    {R.id.b_2_0, R.id.b_2_1, R.id.b_2_2, R.id.b_2_3, R.id.b_2_4, R.id.b_2_5, R.id.b_2_6},
                    {R.id.b_3_0, R.id.b_3_1, R.id.b_3_2, R.id.b_3_3, R.id.b_3_4, R.id.b_3_5, R.id.b_3_6},
                    {R.id.b_4_0, R.id.b_4_1, R.id.b_4_2, R.id.b_4_3, R.id.b_4_4, R.id.b_4_5, R.id.b_4_6},
                    {R.id.b_5_0, R.id.b_5_1, R.id.b_5_2, R.id.b_5_3, R.id.b_5_4, R.id.b_5_5, R.id.b_5_6}};

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.game = new Game();
    }

    private int[] fromIdToPosition(int id){
        for(int row = GameConstants.FIRST_ROW; row < GameConstants.MAX_ROWS; row++){
            for(int column = GameConstants.FIRST_COLUMN;column<GameConstants.MAX_COLUMNS;column++){
                if(this.ids[row][column] == id){
                    int[] position = {row, column};
                    return position;
                }
            }
        }
        return null;
    }

    public void onClick(View view){
        ImageButton imageButton = (ImageButton)view;
        int id = imageButton.getId();
        int[] position = fromIdToPosition(id);
        int column = position[1];
        if(this.game.isColumnAvailable(column)){
            this.game.dropCoin(column, GameConstants.PLAYER_COIN);
            this.game.aiMovement();
            drawTable();
        }else{
            Toast.makeText(this, R.string.full_column, Toast.LENGTH_SHORT).show();
        }
    }

    public void drawTable() {
        for (int row = GameConstants.FIRST_ROW; row < GameConstants.MAX_ROWS; row++) {
            for (int column = GameConstants.FIRST_COLUMN; column < GameConstants.MAX_COLUMNS; column++) {
                int[][] table = this.game.getTable();
                int tableSlot = table[row][column];
                if(tableSlot != GameConstants.VOID_POSITION){
                    ImageButton imageButton = (ImageButton) findViewById(this.ids[row][column]);
                    if(tableSlot == GameConstants.AI_COIN){
                        imageButton.setImageResource(R.drawable.c4_button_ai);
                    }else{
                        imageButton.setImageResource(R.drawable.c4_button_player);
                    }
                }
            }
        }
    }
}
