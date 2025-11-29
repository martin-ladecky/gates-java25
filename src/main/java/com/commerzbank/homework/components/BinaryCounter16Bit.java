package com.commerzbank.homework.components;

import com.commerzbank.homework.gates.AndGate;

/**
 * A 16-bit synchronous binary counter.
 * This counter increments its value on the <b>rising edge</b> of the clock signal.
 *
 * <p>This is a synchronous counter, meaning all internal flip-flops share the same
 * clock signal. The logic for toggling each bit is handled by a chain of AND gates,
 * which makes the counter more stable and predictable than an asynchronous (ripple)
 * counter.
 */
public class BinaryCounter16Bit {

    private final BinaryCounter1BitTwo[] bits = new BinaryCounter1BitTwo[16];
    private final AndGate[] andGates = new AndGate[15];

    public BinaryCounter16Bit() {
        for (int i = 0; i < bits.length; i++) {
            bits[i] = new BinaryCounter1BitTwo();
        }

        // Logic for enabling the toggle of subsequent bits
        // andGates[0] enables bit2, and is AND(bit0, bit1)
        // andGates[1] enables bit3, and is AND(andGates[0], bit2)
        // ...and so on.
        andGates[0] = new AndGate(bits[0], bits[1]);
        for (int i = 1; i < andGates.length; i++) {
            andGates[i] = new AndGate(andGates[i - 1], bits[i + 1]);
        }
    }

    /**
     * Controls the clock signal for the counter. On the rising edge, the count will increment.
     * @param state The new boolean state of the clock.
     */
    public void setClock(boolean state) {
        // In a synchronous counter, the decision to toggle is made *before* the clock pulse.
        boolean[] toggleConditions = new boolean[bits.length];
        toggleConditions[0] = true; // bit0 always toggles
        toggleConditions[1] = bits[0].getState();
        for (int i = 2; i < toggleConditions.length; i++) {
            toggleConditions[i] = andGates[i - 2].getState();
        }

        // Apply the clock to all bits that meet their toggle condition.
        for (int i = 0; i < bits.length; i++) {
            if (toggleConditions[i]) {
                bits[i].setClock(state);
            }
        }
    }

    /**
     * Gets the current integer value of the counter (0-65535).
     * @return The current count.
     */
    public int getValue() {
        int val = 0;
        for (int i = 0; i < bits.length; i++) {
            if (bits[i].getState()) {
                val += (1 << i); // Use bit-shift for powers of 2 (1, 2, 4, 8...)
            }
        }
        return val;
    }
}
