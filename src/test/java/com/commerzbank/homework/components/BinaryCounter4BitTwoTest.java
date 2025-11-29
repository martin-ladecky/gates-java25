package com.commerzbank.homework.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryCounter4BitTwoTest {

    private BinaryCounter4BitTwo counter;

    @BeforeEach
    void setUp() {
        counter = new BinaryCounter4BitTwo();
    }

    @Test
    void testInitialValueIsZero() {
        assertEquals(0, counter.getValue(), "Counter should initialize to 0");
    }

    @Test
    void testCountsUpToFifteenAndRollsOver() {
        // Test the full counting sequence from 0 to 15
        for (int i = 0; i <= 15; i++) {
            assertEquals(i, counter.getValue(), "Counter should be at value " + i);
            // Simulate a full clock pulse (rising edge)
            counter.setClock(false);
            counter.setClock(true);
        }

        // After 16 pulses (0-15), the next pulse should roll the counter over to 0
        assertEquals(0, counter.getValue(), "Counter should roll over to 0 after reaching 15");
    }

    @Test
    void testStateOnlyChangesOnRisingEdge() {
        // Initial state
        assertEquals(0, counter.getValue());

        // Set clock low
        counter.setClock(false);
        assertEquals(0, counter.getValue(), "Count should not change when clock goes low");

        // Set clock high (rising edge) -> count should change
        counter.setClock(true);
        assertEquals(1, counter.getValue(), "Count should increment on rising edge");

        // Set clock high again (no change)
        counter.setClock(true);
        assertEquals(1, counter.getValue(), "Count should not change while clock is high");

        // Set clock low (falling edge) -> count should not change
        counter.setClock(false);
        assertEquals(1, counter.getValue(), "Count should not change on falling edge");
    }

    @Test
    void testStepByStepCount() {
        assertEquals(0, counter.getValue());

        // Pulse 1 -> 1
        counter.setClock(false);
        counter.setClock(true);
        assertEquals(1, counter.getValue());

        // Pulse 2 -> 2
        counter.setClock(false);
        counter.setClock(true);
        assertEquals(2, counter.getValue());

        // Pulse 3 -> 3
        counter.setClock(false);
        counter.setClock(true);
        assertEquals(3, counter.getValue());

        // Pulse 4 -> 4
        counter.setClock(false);
        counter.setClock(true);
        assertEquals(4, counter.getValue());
    }
}
