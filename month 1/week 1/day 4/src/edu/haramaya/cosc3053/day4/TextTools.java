package edu.haramaya.cosc3053.day4;

import java.util.*;

public class TextTools {

    // =========================================================================
    // 1. SIMPLE CHALLENGE (EASY): Clean String Palindrome Verification
    // =========================================================================
    public static boolean isPalindrome(String input) {
        if (input == null) return false;

        // Sanitize: Keep alphanumeric characters and convert to lower-case
        StringBuilder clean = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                clean.append(Character.toLowerCase(ch));
            }
        }

        // Two-pointer check on sanitized StringBuilder
        int left = 0, right = clean.length() - 1;
        while (left < right) {
            if (clean.charAt(left) != clean.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // =========================================================================
    // 2. MEDIUM CHALLENGE (MEDIUM): Anagram Validation in O(n) Time
    // =========================================================================
    public static boolean isAnagram(String str1, String str2) {
        if (str1 == null || str2 == null) return false;

        // Extract raw letters ignoring case and non-alphabetics
        String s1 = str1.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String s2 = str2.replaceAll("[^a-zA-Z]", "").toLowerCase();

        if (s1.length() != s2.length()) return false;

        int[] frequency = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            frequency[s1.charAt(i) - 'a']++;
            frequency[s2.charAt(i) - 'a']--;
        }

        for (int count : frequency) {
            if (count != 0) return false;
        }
        return true;
    }

    // =========================================================================
    // 3. DIFFICULT CHALLENGE (HARD): Comprehensive Document Analysis Engine
    // =========================================================================
    public record TextAnalysisReport(
            int totalWords,
            int uniqueWords,
            double avgWordLength,
            String longestWord,
            int sentenceCount,
            int palindromeWordCount,
            List<String> palindromeWords
    ) {}

    public static TextAnalysisReport analyzeText(String document) {
        if (document == null || document.isBlank()) {
            return new TextAnalysisReport(0, 0, 0.0, "", 0, 0, Collections.emptyList());
        }

        // Count sentences using punctuation regex
        String[] sentences = document.split("[.!?]+");
        int sentenceCount = (sentences.length == 1 && sentences[0].isBlank()) ? 0 : sentences.length;

        // Tokenize words using non-alphanumeric delimiter
        String[] tokens = document.split("[^a-zA-Z0-9]+");

        int totalWords = 0;
        int totalCharCount = 0;
        String longestWord = "";
        Set<String> uniqueWordsSet = new HashSet<>();
        List<String> palindromesFound = new ArrayList<>();

        for (String token : tokens) {
            if (token.isBlank()) continue;

            totalWords++;
            totalCharCount += token.length();
            String lowerToken = token.toLowerCase();
            uniqueWordsSet.add(lowerToken);

            if (token.length() > longestWord.length()) {
                longestWord = token;
            }

            // Check if single word is a palindrome (length > 1)
            if (token.length() > 1 && isPalindrome(token)) {
                palindromesFound.add(lowerToken);
            }
        }

        double avgLength = totalWords > 0 ? (double) totalCharCount / totalWords : 0.0;

        return new TextAnalysisReport(
                totalWords,
                uniqueWordsSet.size(),
                avgLength,
                longestWord,
                sentenceCount,
                palindromesFound.size(),
                palindromesFound
        );
    }

    // =========================================================================
    // TEST RUNNER
    // =========================================================================
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("  HARAMAYA CS: DAY 4 TEXTTOOLS CHALLENGE ENGINE  ");
        System.out.println("=================================================\n");

        // --- TEST 1: SIMPLE CHALLENGE ---
        System.out.println("--- 1. SIMPLE CHALLENGE: PALINDROME CHECK ---");
        String[] testPalindromes = {
                "A man, a plan, a canal: Panama!",
                "Race a car",
                "Was it a car or a cat I saw?",
                "Madam, I'm Adam"
        };
        for (String s : testPalindromes) {
            System.out.printf("  '%s'%n    -> Is Palindrome? %s%n", s, isPalindrome(s) ? "YES ✅" : "NO ❌");
        }

        // --- TEST 2: MEDIUM CHALLENGE ---
        System.out.println("\n--- 2. MEDIUM CHALLENGE: ANAGRAM VALIDATION ---");
        String[][] anagramPairs = {
                {"Listen", "Silent"},
                {"Conversation", "Voices rant on"},
                {"Haramaya", "Java Code"}
        };
        for (String[] pair : anagramPairs) {
            boolean result = isAnagram(pair[0], pair[1]);
            System.out.printf("  '%s' vs '%s'%n    -> Are Anagrams? %s%n", pair[0], pair[1], result ? "YES ✅" : "NO ❌");
        }

        // --- TEST 3: DIFFICULT CHALLENGE ---
        System.out.println("\n--- 3. DIFFICULT CHALLENGE: DOCUMENT ANALYTICS ENGINE ---");
        String sampleDocument =
                "Madam, I'm Adam. Welcome to the Haramaya University Java lab! " +
                        "A man, a plan, a canal: Panama! Racecar drivers refer to civic radar for speed.";

        TextAnalysisReport report = analyzeText(sampleDocument);

        System.out.println("Document Sample: \"" + sampleDocument + "\"");
        System.out.println("-------------------------------------------------");
        System.out.println("  Total Word Count      : " + report.totalWords());
        System.out.println("  Unique Word Count     : " + report.uniqueWords());
        System.out.printf("  Average Word Length   : %.2f chars%n", report.avgWordLength());
        System.out.println("  Longest Word          : \"" + report.longestWord() + "\"");
        System.out.println("  Sentence Count        : " + report.sentenceCount());
        System.out.println("  Palindrome Word Count : " + report.palindromeWordCount());
        System.out.println("  Palindrome Words List : " + report.palindromeWords());
        System.out.println("=================================================");
    }
}