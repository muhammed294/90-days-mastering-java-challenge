package edu.haramaya.cosc3053.day14;

import java.util.List;
import java.util.function.Predicate;

public class LambdaDemo {
    // TODO 14.1: Use java.util.function.Predicate<T> to filter list of numbers
    public static void main(String[] args) {
        List<Integer> nums = List.of(12, 45, 68, 23, 90, 11);
        Predicate<Integer> isEven = n -> n % 2 == 0;

        System.out.print("Even Numbers: ");
        nums.stream().filter(isEven).forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
}