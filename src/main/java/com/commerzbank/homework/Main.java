package com.commerzbank.homework;

import com.commerzbank.homework.components.BinaryCounter4Bit;

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
        
        var counter = new BinaryCounter4Bit();

        System.out.println(new CounterState(0, counter.getValue()));

        for (int i = 1; i <= 15; i++) {
            // Simulate Clock Pulse
            counter.setClock(true);  // Rising edge
            counter.setClock(false); // Falling edge (Flip-Flops trigger here)
            
            System.out.println(new CounterState(i, counter.getValue()));
        }
    }
}
