package com.commerzbank.homework.gates;

import com.commerzbank.homework.core.Gate;
import com.commerzbank.homework.core.Signal;

public class AndGate extends Gate {
    private final Signal finalOutput;

    public AndGate(Signal inputA, Signal inputB) {
        // Using 'var' for cleaner local variable inference
        var n1 = new NandGate(inputA, inputB);
        this.finalOutput = new NandGate(n1, n1);
    }

    @Override
    protected void evaluate() {} // Compositional gate, no internal logic needed

    @Override
    public boolean getState() {
        return finalOutput.getState();
    }
}
