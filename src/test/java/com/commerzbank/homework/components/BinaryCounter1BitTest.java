package com.commerzbank.homework.components;

import com.commerzbank.homework.core.Wire;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("For explanation purpose only")
class BinaryCounter1BitTest {

    private Wire clock;
    private BinaryCounter1Bit counter;

    @BeforeEach
    void setUp() {
        clock = new Wire();
        counter = new BinaryCounter1Bit(clock);
    }

    @Test
    void testInitialStateIsFalse() {
        // Before any clock movement, the internal flip-flop should be in its initial 'false' state.
        assertFalse(counter.getState(), "Initial state should be false");
    }

    @Test
    void testFirstFallingEdge_togglesToTrue() {
        // Initial state check
        assertFalse(counter.getState());

        // Clock goes high (rising edge) - no change expected for this flip-flop type
        clock.setSignal(true);
        assertFalse(counter.getState(), "State should not change on rising edge");

        // Clock goes low (falling edge) - state should toggle
        clock.setSignal(false);
        assertTrue(counter.getState(), "State should toggle to true on the first falling edge");
    }

    @Test
    void testSecondFallingEdge_togglesToFalse() {
        // --- First full clock cycle to get state to true ---
        clock.setSignal(true); // rise
        clock.setSignal(false); // fall
        assertTrue(counter.getState(), "Pre-condition: state should be true after first cycle");

        // --- Second clock cycle ---
        // Clock goes high again - no change expected
        clock.setSignal(true);
        assertTrue(counter.getState(), "State should hold true on the second rising edge");

        // Clock goes low again - state should toggle back to false
        clock.setSignal(false);
        assertFalse(counter.getState(), "State should toggle back to false on the second falling edge");
    }

    @Test
    void testMultipleClockCycles() {
        // Initial state
        assertFalse(counter.getState());

        // Cycle 1 (-> true)
        clock.setSignal(true);
        clock.setSignal(false);
        assertTrue(counter.getState(), "After 1st falling edge, state should be true");

        // Cycle 2 (-> false)
        clock.setSignal(true);
        clock.setSignal(false);
        assertFalse(counter.getState(), "After 2nd falling edge, state should be false");

        // Cycle 3 (-> true)
        clock.setSignal(true);
        clock.setSignal(false);
        assertTrue(counter.getState(), "After 3rd falling edge, state should be true");

        // Cycle 4 (-> false)
        clock.setSignal(true);
        clock.setSignal(false);
        assertFalse(counter.getState(), "After 4th falling edge, state should be false");
    }
}
