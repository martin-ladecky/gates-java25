package com.commerzbank.homework.components;

import com.commerzbank.homework.core.Wire;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GatedDLatchTest {

    @Test
    void testLatch_enabled_dataTrue() {
        Wire data = new Wire(true);
        Wire enable = new Wire(true);
        GatedDLatch latch = new GatedDLatch(enable, data);

        latch.stabilize();

        assertTrue(latch.getState(), "Q should be true when enabled and data is true");
        assertFalse(latch.getNotQState(), "NotQ should be false when enabled and data is true");
    }

    @Test
    void testLatch_enabled_dataFalse() {
        Wire data = new Wire(false);
        Wire enable = new Wire(true);
        GatedDLatch latch = new GatedDLatch(enable, data);

        latch.stabilize();

        assertFalse(latch.getState(), "Q should be false when enabled and data is false");
        assertTrue(latch.getNotQState(), "NotQ should be true when enabled and data is false");
    }

    @Test
    void testLatch_disabled_holdsState_wasTrue() {
        Wire data = new Wire(true);
        Wire enable = new Wire(true);
        GatedDLatch latch = new GatedDLatch(enable, data);

        latch.stabilize(); // Latch the 'true' value
        assertTrue(latch.getState());

        // Disable the latch
        enable.setSignal(false);
        latch.stabilize();
        assertTrue(latch.getState(), "Q should hold its true state when disabled");

        // Change data while disabled
        data.setSignal(false);
        latch.stabilize();
        assertTrue(latch.getState(), "Q should still hold its true state even when data changes while disabled");
    }

    @Test
    void testLatch_disabled_holdsState_wasFalse() {
        Wire data = new Wire(false);
        Wire enable = new Wire(true);
        GatedDLatch latch = new GatedDLatch(enable, data);

        latch.stabilize(); // Latch the 'false' value
        assertFalse(latch.getState());

        // Disable the latch
        enable.setSignal(false);
        latch.stabilize();
        assertFalse(latch.getState(), "Q should hold its false state when disabled");

        // Change data while disabled
        data.setSignal(true);
        latch.stabilize();
        assertFalse(latch.getState(), "Q should still hold its false state even when data changes while disabled");
    }

    @Test
    void testStateChange_throughEnable() {
        Wire data = new Wire(false);
        Wire enable = new Wire(true);
        GatedDLatch latch = new GatedDLatch(enable, data);

        latch.stabilize();
        assertFalse(latch.getState());

        // Change data
        data.setSignal(true);
        latch.stabilize();
        assertTrue(latch.getState(), "Q should follow data when enabled");

        // Disable latch
        enable.setSignal(false);
        latch.stabilize();
        assertTrue(latch.getState(), "Q should hold state when disabled");

        // Change data back to false (should have no effect)
        data.setSignal(false);
        latch.stabilize();
        assertTrue(latch.getState(), "Q should ignore data change when disabled");

        // Re-enable latch, should now follow data again
        enable.setSignal(true);
        latch.stabilize();
        assertFalse(latch.getState(), "Q should follow data again when re-enabled");
    }
}
