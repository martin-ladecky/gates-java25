package com.commerzbank.homework.gates;

import com.commerzbank.homework.core.Gate;
import com.commerzbank.homework.core.Signal;

/**
 * Fundamental NAND Gate.
 */
public class NandGate extends Gate {
    private final Signal inputA;
    private final Signal inputB;
    private boolean outputState;

    public NandGate(Signal inputA, Signal inputB) {
        this.inputA = inputA;
        this.inputB = inputB;
        evaluate();
    }

    @Override
    public void evaluate() {
        // Modern syntax: explicit boolean logic
        this.outputState = !(inputA.getState() && inputB.getState());
    }

    @Override
    public boolean getState() {
        evaluate();
        return outputState;
    }
}
