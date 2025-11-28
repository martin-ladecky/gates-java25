package com.commerzbank.homework.core;

/**
 * Functional interface representing any component that carries a digital signal.
 */
@FunctionalInterface
public interface Signal {
    boolean getState();
}
