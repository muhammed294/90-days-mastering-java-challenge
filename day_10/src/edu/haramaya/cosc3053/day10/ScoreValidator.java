package edu.haramaya.cosc3053.day10;

// TODO 10.1: Define InvalidScoreException extending Exception
class InvalidScoreException extends Exception {
    public InvalidScoreException(String msg) { super(msg); }
}

public class ScoreValidator {
    // TODO 10.2: Implement validate(double score) throws InvalidScoreException
    public static double validate(double score) throws InvalidScoreException {
        if (score < 0.0 || score > 100.0) {
            throw new InvalidScoreException("Score out of bounds: " + score);
        }
        return score;
    }

    public static void main(String[] args) {
        try {
            validate(105.0);
        } catch (InvalidScoreException e) {
            System.out.println("Caught Expected Exception: " + e.getMessage());
        }
    }
}