package edu.haramaya.cosc3053.day17;

public class SwitchDemo {
    public static void main(String[] args) {
        String day = "MONDAY";
        int typeCode = switch (day) {
            case "MONDAY", "FRIDAY" -> 1;
            case "SATURDAY", "SUNDAY" -> 2;
            default -> {
                System.out.println("Mid-week processing...");
                yield 0;
            }
        };
        System.out.println("Type Code: " + typeCode);
    }
}