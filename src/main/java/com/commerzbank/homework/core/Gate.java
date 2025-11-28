package com.commerzbank.homework.core;

/**
 * Abstract base for logic gates.
 */
public abstract class Gate implements Signal {
    protected abstract void evaluate();
}
