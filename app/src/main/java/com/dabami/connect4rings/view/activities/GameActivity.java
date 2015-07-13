package com.dabami.connect4rings.view.activities;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dabami.connect4rings.controller.Game;
import com.dabami.connect4rings.util.GameConstants;
import com.dabami.connect4rings.view.fragments.BackToMainMenuDialogFragment;
import com.dabami.connect4rings.view.fragments.BackToMainMenuDialogFragment.BackToMainMenuDialogListener;

import dabami.connect4rings.R;

public class GameActivity extends Activity implements BackToMainMenuDialogListener {

    private Animation animTranslate;
    private int[][] ids;
    private int[][] printedIds;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        setViews();
        setAnimations();

        this.game = new Game();
    }

    private void setViews() {
        ids = new int[][]{{R.id.b_0_0, R.id.b_0_1, R.id.b_0_2, R.id.b_0_3, R.id.b_0_4, R.id.b_0_5, R.id.b_0_6},
                {R.id.b_1_0, R.id.b_1_1, R.id.b_1_2, R.id.b_1_3, R.id.b_1_4, R.id.b_1_5, R.id.b_1_6},
                {R.id.b_2_0, R.id.b_2_1, R.id.b_2_2, R.id.b_2_3, R.id.b_2_4, R.id.b_2_5, R.id.b_2_6},
                {R.id.b_3_0, R.id.b_3_1, R.id.b_3_2, R.id.b_3_3, R.id.b_3_4, R.id.b_3_5, R.id.b_3_6},
                {R.id.b_4_0, R.id.b_4_1, R.id.b_4_2, R.id.b_4_3, R.id.b_4_4, R.id.b_4_5, R.id.b_4_6},
                {R.id.b_5_0, R.id.b_5_1, R.id.b_5_2, R.id.b_5_3, R.id.b_5_4, R.id.b_5_5, R.id.b_5_6}};

        printedIds = new int[GameConstants.MAX_ROWS][GameConstants.MAX_COLUMNS];
    }

    private void setAnimations() {
        animTranslate = AnimationUtils.loadAnimation(this, R.anim.translate);
    }

    private int fromIdToColumn(int id) {
        for (int row = GameConstants.FIRST_ROW; row < GameConstants.MAX_ROWS; row++) {
            for (int column = GameConstants.FIRST_COLUMN; column < GameConstants.MAX_COLUMNS; column++) {
                if (this.ids[row][column] == id) {
                    return column;
                }
            }
        }
        return 0;
    }

    public void onClick(View view) {
        ImageButton imageButton = (ImageButton) view;
        int id = imageButton.getId();
        int column = fromIdToColumn(id);
        if (this.game.userMovement(column)) {
            drawTable();
            showGameStatus(GameConstants.USER_COIN);
            this.game.aiMovement();
            drawTable();
            showGameStatus(GameConstants.AI_COIN);
        } else {
            Toast.makeText(this, R.string.full_column, Toast.LENGTH_SHORT).show();
        }
    }

    public void drawTable() {
        for (int row = GameConstants.FIRST_ROW; row < GameConstants.MAX_ROWS; row++) {
            for (int column = GameConstants.FIRST_COLUMN; column < GameConstants.MAX_COLUMNS; column++) {
                int slotType = this.game.getSlotType(row, column);
                if (slotType != GameConstants.VOID_SLOT) {
                    if (this.ids[row][column] != this.printedIds[row][column]) {
                        final ImageButton imageButton = (ImageButton) findViewById(this.ids[row][column]);
                        imageButton.startAnimation(animTranslate);
                        if (slotType == GameConstants.AI_COIN) {
                            imageButton.setImageResource(R.drawable.ai_coin);
                        } else {
                            imageButton.setImageResource(R.drawable.user_coin);
                        }
                        this.printedIds[row][column] = this.ids[row][column];
                    }
                }
            }
        }
    }

    public void showGameStatus(int coin) {
        boolean status = this.game.checkGameStatus(coin);
        if (status) {
            if (coin == GameConstants.USER_COIN) {
                Toast.makeText(this, R.string.user_victory, Toast.LENGTH_SHORT).show();
            } else if (coin == GameConstants.AI_COIN) {
                Toast.makeText(this, R.string.ai_victory, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        DialogFragment dialogFragment = new BackToMainMenuDialogFragment();
        dialogFragment.show(getFragmentManager(), getResources().getString(R.string.back_dialog_tag));
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        dialog.dismiss();
        super.onBackPressed();
    }
}
