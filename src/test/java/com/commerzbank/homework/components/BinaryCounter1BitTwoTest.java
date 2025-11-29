package com.commerzbank.homework.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryCounter1BitTwoTest {

    private BinaryCounter1BitTwo counter;

    @BeforeEach
    void setUp() {
        counter = new BinaryCounter1BitTwo();
    }

    @Test
    void testInitialStateIsFalse() {
        // The counter should initialize to a stable 'false' state.
        assertFalse(counter.getState(), "Initial state should be false");
    }

    @Test
    void testFirstRisingEdge_togglesToTrue() {
        // Initial state check
        assertFalse(counter.getState());

        // Clock goes low - no change expected
        counter.setClock(false);
        assertFalse(counter.getState(), "State should not change while clock is low");

        // Clock goes high (rising edge) - state should toggle
        counter.setClock(true);
        assertTrue(counter.getState(), "State should toggle to true on the first rising edge");
    }

    @Test
    void testSecondRisingEdge_togglesToFalse() {
        // --- First full clock cycle to get state to true ---
        counter.setClock(false); // low
        counter.setClock(true);  // high (rise) -> toggles to true
        assertTrue(counter.getState(), "Pre-condition: state should be true after first cycle");

        // --- Second clock cycle ---
        // Clock goes low again - no change expected
        counter.setClock(false);
        assertTrue(counter.getState(), "State should hold true on the falling edge");

        // Clock goes high again - state should toggle back to false
        counter.setClock(true);
        assertFalse(counter.getState(), "State should toggle back to false on the second rising edge");
    }

    @Test
    void testMultipleClockCycles() {
        // Initial state
        assertFalse(counter.getState());

        // Cycle 1 (-> true)
        counter.setClock(false);
        counter.setClock(true);
        assertTrue(counter.getState(), "After 1st rising edge, state should be true");

        // Cycle 2 (-> false)
        counter.setClock(false);
        counter.setClock(true);
        assertFalse(counter.getState(), "After 2nd rising edge, state should be false");

        // Cycle 3 (-> true)
        counter.setClock(false);
        counter.setClock(true);
        assertTrue(counter.getState(), "After 3rd rising edge, state should be true");

        // Cycle 4 (-> false)
        counter.setClock(false);
        counter.setClock(true);
        assertFalse(counter.getState(), "After 4th rising edge, state should be false");
    }
}
