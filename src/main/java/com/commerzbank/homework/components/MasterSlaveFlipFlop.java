package com.commerzbank.homework.components;

import com.commerzbank.homework.core.Signal;
import com.commerzbank.homework.gates.NandGate;

public class MasterSlaveFlipFlop implements Signal {
    private final GatedDLatch slave;

    public MasterSlaveFlipFlop(Signal clock, Signal data) {
        var invertedClock = new NandGate(clock, clock);

        var master = new GatedDLatch(clock, data);
        this.slave = new GatedDLatch(invertedClock, master);
    }

    @Override
    public boolean getState() {
        return slave.getState();
    }
    
    public boolean getNotQState() {
        return slave.getNotQState();
    }
}
