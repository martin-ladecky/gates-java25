package com.commerzbank.homework;

import com.commerzbank.homework.components.BinaryCounter4Bit;
import com.commerzbank.homework.components.BinaryCounter4BitTwo;

public class Main {
    
    // Record to hold state snapshot (Java 16+ feature, standard in 25)
    record CounterState(int step, int value) {
        @Override
        public String toString() {
            return "Step %d: Value = %d (Binary: %s)".formatted(
                step, value, Integer.toBinaryString(value));
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Starting Java 25 Binary Counter Simulation ---");
        
        var counter = new BinaryCounter4BitTwo();

        System.out.println(new CounterState(0, counter.getValue()));

        for (int i = 1; i <= 15; i++) {
            // Simulate Clock Pulse
            counter.setClock(false); // Falling edge
            counter.setClock(true);  // Rising edge

            System.out.println(new CounterState(i, counter.getValue()));
        }
    }
}
