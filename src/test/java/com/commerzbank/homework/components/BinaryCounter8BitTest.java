package com.commerzbank.homework.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryCounter8BitTest {

    private BinaryCounter8Bit counter;

    @BeforeEach
    void setUp() {
        counter = new BinaryCounter8Bit();
    }

    @Test
    void testInitialValueIsZero() {
        assertEquals(0, counter.getValue(), "Counter should initialize to 0");
    }

    @Test
    void testCountsUpTo255AndRollsOver() {
        // The counter starts at 0. We need 255 pulses to get it to 255.
        for (int i = 1; i <= 255; i++) {
            pulseClock();
            assertEquals(i, counter.getValue(), "After " + i + " pulses, counter should be at " + i);
        }

        // At this point, the counter's value is 255.
        assertEquals(255, counter.getValue(), "Counter should be at 255 after 255 pulses");

        // The next pulse should roll the counter over to 0.
        pulseClock();
        assertEquals(0, counter.getValue(), "Counter should roll over to 0 after reaching 255");
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

    private void pulseClock() {
        counter.setClock(false);
        counter.setClock(true);
    }
}
