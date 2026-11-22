package edu.haramaya.cosc3053.day20;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Day20Engine {
    public static void main(String[] args) throws IOException {
        // TODO 20.1: Initialize core components for Byte Streams — FileInputStream & FileOutputStream
        Path file = Files.createTempFile("day20", ".bin");
        byte[] original = "Haramaya".getBytes(StandardCharsets.UTF_8);

        // TODO 20.2: Execute task logic and validate invariants
        try (FileOutputStream out = new FileOutputStream(file.toFile())) {
            out.write(original);
        }

        byte[] readBack;
        try (FileInputStream in = new FileInputStream(file.toFile())) {
            readBack = in.readAllBytes();
        }

        if (!Arrays.equals(original, readBack)) {
            throw new IllegalStateException("Bytes read do not match bytes written.");
        }
        Files.delete(file);

        // TODO 20.3: Output confirmation results
        System.out.println("=== DAY 20: BYTE STREAMS — FILEINPUTSTREAM & FILEOUTPUTSTREAM ===");
        System.out.println("Execution Completed Successfully.");
        System.out.println(Arrays.toString(readBack));
    }
}