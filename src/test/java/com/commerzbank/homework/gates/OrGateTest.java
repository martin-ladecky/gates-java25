package com.commerzbank.homework.gates;

import com.commerzbank.homework.core.Signal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrGateTest {

    @Test
    void testOrGate_ff() {
        Signal inputA = () -> false;
        Signal inputB = () -> false;
        OrGate orGate = new OrGate(inputA, inputB);
        assertFalse(orGate.getState());
    }

    @Test
    void testOrGate_ft() {
        Signal inputA = () -> false;
        Signal inputB = () -> true;
        OrGate orGate = new OrGate(inputA, inputB);
        assertTrue(orGate.getState());
    }

    @Test
    void testOrGate_tf() {
        Signal inputA = () -> true;
        Signal inputB = () -> false;
        OrGate orGate = new OrGate(inputA, inputB);
        assertTrue(orGate.getState());
    }

    @Test
    void testOrGate_tt() {
        Signal inputA = () -> true;
        Signal inputB = () -> true;
        OrGate orGate = new OrGate(inputA, inputB);
        assertTrue(orGate.getState());
    }
}
