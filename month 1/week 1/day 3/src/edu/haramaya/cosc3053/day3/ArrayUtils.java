package edu.haramaya.cosc3053.day3;

import java.util.Arrays;

public class ArrayUtils {

    // 1. Find Minimum Element in O(n) time
    public static int findMin(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Array cannot be empty or null");
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
        }
        return min;
    }

    // 2. Find Maximum Element in O(n) time
    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Array cannot be empty or null");
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }

    // 3. In-Place Array Reversal O(n) time, O(1) space
    public static void reverse(int[] arr) {
        if (arr == null) return;
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    // 4. Binary Search O(log n) time (Requires Pre-Sorted Array)
    public static int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2; // Prevents overflow
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1; // Target not found
    }

    // Test Runner Method
    public static void executeRun(String runName, int[] inputArr, int searchTarget) {
        System.out.println("=========================================");
        System.out.println(" RUN: " + runName);
        System.out.println("=========================================");
        System.out.println(" Original Array : " + Arrays.toString(inputArr));
        System.out.println(" Min Element    : " + findMin(inputArr));
        System.out.println(" Max Element    : " + findMax(inputArr));

        int[] reversed = inputArr.clone();
        reverse(reversed);
        System.out.println(" Reversed Array : " + Arrays.toString(reversed));

        int[] sorted = inputArr.clone();
        Arrays.sort(sorted);
        int idx = binarySearch(sorted, searchTarget);
        System.out.println(" Sorted Array   : " + Arrays.toString(sorted));
        System.out.println(" Binary Search ('" + searchTarget + "'): Index " + idx + (idx != -1 ? " [FOUND]" : " [NOT FOUND]"));
        System.out.println();
    }

    public static void main(String[] args) {
        // Sample Run 1: Standard Unsorted Array
        executeRun("Sample Run 1 (Standard Elements)", new int[]{45, 12, 89, 33, 67, 21}, 33);

        // Sample Run 2: Negative Numbers & Duplicates
        executeRun("Sample Run 2 (Negatives & Duplicates)", new int[]{-15, 0, 42, -8, 42, -15}, -8);

        // Sample Run 3: Single-Element Array
        executeRun("Sample Run 3 (Single Element)", new int[]{100}, 100);
    }
}