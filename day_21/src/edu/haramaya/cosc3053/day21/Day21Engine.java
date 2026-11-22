package edu.haramaya.cosc3053.day21;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day21Engine {
    public static void main(String[] args) throws IOException {
        // TODO 21.1: Initialize core components for Character Streams — BufferedReader & BufferedWriter
        Path file = Files.createTempFile("day21", ".txt");
        List<String> original = List.of("Haramaya University", "CoSc3053 Java", "Day 21");

        // TODO 21.2: Execute task logic and validate invariants
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile(), StandardCharsets.UTF_8))) {
            for (String line : original) {
                writer.write(line);
                writer.newLine();
            }
        }

        List<String> readBack = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                readBack.add(line);
            }
        }

        if (!original.equals(readBack)) {
            throw new IllegalStateException("Lines read do not match lines written.");
        }
        Files.delete(file);

        // TODO 21.3: Output confirmation results
        System.out.println("=== DAY 21: CHARACTER STREAMS — BUFFEREDREADER & BUFFEREDWRITER ===");
        System.out.println("Execution Completed Successfully.");
        System.out.println(readBack);
    }
}