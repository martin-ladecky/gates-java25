package com.commerzbank.homework.gates;

import com.commerzbank.homework.core.Signal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XorGateTest {

    @Test
    void testXorGate_ff() {
        Signal inputA = () -> false;
        Signal inputB = () -> false;
        XorGate xorGate = new XorGate(inputA, inputB);
        assertFalse(xorGate.getState());
    }

    @Test
    void testXorGate_ft() {
        Signal inputA = () -> false;
        Signal inputB = () -> true;
        XorGate xorGate = new XorGate(inputA, inputB);
        assertTrue(xorGate.getState());
    }

    @Test
    void testXorGate_tf() {
        Signal inputA = () -> true;
        Signal inputB = () -> false;
        XorGate xorGate = new XorGate(inputA, inputB);
        assertTrue(xorGate.getState());
    }

    @Test
    void testXorGate_tt() {
        Signal inputA = () -> true;
        Signal inputB = () -> true;
        XorGate xorGate = new XorGate(inputA, inputB);
        assertFalse(xorGate.getState());
    }
}
