package com.commerzbank.homework.components;

import com.commerzbank.homework.core.Signal;
import com.commerzbank.homework.gates.NandGate;

/**
 * Implements a rising-edge triggered Master-Slave D-Type Flip-Flop.
 *
 * <p>A flip-flop is a fundamental memory circuit that stores a single bit of data.
 * This specific implementation is "edge-triggered," meaning it only changes its output
 * state at a specific moment in the clock cycle.
 *
 * <p>This Master-Slave D-Flip-Flop captures the value of the {@code data} input at the
 * precise moment the {@code clock} signal transitions from low (false) to high (true).
 * The captured value is then held at the output until the next rising clock edge.
 *
 * <p>It works by chaining two {@link GatedDLatch} components:
 * <ol>
 *     <li><b>Master Latch:</b> Is open (transparent) when the clock is LOW. It continuously
 *     samples the data input during this phase.</li>
 *     <li><b>Slave Latch:</b> Is open (transparent) when the clock is HIGH. When the clock
 *     transitions from low to high, the master latch closes, holding the last-seen data value,
 *     and the slave latch opens, allowing this captured value to pass through to the final output.</li>
 * </ol>
 * This architecture prevents the "transparency" issue where the output could change
 * multiple times while the clock is high.
 */
public class MasterSlaveFlipFlopTwo implements Signal {
    private final GatedDLatch slave;

    /**
     * Constructs a rising-edge Master-Slave D-Flip-Flop.
     *
     * @param clock The clock signal. The flip-flop will change state on the rising edge of this signal.
     * @param data  The data input signal. Its value will be captured on the rising clock edge.
     */
    public MasterSlaveFlipFlopTwo(Signal clock, Signal data) {
        var invertedClock = new NandGate(clock, clock);

        // The master latch is open when the clock is low (since invertedClock will be high).
        var master = new GatedDLatch(invertedClock, data);
        // The slave latch is open when the clock is high, capturing the state from the master.
        this.slave = new GatedDLatch(clock, master);
    }

    /**
     * Gets the stable output state (Q) of the flip-flop.
     * <p>This value represents the data that was captured on the last rising edge of the clock.
     *
     * @return the current latched boolean state.
     */
    @Override
    public boolean getState() {
        return slave.getState();
    }

    /**
     * Gets the inverted stable output state (!Q) of the flip-flop.
     *
     * @return the inverted version of the current latched state.
     */
    public boolean getNotQState() {
        return slave.getNotQState();
    }
}
