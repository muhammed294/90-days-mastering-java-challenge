package edu.haramaya.cosc3053.day16;

public class ModernJavaDemo {
    // Sealed Interface
    public sealed interface Shape permits Circle, Rectangle {}

    // Immutable Records
    public record Circle(double radius) implements Shape {}
    public record Rectangle(double length, double width) implements Shape {}

    // Pattern Matching Calculation
    public static double calculateArea(Shape shape) {
        return switch (shape) {
            case Circle c -> Math.PI * c.radius() * c.radius();
            case Rectangle r -> r.length() * r.width();
        };
    }

    public static void main(String[] args) {
        Shape circle = new Circle(5.0);
        System.out.printf("Circle Area: %.2f%n", calculateArea(circle));
    }
}