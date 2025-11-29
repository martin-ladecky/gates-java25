package com.commerzbank.homework.components;

import com.commerzbank.homework.gates.AndGate;

/**
 * A 4-bit synchronous binary counter.
 * This counter increments its value on the <b>rising edge</b> of the clock signal.
 *
 * <p>This is a synchronous counter, meaning all internal flip-flops share the same
 * clock signal. The logic for toggling each bit is handled by a chain of AND gates,
 * which makes the counter more stable and predictable than an asynchronous (ripple)
 * counter.
 */
public class BinaryCounter4BitTwo {

    private final BinaryCounter1BitTwo bit0;
    private final BinaryCounter1BitTwo bit1;
    private final BinaryCounter1BitTwo bit2;
    private final BinaryCounter1BitTwo bit3;

    private final AndGate andGate1;
    private final AndGate andGate2;

    public BinaryCounter4BitTwo() {
        this.bit0 = new BinaryCounter1BitTwo();
        this.bit1 = new BinaryCounter1BitTwo();
        this.bit2 = new BinaryCounter1BitTwo();
        this.bit3 = new BinaryCounter1BitTwo();

        // Logic for enabling the toggle of subsequent bits
        this.andGate1 = new AndGate(bit0, bit1);
        this.andGate2 = new AndGate(andGate1, bit2);
    }

    /**
     * Controls the clock signal for the counter. On the rising edge, the count will increment.
     * @param state The new boolean state of the clock.
     */
    public void setClock(boolean state) {
        // In a synchronous counter, the decision to toggle is made *before* the clock pulse.
        boolean toggleBit1 = bit0.getState();
        boolean toggleBit2 = andGate1.getState();
        boolean toggleBit3 = andGate2.getState();

        // The first bit always toggles.
        bit0.setClock(state);

        // Subsequent bits only toggle if their respective enable condition is true.
        if (toggleBit1) {
            bit1.setClock(state);
        }
        if (toggleBit2) {
            bit2.setClock(state);
        }
        if (toggleBit3) {
            bit3.setClock(state);
        }
    }

    /**
     * Gets the current integer value of the counter (0-15).
     * @return The current count.
     */
    public int getValue() {
        int val = 0;
        if (bit0.getState()) val += 1;
        if (bit1.getState()) val += 2;
        if (bit2.getState()) val += 4;
        if (bit3.getState()) val += 8;
        return val;
    }
}
