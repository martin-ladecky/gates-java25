package com.commerzbank.homework.components;

import com.commerzbank.homework.core.Wire;

public class BinaryCounter4Bit {
    private final Wire clockInput = new Wire();
    
    // Using List for cleaner iteration if needed later
    private final BinaryCounter1Bit bit0;
    private final BinaryCounter1Bit bit1;
    private final BinaryCounter1Bit bit2;
    private final BinaryCounter1Bit bit3;

    public BinaryCounter4Bit() {
        // Ripple structure
        this.bit0 = new BinaryCounter1Bit(clockInput);
        this.bit1 = new BinaryCounter1Bit(bit0);
        this.bit2 = new BinaryCounter1Bit(bit1);
        this.bit3 = new BinaryCounter1Bit(bit2);
    }

    public void setClock(boolean state) {
        clockInput.setSignal(state);
    }

    public int getValue() {
        int val = 0;
        if (bit0.getState()) val += 1;
        if (bit1.getState()) val += 2;
        if (bit2.getState()) val += 4;
        if (bit3.getState()) val += 8;
        return val;
    }
}
