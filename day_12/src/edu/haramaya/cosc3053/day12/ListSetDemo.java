package edu.haramaya.cosc3053.day12;

import java.util.*;

public class ListSetDemo {
    public static void main(String[] args) {
        // TODO 12.1: ArrayList and LinkedList, O(1) vs O(n) positional lookup
        List<String> arrayList = new ArrayList<>(List.of("Abebe", "Kebede", "Almaz"));
        List<String> linkedList = new LinkedList<>(List.of("Abebe", "Kebede", "Almaz"));
        String fromArray = arrayList.get(2);   // O(1): direct jump
        String fromLinked = linkedList.get(2); // O(n): walks the nodes

        // TODO 12.2: duplicates added to a HashSet are filtered out
        Set<String> uniqueNames = new HashSet<>(List.of("Abebe", "Kebede", "Abebe", "Almaz"));

        // TODO 12.3: natural sorting using TreeSet
        Set<String> sortedNames = new TreeSet<>(uniqueNames);

        System.out.println("HashSet Unique Elements: " + uniqueNames);
    }
}
