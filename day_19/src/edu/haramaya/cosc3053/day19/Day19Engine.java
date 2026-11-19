package edu.haramaya.cosc3053.day19;

import java.nio.file.Path;

public class Day19Engine {
    public static void main(String[] args) {
        // TODO 19.1: Initialize core components for NIO.2 Path & Files Architecture
        Path path = Path.of("data", "notes.txt");

        // TODO 19.2: Execute task logic and validate invariants
        Path normalized = path.normalize();
        boolean isValid = !normalized.isAbsolute() && normalized.getFileName() != null;
        if (!isValid) {
            throw new IllegalStateException("Invalid path: " + normalized);
        }

        // TODO 19.3: Output confirmation results
        System.out.println("File name : " + normalized.getFileName());
        System.out.println("Parent    : " + normalized.getParent());
        System.out.println("Exists?   : " + java.nio.file.Files.exists(normalized));
        System.out.println("=== DAY 19: NIO.2 PATH & FILES ARCHITECTURE ===");
        System.out.println("Execution Completed Successfully.");
    }
}