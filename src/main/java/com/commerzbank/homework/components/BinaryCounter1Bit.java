package com.commerzbank.homework.components;

import com.commerzbank.homework.core.Signal;

public class BinaryCounter1Bit implements Signal {
    private final MasterSlaveFlipFlop flipFlop;
    private final FeedbackWire feedbackLoop = new FeedbackWire();

    public BinaryCounter1Bit(Signal clock) {
        this.flipFlop = new MasterSlaveFlipFlop(clock, feedbackLoop);
    }

    public void update() {
        feedbackLoop.setSignal(flipFlop.getNotQState());
    }

    @Override
    public boolean getState() {
        update();
        return flipFlop.getState();
    }
    
    public boolean getNotQ() {
        update();
        return flipFlop.getNotQState();
    }

    // A simple inner class acting as the feedback wire to break recursion
    private static class FeedbackWire implements Signal {
        private boolean value = false; 
        void setSignal(boolean v) { this.value = v; }
        @Override public boolean getState() { return value; }
    }
}
