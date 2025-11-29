package com.commerzbank.homework.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MasterSlaveFlipFlopTwoTest {

    private MasterSlaveFlipFlopTwo ff;

    @BeforeEach
    void setup() {
        ff = new MasterSlaveFlipFlopTwo();
    }

    @Test
    void testInitialStateIsFalse() {
        // Initial state should be false before any clock edge
        assertFalse(ff.getState(), "Initial state should be false");
    }

    @Test
    void testRisingEdge_capturesTrue() {
        // Data is high, clock is low
        ff.setData(true);
        ff.setClock(false);
        assertFalse(ff.getState(), "State should not change while clock is low");

        // Rising edge
        ff.setClock(true);
        assertTrue(ff.getState(), "State should become true on rising edge");
    }

    @Test
    void testRisingEdge_capturesFalse() {
        // First, set state to true
        ff.setData(true);
        ff.setClock(false);
        ff.setClock(true); // Rising edge, captures true
        assertTrue(ff.getState());

        // Data is low, clock is high
        ff.setData(false);
        assertTrue(ff.getState(), "State should not change while clock is high");

        // Falling edge
        ff.setClock(false);
        assertTrue(ff.getState(), "State should not change on falling edge");

        // Rising edge
        ff.setClock(true);
        assertFalse(ff.getState(), "State should become false on rising edge");
    }

    @Test
    void testDataChangesIgnored_whileClockIsHigh() {
        // Capture initial true state
        ff.setData(true);
        ff.setClock(false);
        ff.setClock(true); // Rising edge
        assertTrue(ff.getState());

        // Change data while clock is high
        ff.setData(false);
        assertTrue(ff.getState(), "State should not change when data changes while clock is high");
    }

    @Test
    void testDataChangesIgnored_whileClockIsLow() {
        // Initial state is false
        assertFalse(ff.getState());

        // Change data while clock is low
        ff.setData(true);
        ff.setClock(false); // This call now correctly pre-loads the master latch
        assertFalse(ff.getState(), "State should not change when data changes while clock is low");
    }

    @Test
    void testMultipleClockCycles() {
        // Cycle 1: Capture true
        ff.setData(true);
        ff.setClock(false);
        ff.setClock(true); // Rising edge
        assertTrue(ff.getState(), "Cycle 1: Should capture true");

        // Cycle 2: Capture false
        ff.setData(false);
        ff.setClock(false);
        ff.setClock(true); // Rising edge
        assertFalse(ff.getState(), "Cycle 2: Should capture false");

        // Cycle 3: Capture true again
        ff.setData(true);
        ff.setClock(false);
        ff.setClock(true); // Rising edge
        assertTrue(ff.getState(), "Cycle 3: Should capture true again");
    }
}
