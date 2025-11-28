package com.commerzbank.homework.gates;

import com.commerzbank.homework.core.Signal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NandGateTest {

    @Test
    void testNandGate_bothInputsFalse_outputTrue() {
        Signal inputA = () -> false;
        Signal inputB = () -> false;
        NandGate nandGate = new NandGate(inputA, inputB);
        assertTrue(nandGate.getState(), "NAND(false, false) should be true");
    }

    @Test
    void testNandGate_inputAFalseInputBTrue_outputTrue() {
        Signal inputA = () -> false;
        Signal inputB = () -> true;
        NandGate nandGate = new NandGate(inputA, inputB);
        assertTrue(nandGate.getState(), "NAND(false, true) should be true");
    }

    @Test
    void testNandGate_inputATrueInputBFalse_outputTrue() {
        Signal inputA = () -> true;
        Signal inputB = () -> false;
        NandGate nandGate = new NandGate(inputA, inputB);
        assertTrue(nandGate.getState(), "NAND(true, false) should be true");
    }

    @Test
    void testNandGate_bothInputsTrue_outputFalse() {
        Signal inputA = () -> true;
        Signal inputB = () -> true;
        NandGate nandGate = new NandGate(inputA, inputB);
        assertFalse(nandGate.getState(), "NAND(true, true) should be false");
    }
}
