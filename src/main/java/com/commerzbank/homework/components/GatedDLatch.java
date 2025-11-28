package com.commerzbank.homework.components;

import com.commerzbank.homework.core.Signal;
import com.commerzbank.homework.core.Wire;
import com.commerzbank.homework.gates.NandGate;

public class GatedDLatch implements Signal {
    private final Wire qOutput = new Wire();
    private final Wire notQOutput = new Wire();
    
    // Internal gates kept for structure, though simulation drives wires
    private final NandGate gate3;
    private final NandGate gate4;

    public GatedDLatch(Signal enable, Signal data) {
        var gate1 = new NandGate(data, enable);
        
        var invertedData = new NandGate(data, data);
        var gate2 = new NandGate(invertedData, enable);

        // Feedback loop setup
        this.gate3 = new NandGate(gate1, notQOutput);
        this.gate4 = new NandGate(gate2, qOutput);
    }

    /**
     * Resolves the feedback loop for this latch.
     */
    public void stabilize() {
        // In a simulation loop, we might iterate until convergence.
        // For this simple latch, 5 iterations is plenty.
        for (int i = 0; i < 5; i++) {
            boolean q = gate3.getState();
            boolean nq = gate4.getState();
            qOutput.setSignal(q);
            notQOutput.setSignal(nq);
        }
    }

    @Override
    public boolean getState() {
        stabilize();
        return qOutput.getState();
    }
    
    public boolean getNotQState() {
        stabilize();
        return notQOutput.getState();
    }
}
