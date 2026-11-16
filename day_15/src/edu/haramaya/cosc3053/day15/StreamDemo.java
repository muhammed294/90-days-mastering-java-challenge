package edu.haramaya.cosc3053.day15;

import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

    public record Student(String name, String dept, double gpa) {}

    private static void runDemo(List<Student> roster) {
        System.out.println("--- Filter + map ---");
        List<String> csNames = roster.stream()
                .filter(s -> s.dept().equals("CS"))
                .map(Student::name)
                .collect(Collectors.toList());
        System.out.println("CS names: " + csNames);

        System.out.println("--- Sorted ---");
        List<String> byGpa = roster.stream()
                .sorted(Comparator.comparingDouble(Student::gpa).reversed())
                .map(Student::name)
                .collect(Collectors.toList());
        System.out.println("By GPA (high to low): " + byGpa);

        List<String> byName = roster.stream()
                .sorted(Comparator.comparing(Student::name))
                .map(Student::name)
                .collect(Collectors.toList());
        System.out.println("By name (A-Z): " + byName);

        System.out.println("--- Aggregation ---");
        long csCount = roster.stream().filter(s -> s.dept().equals("CS")).count();
        System.out.println("Count of CS: " + csCount);

        double total = roster.stream().mapToDouble(Student::gpa).sum();
        System.out.printf("Total GPA: %.2f%n", total);

        String top = roster.stream()
                .max(Comparator.comparingDouble(Student::gpa))
                .map(Student::name)
                .orElse("none");
        System.out.println("Top student: " + top);

        System.out.println("Any GPA > 3.8? " + roster.stream().anyMatch(s -> s.gpa() > 3.8));
        System.out.println("All GPA > 3.5? " + roster.stream().allMatch(s -> s.gpa() > 3.5));

        double emptyAvg = roster.stream()
                .filter(s -> s.dept().equals("EE"))      // nobody matches
                .mapToDouble(Student::gpa)
                .average()
                .orElse(0.0);
        System.out.println("Empty average (no EE students): " + emptyAvg);

        System.out.println("--- collect ---");
        String joined = roster.stream().map(Student::name).collect(Collectors.joining(", "));
        System.out.println("Joined names: " + joined);

        System.out.println("Count per dept: " + roster.stream()
                .collect(Collectors.groupingBy(Student::dept, TreeMap::new, Collectors.counting())));
        System.out.println("Average per dept: " + roster.stream()
                .collect(Collectors.groupingBy(Student::dept, TreeMap::new,
                        Collectors.averagingDouble(Student::gpa))));

        System.out.println("--- Lazy evaluation ---");
        Stream<Integer> lazy = Stream.of(1, 2, 3)
                .peek(n -> System.out.println("processing " + n));
        System.out.println("pipeline built, nothing printed yet");
        lazy.forEach(n -> System.out.println("done " + n));
    }

    public static void main(String[] args) {
        List<Student> roster = List.of(
                new Student("Abebe", "CS", 3.85),
                new Student("Kebede", "IS", 3.20),
                new Student("Almaz", "CS", 3.90)
        );

        // TODO 15.1: Calculate average GPA of CS department students using Stream API
        double avgGpa = roster.stream()
                .filter(s -> s.dept().equals("CS"))
                .mapToDouble(Student::gpa)
                .average()
                .orElse(0.0);

        System.out.printf("CS Average GPA: %.2f%n", avgGpa);

        if (args.length > 0 && args[0].equals("demo")) {
            runDemo(roster);
        }
    }
}