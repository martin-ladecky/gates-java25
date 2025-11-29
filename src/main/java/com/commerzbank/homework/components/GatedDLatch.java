package com.commerzbank.homework.components;

import com.commerzbank.homework.core.Signal;
import com.commerzbank.homework.core.Wire;
import com.commerzbank.homework.gates.NandGate;

/**
 * Implements a Gated D-type Latch, a fundamental 1-bit memory circuit.
 *
 * <h3>What it does</h3>
 * <p>A Gated D-Latch has two primary modes of operation based on an {@code enable} signal:</p>
 * <ol>
 *     <li><b>Transparent Mode:</b> When {@code enable} is {@code true}, the latch is "open".
 *     The output 'Q' (from {@link #getState()}) continuously follows the {@code data} input.</li>
 *     <li><b>Memory Mode:</b> When {@code enable} is {@code false}, the latch is "closed".
 *     It ignores the {@code data} input and holds the last value it had at the moment
 *     {@code enable} became false.</li>
 * </ol>
 *
 * <h3>Why this design</h3>
 * <p>This component is built entirely from {@link NandGate}s, demonstrating a classic
 * digital logic construction. The core of the latch is an SR Latch (formed by the
 * cross-coupled {@code gate3} and {@code gate4}). The other gates form a "gating"
 * mechanism that controls when the SR Latch is allowed to change its state.</p>
 *
 * <p>The {@link #stabilize()} method is crucial for simulating the behavior of this
 * feedback circuit, ensuring the output is always a settled, stable value.</p>
 */
public class GatedDLatch implements Signal {
    private final Wire qOutput = new Wire();
    private final Wire notQOutput = new Wire();
    
    // Internal gates kept for structure, though simulation drives wires
    private final NandGate gate3;
    private final NandGate gate4;

    /**
     * Constructs a Gated D-Latch.
     *
     * <p>The constructor initializes the internal NAND gates and, crucially, sets the
     * latch to a known, stable initial state (Q=false, !Q=true). This prevents the
     * latch from starting in an invalid state where both outputs are the same.</p>
     *
     * @param enable The signal that controls the latch's mode (transparent or memory).
     * @param data The data signal to be latched.
     */
    public GatedDLatch(Signal enable, Signal data) {
        var gate1 = new NandGate(data, enable);
        
        var invertedData = new NandGate(data, data);
        var gate2 = new NandGate(invertedData, enable);

        // Feedback loop setup
        this.gate3 = new NandGate(gate1, notQOutput);
        this.gate4 = new NandGate(gate2, qOutput);

        // Initialize the latch to a known, stable state (Q=false, !Q=true).
        // The default state of Wires (both false) is an invalid state for a latch
        // and causes unpredictable behavior on startup.
        qOutput.setSignal(false);
        notQOutput.setSignal(true);
    }

    /**
     * Resolves the feedback loop for this latch to ensure a stable output.
     *
     * <p><b>Why it's needed:</b> Circuits with feedback loops can oscillate for a brief
     * period after an input changes before settling on a final value. This method
     * simulates that settling process by repeatedly evaluating the internal gates.
     * Calling this ensures that any value read from {@link #getState()} is the final,
     * stable result and not a transient, intermediate value.</p>
     */
    public void stabilize() {
        // In a simulation loop, we might iterate until convergence.
        // For this simple latch, 5 iterations is plenty.
        for (int i = 0; i < 5; i++) {
            boolean q = gate3.getState();
            boolean nq = gate4.getState();
            qOutput.setSignal(q);
            notQOutput.setSignal(nq);
        }
    }

    /**
     * Gets the primary output state (Q) of the latch.
     * This method implicitly stabilizes the latch before returning a value.
     * @return the current latched boolean state.
     */
    @Override
    public boolean getState() {
        stabilize();
        return qOutput.getState();
    }

    /**
     * Gets the inverted output state (!Q) of the latch.
     * This method implicitly stabilizes the latch before returning a value.
     * @return the inverted version of the current latched state.
     */
    public boolean getNotQState() {
        stabilize();
        return notQOutput.getState();
    }
}
