package edu.haramaya.cosc3053.day2;
import java.util.*;

public class GradeCalc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double mark = -1.0;

        System.out.println("=========================================");
        System.out.println(" HARAMAYA CS: GRADE CALCULATOR ENGINE ");
        System.out.println("=========================================");
        // 1. Input Validation Loop (Reprompts if mark < 0 or mark > 100)
        do {
            System.out.print("Enter exam mark [0.0 - 100.0]: ");
            if (scanner.hasNextDouble()) {
                mark = scanner.nextDouble();
                if (mark < 0.0 || mark > 100.0) {
                    System.out.println("❌ Invalid Score! Mark must be between 0.0 and 100.0.\n");
                }
            } else {
                System.out.println("❌ Invalid Input! Please enter a valid numeric value.\n");
                scanner.next(); // Clear non-numeric token
            }
        } while (mark < 0.0 || mark > 100.0);

        // 2. Multi-Way Selection Ladder (if-else-if)
        char letterGrade;
        if (mark >= 90.0) {
            letterGrade = 'A';
        } else if (mark >= 80.0) {
            letterGrade = 'B';
        } else if (mark >= 70.0) {
            letterGrade = 'C';
        } else if (mark >= 60.0) {
            letterGrade = 'D';
        } else {
            letterGrade = 'F';
        }
        // 3. Modern Switch Expression with Arrow Syntax (JDK 14+)
        String remarks = switch (letterGrade) {
            case 'A' -> "Distinction! Outstanding academic performance.";
            case 'B' -> "Very Good! Upper division pass.";
            case 'C' -> "Satisfactory Pass.";
            case 'D' -> "Conditional Pass. Support recommended.";
            case 'F' -> "Unsatisfactory. Academic re-evaluation required.";
            default -> "Unknown Grade State.";
        };

        // 4. Pass / Fail Decision Logic
        boolean isPassed = (letterGrade != 'F');
        // 5. Output Results Summary
        System.out.println("\n-----------------------------------------");
        System.out.println(" FINAL EVALUATION RESULT ");
        System.out.println("-----------------------------------------");
        System.out.printf(" Exam Mark Score : %.2f / 100.00%n", mark);
        System.out.println(" Letter Grade : " + letterGrade);
        System.out.println(" Academic Status : " + (isPassed ? "PASSED ✅" : "FAILED ❌"));
        System.out.println(" Instructor Remark: " + remarks);
        System.out.println("=========================================");
        scanner.close();

    }
    }
