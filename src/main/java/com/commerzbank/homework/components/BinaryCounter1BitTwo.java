package com.commerzbank.homework.components;

import com.commerzbank.homework.core.Signal;

/**
 * A 1-bit binary counter, also known as a T-Flip-Flop (Toggle Flip-Flop).
 * This component toggles its output state on the <b>rising edge</b> of the clock signal.
 * It effectively divides the clock frequency by two.
 */
public class BinaryCounter1BitTwo implements Signal {
    private final MasterSlaveFlipFlopTwo flipFlop;

    /**
     * Constructs a rising-edge 1-bit binary counter.
     */
    public BinaryCounter1BitTwo() {
        this.flipFlop = new MasterSlaveFlipFlopTwo();
    }

    /**
     * Controls the clock signal for the counter.
     * On the rising edge (false -> true), the counter will toggle its state.
     * @param state the new boolean state of the clock.
     */
    public void setClock(boolean state) {
        // The core logic of a T-Flip-Flop: the data input (D) is always set
        // to the inverse of the current output (!Q).
        boolean notQ = flipFlop.getNotQState();
        flipFlop.setData(notQ);

        // Pass the clock signal to the flip-flop to trigger the state change.
        flipFlop.setClock(state);
    }

    /**
     * Gets the current output state of the counter.
     * @return the current boolean state.
     */
    @Override
    public boolean getState() {
        return flipFlop.getState();
    }

    /**
     * Gets the inverted output state of the counter.
     * @return the inverted version of the current state.
     */
    public boolean getNotQ() {
        return flipFlop.getNotQState();
    }
}
