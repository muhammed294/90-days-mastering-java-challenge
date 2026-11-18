package edu.haramaya.cosc3053.day13;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("S101", 95);
        scores.put("S102", 88);

        // TODO 13.1: Demonstrate O(1) key retrieval
        System.out.println("S101 Score: " + scores.get("S101"));

        // TODO 13.2: Explain treeification threshold (8 bucket collisions convert linked list to Red-Black tree)
        // A HashMap stores colliding keys in the same bucket as a linked list, and searching it is O(n).
        // When a bucket reaches 8 entries (and the table has at least 64 buckets), Java converts
        // that linked list into a Red-Black tree, so searching becomes O(log n) in the worst case.
        // If the bucket shrinks to 6 entries or fewer, it is converted back to a linked list.
    }
}