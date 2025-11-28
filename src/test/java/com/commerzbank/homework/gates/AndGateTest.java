package com.commerzbank.homework.gates;

import com.commerzbank.homework.core.Signal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AndGateTest {

    @Test
    void testAndGate_ff() {
        Signal inputA = () -> false;
        Signal inputB = () -> false;
        AndGate andGate = new AndGate(inputA, inputB);
        assertFalse(andGate.getState());
    }

    @Test
    void testAndGate_ft() {
        Signal inputA = () -> false;
        Signal inputB = () -> true;
        AndGate andGate = new AndGate(inputA, inputB);
        assertFalse(andGate.getState());
    }

    @Test
    void testAndGate_tf() {
        Signal inputA = () -> true;
        Signal inputB = () -> false;
        AndGate andGate = new AndGate(inputA, inputB);
        assertFalse(andGate.getState());
    }

    @Test
    void testAndGate_tt() {
        Signal inputA = () -> true;
        Signal inputB = () -> true;
        AndGate andGate = new AndGate(inputA, inputB);
        assertTrue(andGate.getState());
    }
}
