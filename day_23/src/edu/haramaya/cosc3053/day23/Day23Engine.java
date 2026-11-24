package edu.haramaya.cosc3053.day23;

public class Day23Engine {

    // TODO 23.1: core component, a resource that can be closed automatically
    static class ManagedResource implements AutoCloseable {
        private final String name;
        private boolean closed = false;

        ManagedResource(String name) {
            this.name = name;
        }

        void use() {
            if (closed) {
                throw new IllegalStateException(name + " is already closed.");
            }
        }

        boolean isClosed() {
            return closed;
        }

        @Override
        public void close() {
            System.out.println(name + " closed");
            closed = true;
        }
    }

    public static void main(String[] args) {
        // TODO 23.1: Initialize core components for Try-With-Resources & AutoCloseable Resource Safety
        ManagedResource first = new ManagedResource("db-connection");
        ManagedResource second = new ManagedResource("file-handle");

        // TODO 23.2: Execute task logic and validate invariants
        try (first) {
            first.use();
        }
        if (!first.isClosed()) {
            throw new IllegalStateException("First resource was not closed.");
        }

        try (second) {
            throw new IllegalStateException("Simulated failure");
        } catch (IllegalStateException e) {
            // expected: the error was thrown on purpose
        }
        if (!second.isClosed()) {
            throw new IllegalStateException("Second resource was not closed after an error.");
        }

        // TODO 23.3: Output confirmation results
        System.out.println("=== DAY 23: TRY-WITH-RESOURCES & AUTOCLOSEABLE RESOURCE SAFETY ===");
        System.out.println("Execution Completed Successfully.");
    }
}