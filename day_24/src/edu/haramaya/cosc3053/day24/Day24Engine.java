package edu.haramaya.cosc3053.day24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Day24Engine {

    // TODO 24.1: core components, a record and a parser for one CSV line
    record Student(String name, String dept, double gpa) {}

    static Student parse(String line) {
        String[] parts = line.split(",");
        return new Student(parts[0], parts[1], Double.parseDouble(parts[2]));
    }

    public static void main(String[] args) throws IOException {
        // TODO 24.1: Initialize core components for File I/O Synthesis — Streaming CSV Processor Engine
        Path file = Files.createTempFile("day24", ".csv");
        Files.write(file, List.of(
                "name,dept,gpa",
                "Abebe,CS,3.85",
                "Kebede,IS,3.20",
                "Almaz,CS,3.90"));

        // TODO 24.2: Execute task logic and validate invariants
        List<String> csNames;
        try (Stream<String> lines = Files.lines(file)) {
            csNames = lines
                    .skip(1)
                    .map(Day24Engine::parse)
                    .filter(s -> s.dept().equals("CS"))
                    .map(s -> s.name().toUpperCase())
                    .toList();
        }

        if (!csNames.equals(List.of("ABEBE", "ALMAZ"))) {
            throw new IllegalStateException("Unexpected result: " + csNames);
        }
        Files.delete(file);

        // TODO 24.3: Output confirmation results
        System.out.println("=== DAY 24: FILE I/O SYNTHESIS — STREAMING CSV PROCESSOR ENGINE ===");
        System.out.println(csNames);
        System.out.println("Execution Completed Successfully.");

    }
}