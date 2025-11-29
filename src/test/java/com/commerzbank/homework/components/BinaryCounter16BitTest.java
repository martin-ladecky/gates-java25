package com.commerzbank.homework.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryCounter16BitTest {

    private BinaryCounter16Bit counter;

    @BeforeEach
    void setUp() {
        counter = new BinaryCounter16Bit();
    }

    @Test
    void testInitialValueIsZero() {
        assertEquals(0, counter.getValue(), "Counter should initialize to 0");
    }

    @Test
    @Disabled("Can tak a long time and Junit does not stop it automatically everytime.")
    @Timeout(value = 15, unit = TimeUnit.SECONDS) // This test may be slow due to the number of iterations
    void testCountsUpTo65535AndRollsOver() {
        // Test the first few values to ensure it starts correctly
        for (int i = 1; i <= 65535; i++) {
            pulseClock();
            assertEquals(i, counter.getValue(), "After " + i + " pulses, counter should be at " + i);
            System.out.println(i);
        }

        // The next pulse (the 65536th pulse) should roll the counter over to 0.
        pulseClock();
        assertEquals(0, counter.getValue(), "Counter should roll over to 0 after reaching 65535");
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
