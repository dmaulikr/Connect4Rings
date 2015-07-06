package com.dabami.connect4.model;

import com.dabami.connect4.util.GameConstants;

public class Slot {

    private int type;

    public Slot() {
        initializeEmptySlot();
    }

    private void initializeEmptySlot() {
        this.type = GameConstants.VOID_POSITION;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
