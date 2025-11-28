package com.commerzbank.homework.gates;

import com.commerzbank.homework.core.Gate;
import com.commerzbank.homework.core.Signal;

public class XorGate extends Gate {
    private final Signal finalOutput;

    public XorGate(Signal inputA, Signal inputB) {
        // XOR from 4 NANDs
        var n1 = new NandGate(inputA, inputB);
        var n2 = new NandGate(inputA, n1);
        var n3 = new NandGate(n1, inputB);
        this.finalOutput = new NandGate(n2, n3);
    }

    @Override
    public boolean getState() {
        return finalOutput.getState();
    }

    @Override
    protected void evaluate() {}
}
