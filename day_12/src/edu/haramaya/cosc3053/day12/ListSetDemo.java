package edu.haramaya.cosc3053.day12;

import java.util.*;

public class ListSetDemo {

    // TODO 12.1: ArrayList vs LinkedList random access
    private static void compareLists() {
        System.out.println("--- 12.1 ArrayList vs LinkedList ---");
        int size = 100_000;
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long start = System.nanoTime();
        long sum1 = 0;
        for (int i = 0; i < size; i++) sum1 += arrayList.get(i);      // O(1) each
        long arrayTime = System.nanoTime() - start;

        start = System.nanoTime();
        long sum2 = 0;
        for (int i = 0; i < size; i++) sum2 += linkedList.get(i);     // O(n) each
        long linkedTime = System.nanoTime() - start;

        System.out.println("Sums equal: " + (sum1 == sum2));
        System.out.println("ArrayList  get(i) loop: " + arrayTime / 1_000_000 + " ms");
        System.out.println("LinkedList get(i) loop: " + linkedTime / 1_000_000 + " ms");
    }

    // TODO 12.2: HashSet uniqueness
    private static void demoHashSet() {
        System.out.println("--- 12.2 HashSet uniqueness ---");
        Set<String> names = new HashSet<>();
        System.out.println("add Abebe : " + names.add("Abebe"));
        System.out.println("add Kebede: " + names.add("Kebede"));
        System.out.println("add Abebe : " + names.add("Abebe"));   // duplicate -> false
        System.out.println("Size: " + names.size() + "  Set: " + names);
        System.out.println("contains Almaz? " + names.contains("Almaz"));
    }

    // TODO 12.3: Natural sorting with TreeSet
    private static void demoTreeSet() {
        System.out.println("--- 12.3 TreeSet natural sorting ---");
        List<String> source = List.of("Kebede", "Abebe", "Almaz", "Abebe");
        System.out.println("LinkedHashSet (insertion order): " + new LinkedHashSet<>(source));
        System.out.println("TreeSet (sorted A-Z)           : " + new TreeSet<>(source));
        System.out.println("TreeSet of numbers             : " + new TreeSet<>(List.of(42, 7, 19, 7, 3)));
    }

    public static void main(String[] args) {
        Set<String> uniqueNames = new HashSet<>(List.of("Abebe", "Kebede", "Abebe", "Almaz"));
        System.out.println("HashSet Unique Elements: " + uniqueNames);

        if (args.length > 0 && args[0].equals("demo")) {
            compareLists();
            demoHashSet();
            demoTreeSet();
        }
    }
}