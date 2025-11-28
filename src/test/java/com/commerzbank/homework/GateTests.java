package com.commerzbank.homework;

import com.commerzbank.homework.core.Wire;
import com.commerzbank.homework.gates.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GateTests {
    private final Wire inA = new Wire();
    private final Wire inB = new Wire();

    @Test
    void testNandGate() {
        var gate = new NandGate(inA, inB);
        assertGateLogic(gate, true, true, true, false);
    }

    @Test
    void testXorGate() {
        var gate = new XorGate(inA, inB);
        assertGateLogic(gate, false, true, true, false);
    }
    
    // Helper method using Switch Expressions/compact logic
    private void assertGateLogic(com.commerzbank.homework.core.Signal gate, 
                                 boolean expect00, boolean expect01, 
                                 boolean expect10, boolean expect11) {
        
        inA.setSignal(false); inB.setSignal(false);
        assertEquals(expect00, gate.getState(), "Failed at 0,0");

        inA.setSignal(false); inB.setSignal(true);
        assertEquals(expect01, gate.getState(), "Failed at 0,1");

        inA.setSignal(true); inB.setSignal(false);
        assertEquals(expect10, gate.getState(), "Failed at 1,0");

        inA.setSignal(true); inB.setSignal(true);
        assertEquals(expect11, gate.getState(), "Failed at 1,1");
    }
}
