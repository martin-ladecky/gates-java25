package com.commerzbank.homework.components;

import com.commerzbank.homework.core.Wire;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MasterSlaveFlipFlopTwoTest {

    private Wire data;
    private Wire clock;
    private MasterSlaveFlipFlopTwo ff;

    @BeforeEach
    void setup() {
        data = new Wire();
        clock = new Wire();
        ff = new MasterSlaveFlipFlopTwo(clock, data);
    }

    @Test
    void testInitialStateIsFalse() {
        // Initial state should be false before any clock edge
        assertFalse(ff.getState(), "Initial state should be false");
    }

    @Test
    void testRisingEdge_capturesTrue() {
        // Data is high, clock is low
        data.setSignal(true);
        clock.setSignal(false);
        assertFalse(ff.getState(), "State should not change while clock is low");

        // Rising edge
        clock.setSignal(true);
        assertTrue(ff.getState(), "State should become true on rising edge");
    }

    @Test
    void testRisingEdge_capturesFalse() {
        // First, set state to true
        data.setSignal(true);
        clock.setSignal(false);
        clock.setSignal(true); // Rising edge, captures true
        assertTrue(ff.getState());

        // Data is low, clock is high
        data.setSignal(false);
        assertTrue(ff.getState(), "State should not change while clock is high");

        // Falling edge
        clock.setSignal(false);
        assertTrue(ff.getState(), "State should not change on falling edge");

        // Rising edge
        clock.setSignal(true);
        assertFalse(ff.getState(), "State should become false on rising edge");
    }

    @Test
    void testDataChangesIgnored_whileClockIsHigh() {
        // Capture initial true state
        data.setSignal(true);
        clock.setSignal(false);
        clock.setSignal(true); // Rising edge
        assertTrue(ff.getState());

        // Change data while clock is high
        data.setSignal(false);
        assertTrue(ff.getState(), "State should not change when data changes while clock is high");
    }

    @Test
    void testDataChangesIgnored_whileClockIsLow() {
        // Initial state is false
        assertFalse(ff.getState());

        // Change data while clock is low
        data.setSignal(true);
        assertFalse(ff.getState(), "State should not change when data changes while clock is low");
    }

    @Test
    void testMultipleClockCycles() {
        // Cycle 1: Capture true
        data.setSignal(true);
        clock.setSignal(false);
        clock.setSignal(true); // Rising edge
        assertTrue(ff.getState());

        // Cycle 2: Capture false
        data.setSignal(false);
        clock.setSignal(false);
        clock.setSignal(true); // Rising edge
        assertFalse(ff.getState());

        // Cycle 3: Capture true again
        data.setSignal(true);
        clock.setSignal(false);
        clock.setSignal(true); // Rising edge
        assertTrue(ff.getState());
    }
}
