# Java Digital Logic Simulator

This project is a simple yet powerful digital logic circuit simulator built entirely in Java. It demonstrates how fundamental digital components like logic gates, latches, flip-flops, and counters can be modeled and simulated using object-oriented principles.

## Features

The simulator includes a variety of components, built from the ground up starting with the fundamental NAND gate.

### Core Components
- **`Signal`**: A functional interface representing any component that can carry a boolean signal.
- **`Wire`**: A mutable `Signal` that acts as a controllable input wire.
- **`Gate`**: An abstract base class for all logic gates.

### Logic Gates
All gates are ultimately constructed from the universal `NandGate`.
- `NandGate`
- `AndGate`
- `OrGate`
- `XorGate`

### Memory Units
- **`GatedDLatch`**: A level-triggered 1-bit memory latch.
- **`MasterSlaveFlipFlop`**: A falling-edge triggered D-type flip-flop.
- **`MasterSlaveFlipFlopTwo`**: A rising-edge triggered D-type flip-flop.

### Counters
- **`BinaryCounter1Bit` / `BinaryCounter1BitTwo`**: T-Flip-Flops that toggle on a clock edge, forming the basis of all counters.
- **`BinaryCounter4Bit`**: An asynchronous (ripple-carry) 4-bit counter.
- **`BinaryCounter4BitTwo`**, **`BinaryCounter8Bit`**, **`BinaryCounter16Bit`**: A family of synchronous counters that are more stable and predictable than their ripple-carry counterparts.

## Design Philosophy

- **Composition over Inheritance**: Complex components are built by composing simpler ones. For example, an `AndGate` is built from two `NandGate`s, and a `MasterSlaveFlipFlop` is built from two `GatedDLatch`es.
- **Simulation of Physical Concepts**: The design mimics real-world electronics. The `Signal` interface acts as a "wire," and components are "wired" together by passing `Signal` objects in their constructors.
- **Encapsulation**: More complex components like the synchronous counters hide their internal complexity behind a simple API (`setClock()`, `getValue()`).
- **Evolution of Design**: The project includes both simple asynchronous counters and more robust synchronous counters, demonstrating different approaches to solving the same problem.

## How to Run the Simulation

The project includes a `Main.java` file that runs a simulation of the 4-bit synchronous counter. To run it:
1. Compile the project.
2. Run the `main` method in the `com.commerzbank.homework.Main` class.

The output will show the counter incrementing from 0 to 15.

## How to Run Tests

The project includes a suite of JUnit 5 tests that verify the functionality of each component. To run the tests, use your IDE's test runner or execute the following Maven command from the project root:

```bash
mvn test
```
