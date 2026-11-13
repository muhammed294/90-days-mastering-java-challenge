package edu.haramaya.cosc3053.day10;

// TODO 10.1: Define InvalidScoreException extending Exception
class InvalidScoreException extends Exception {
    public InvalidScoreException(String msg) { super(msg); }
}

public class ScoreValidator {

    // TODO 10.2: validate(double score) throws InvalidScoreException
    public static double validate(double score) throws InvalidScoreException {
        // !(score >= 0.0 && score <= 100.0) also rejects NaN
        if (!(score >= 0.0 && score <= 100.0)) {
            throw new InvalidScoreException("Score out of bounds: " + score);
        }
        return score;
    }

    // Extra demo: run it by passing the program argument "demo"
    private static void runDemo() {
        System.out.println("--- Demo ---");
        double[] tests = {85.5, 0.0, 100.0, -1.0, Double.NaN};
        for (double t : tests) {
            try {
                double ok = validate(t);
                System.out.println("Valid score: " + ok);
            } catch (InvalidScoreException e) {
                System.out.println("Invalid: " + e.getMessage());
            } finally {
                System.out.println("  (checked " + t + ")");
            }
        }
    }

    public static void main(String[] args) {
        try {
            validate(105.0);
        } catch (InvalidScoreException e) {
            System.out.println("Caught Expected Exception: " + e.getMessage());
        }

        if (args.length > 0 && args[0].equals("demo")) {
            runDemo();
        }
    }
}