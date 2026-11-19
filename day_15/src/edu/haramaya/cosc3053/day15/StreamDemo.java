package edu.haramaya.cosc3053.day15;

import java.util.List;
import java.util.stream.Collectors;

public class StreamDemo {
    public record Student(String name, String dept, double gpa) {}

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

        List<String> names = roster.stream()
                .map(Student::name)
                .sorted()
                .collect(Collectors.toList());

        System.out.printf("CS Average GPA: %.2f%n", avgGpa);
        System.out.println(names);

    }
}