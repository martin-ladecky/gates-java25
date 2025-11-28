package com.commerzbank.homework.core;

/**
 * A mutable wire connection.
 */
public class Wire implements Signal {
    private boolean state = false;

    public void setSignal(boolean newState) {
        this.state = newState;
    }

    @Override
    public boolean getState() {
        return state;
    }
}
