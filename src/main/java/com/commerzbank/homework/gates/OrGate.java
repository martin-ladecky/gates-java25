package com.commerzbank.homework.gates;

import com.commerzbank.homework.core.Gate;
import com.commerzbank.homework.core.Signal;

public class OrGate extends Gate {
    private final Signal finalOutput;

    public OrGate(Signal inputA, Signal inputB) {
        var notA = new NandGate(inputA, inputA);
        var notB = new NandGate(inputB, inputB);
        this.finalOutput = new NandGate(notA, notB);
    }

    @Override
    public boolean getState() {
        return finalOutput.getState();
    }

    @Override
    protected void evaluate() {}
}
