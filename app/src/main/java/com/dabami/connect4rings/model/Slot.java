package com.dabami.connect4rings.model;

import com.dabami.connect4rings.util.GameConstants;

public class Slot {

    private int type;

    public Slot() {
        initializeEmptySlot();
    }

    private void initializeEmptySlot() {
        this.type = GameConstants.VOID_SLOT;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
