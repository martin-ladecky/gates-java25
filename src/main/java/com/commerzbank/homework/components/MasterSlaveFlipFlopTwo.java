package com.commerzbank.homework.components;

import com.commerzbank.homework.core.Signal;
import com.commerzbank.homework.core.Wire;
import com.commerzbank.homework.gates.NandGate;

/**
 * Implements a rising-edge triggered Master-Slave D-Type Flip-Flop.
 *
 * <p>A flip-flop is a fundamental memory circuit that stores a single bit of data.
 * This implementation is "edge-triggered," capturing the value of the data input
 * at the precise moment the clock transitions from low (false) to high (true).
 *
 * <p>This component is self-contained. Use the {@link #setData(boolean)} and
 * {@link #setClock(boolean)} methods to control its operation.
 *
 * <p>It works by chaining two {@link GatedDLatch} components:
 * <ol>
 *     <li><b>Master Latch:</b> Is open (transparent) when the clock is LOW. It continuously
 *     samples the data input during this phase.</li>
 *     <li><b>Slave Latch:</b> Is open (transparent) when the clock is HIGH. When the clock
 *     transitions from low to high, the master latch closes, holding the last-seen data value,
 *     and the slave latch opens, allowing this captured value to pass through to the final output.</li>
 * </ol>
 */
public class MasterSlaveFlipFlopTwo implements Signal {
    private final Wire dataWire = new Wire();
    private final Wire clockWire = new Wire();
    private final GatedDLatch master;
    private final GatedDLatch slave;

    /**
     * Constructs a rising-edge Master-Slave D-Flip-Flop.
     */
    public MasterSlaveFlipFlopTwo() {
        var invertedClock = new NandGate(clockWire, clockWire);

        // With the GatedDLatch now being stable, no workarounds are needed here.
        this.master = new GatedDLatch(invertedClock, dataWire);
        this.slave = new GatedDLatch(clockWire, master);
    }

    /**
     * Sets the data input signal for the next clock cycle.
     * @param state the boolean value for the data line.
     */
    public void setData(boolean state) {
        dataWire.setSignal(state);
    }

    /**
     * Controls the clock signal and triggers the flip-flop's state changes.
     * The flip-flop's output state is captured on the rising edge (false -> true).
     *
     * @param state the new boolean state of the clock.
     */
    public void setClock(boolean state) {
        clockWire.setSignal(state);
        // When the clock is LOW, the master latch is open and should be sampling the data.
        // We must explicitly run its stabilization to simulate this. This ensures that
        // when the clock goes high, the master has captured the correct value.
        if (!state) {
            master.stabilize();
        }
    }

    /**
     * Gets the stable output state (Q) of the flip-flop.
     * <p>This value represents the data that was captured on the last rising edge of the clock.
     *
     * @return the current latched boolean state.
     */
    @Override
    public boolean getState() {
        // The slave's getState will trigger its own stabilization, which in turn pulls
        // the stable state from the master latch.
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
