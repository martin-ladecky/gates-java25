package com.commerzbank.homework.components;

import com.commerzbank.homework.core.Signal;
import com.commerzbank.homework.core.Wire;
import com.commerzbank.homework.gates.NandGate;

public class GatedDLatchTwo implements Signal {
    private final Wire qOutput = new Wire();
    private final Wire notQOutput = new Wire();

    // Internal gates kept for structure, though simulation drives wires
    private final NandGate gate3;
    private final NandGate gate4;

    public GatedDLatchTwo(Signal enable, Signal data) {
        var gate1 = new NandGate(data, enable);
        
        var gate2 = new NandGate(gate1, enable);

        // Feedback loop setup
        this.gate3 = new NandGate(gate1, notQOutput);
        this.gate4 = new NandGate(gate2, qOutput);
        qOutput.setSignal(false);
        notQOutput.setSignal(true);
        evaluate();
    }

    /*
     * Resolves the feedback loop for this latch.
     */
    public void evaluate() {
            boolean q = gate3.getState();
            boolean nq = gate4.getState();
            qOutput.setSignal(q);
            notQOutput.setSignal(nq);
    }

    @Override
    public boolean getState() {
        evaluate();
        return qOutput.getState();
    }
    
    public boolean getNotQState() {
        evaluate();
        return notQOutput.getState();
    }
}
