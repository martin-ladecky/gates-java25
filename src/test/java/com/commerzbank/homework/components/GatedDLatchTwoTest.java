package com.commerzbank.homework.components;

import com.commerzbank.homework.core.Wire;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GatedDLatchTwoTest {

    @Test
    void testLatch_enabled_dataTrue() {
        Wire data = new Wire();
        data.setSignal(true);
        Wire enable = new Wire();
        enable.setSignal(true);
        GatedDLatchTwo latch = new GatedDLatchTwo(enable, data);

        latch.evaluate();

        assertTrue(latch.getState(), "Q should be true when enabled and data is true");
        assertFalse(latch.getNotQState(), "NotQ should be false when enabled and data is true");
    }

    @Test
    void testLatch_enabled_dataFalse() {
        Wire data = new Wire(); // Initial state is false
        Wire enable = new Wire();
        enable.setSignal(true);
        GatedDLatchTwo latch = new GatedDLatchTwo(enable, data);

        latch.evaluate();

        assertFalse(latch.getState(), "Q should be false when enabled and data is false");
        assertTrue(latch.getNotQState(), "NotQ should be true when enabled and data is false");
    }

    @Test
    void testLatch_disabled_holdsState_wasTrue() {
        Wire data = new Wire();
        data.setSignal(true);
        Wire enable = new Wire();
        enable.setSignal(true);
        GatedDLatchTwo latch = new GatedDLatchTwo(enable, data);

        latch.evaluate(); // Latch the 'true' value
        assertTrue(latch.getState());

        // Disable the latch
        enable.setSignal(false);
        latch.evaluate();
        assertTrue(latch.getState(), "Q should hold its true state when disabled");

        // Change data while disabled
        data.setSignal(false);
        latch.evaluate();
        assertTrue(latch.getState(), "Q should still hold its true state even when data changes while disabled");
    }

    @Test
    void testLatch_disabled_holdsState_wasFalse() {
        Wire data = new Wire(); // Initial state is false
        Wire enable = new Wire();
        enable.setSignal(true);
        GatedDLatchTwo latch = new GatedDLatchTwo(enable, data);

        latch.evaluate(); // Latch the 'false' value
        assertFalse(latch.getState());

        // Disable the latch
        enable.setSignal(false);
        latch.evaluate();
        assertFalse(latch.getState(), "Q should hold its false state when disabled");

        // Change data while disabled
        data.setSignal(true);
        latch.evaluate();
        assertFalse(latch.getState(), "Q should still hold its false state even when data changes while disabled");
    }

    @Test
    void testStateChange_throughEnable() {
        Wire data = new Wire(); // Initial state is false
        Wire enable = new Wire();
        enable.setSignal(true);
        GatedDLatchTwo latch = new GatedDLatchTwo(enable, data);

        latch.evaluate();
        assertFalse(latch.getState());

        // Change data
        data.setSignal(true);
        latch.evaluate();
        assertTrue(latch.getState(), "Q should follow data when enabled");

        // Disable latch
        enable.setSignal(false);
        latch.evaluate();
        assertTrue(latch.getState(), "Q should hold state when disabled");

        // Change data back to false (should have no effect)
        data.setSignal(false);
        latch.evaluate();
        assertTrue(latch.getState(), "Q should ignore data change when disabled");

        // Re-enable latch, should now follow data again
        enable.setSignal(true);
        latch.evaluate();
        assertFalse(latch.getState(), "Q should follow data again when re-enabled");
    }
}
