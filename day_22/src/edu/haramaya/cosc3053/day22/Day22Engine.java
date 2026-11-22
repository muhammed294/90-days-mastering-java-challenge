package edu.haramaya.cosc3053.day22;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day22Engine {

    // TODO 22.1: core component, a serializable class with a transient field
    static class User implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String name;
        private transient String password; // not saved to the file

        User(String name, String password) {
            this.name = name;
            this.password = password;
        }

        String getName() { return name; }
        String getPassword() { return password; }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO 22.1: Initialize core components for Object Serialization & Transience
        Path file = Files.createTempFile("day22", ".ser");
        User original = new User("Abebe", "secret123");

        // TODO 22.2: Execute task logic and validate invariants
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file.toFile()))) {
            out.writeObject(original);
        }

        User restored;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file.toFile()))) {
            restored = (User) in.readObject();
        }

        if (!original.getName().equals(restored.getName()) || restored.getPassword() != null) {
            throw new IllegalStateException("Serialization invariants violated.");
        }
        Files.delete(file);

        // TODO 22.3: Output confirmation results
        System.out.println("=== DAY 22: OBJECT SERIALIZATION & TRANSIENCE ===");
        System.out.println("Execution Completed Successfully.");
        System.out.println("Name     : " + restored.getName());
        System.out.println("Password : " + restored.getPassword());
    }
}