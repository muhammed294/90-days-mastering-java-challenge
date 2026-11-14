package edu.haramaya.cosc3053.day13;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {

    // Same steps HashMap uses to choose a bucket (for learning only)
    private static int indexFor(Object key, int capacity) {
        int h = key.hashCode();
        h = h ^ (h >>> 16);              // spread the high bits
        return (capacity - 1) & h;       // capacity must be a power of 2
    }

    // A key class WITHOUT equals/hashCode (breaks the contract on purpose)
    static class BadKey {
        final int x, y;
        BadKey(int x, int y) { this.x = x; this.y = y; }
    }

    // A record writes equals() and hashCode() for you
    record GoodKey(int x, int y) {}

    private static void runDemo() {
        System.out.println("--- 13.1 Index calculation ---");
        System.out.println("S101 hash=" + "S101".hashCode() + " index=" + indexFor("S101", 16));
        System.out.println("Aa   hash=" + "Aa".hashCode() + " index=" + indexFor("Aa", 16));
        System.out.println("BB   hash=" + "BB".hashCode() + " index=" + indexFor("BB", 16));

        System.out.println("--- Collision: same bucket, different keys ---");
        Map<String, Integer> map = new HashMap<>();
        map.put("Aa", 1);
        map.put("BB", 2);
        System.out.println("Map: " + map);
        System.out.println("get(Aa)=" + map.get("Aa") + "  get(BB)=" + map.get("BB"));

        System.out.println("--- equals/hashCode contract ---");
        Map<BadKey, String> bad = new HashMap<>();
        bad.put(new BadKey(1, 2), "A");
        System.out.println("Bad key lookup   : " + bad.get(new BadKey(1, 2)));

        Map<GoodKey, String> good = new HashMap<>();
        good.put(new GoodKey(1, 2), "A");
        System.out.println("Record key lookup: " + good.get(new GoodKey(1, 2)));

        System.out.println("--- Other behaviors ---");
        Map<String, Integer> scores = new HashMap<>();
        scores.put("S101", 95);
        System.out.println("put same key again returns old value: " + scores.put("S101", 99));
        System.out.println("getOrDefault(S999, 0) = " + scores.getOrDefault("S999", 0));
        System.out.println("get(S999) = " + scores.get("S999"));
        System.out.println("containsKey(S101) = " + scores.containsKey("S101"));
    }

    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("S101", 95);
        scores.put("S102", 88);

        // TODO 13.1: Demonstrate O(1) key retrieval
        System.out.println("S101 Score: " + scores.get("S101"));

        // TODO 13.2: Treeification threshold
        // A bucket chain longer than 8 entries is converted from a linked list into a
        // Red-Black tree (only if the table capacity is >= 64; otherwise the table resizes).
        // This keeps lookup at O(log n) in the worst case instead of O(n).

        if (args.length > 0 && args[0].equals("demo")) {
            runDemo();
        }
    }
}