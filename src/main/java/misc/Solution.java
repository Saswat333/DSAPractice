package misc;

import java.util.Arrays;

public class Solution {

    public int minOperationsToSort(int[] arr) {
        int n = arr.length;
        int[] sortedArr = new int[n];
        for (int i = 0; i < n; i++) {
            sortedArr[i] = i + 1;
        }

        // Try cyclic shifts first
        int shifts = 0;
        while (!Arrays.equals(arr, sortedArr)) {
            // If we reach a situation where only a few swaps can fix the array
            if (canBeSortedWithSwaps(arr, sortedArr)) {
                return shifts + countSwaps(arr, sortedArr);
            }
            // Apply cyclic shift
            cyclicShift(arr);
            shifts++;
        }
        return shifts;  // If sorted only with shifts
    }

    private void cyclicShift(int[] arr) {
        int n = arr.length;
        int first = arr[0];
        for (int i = 0; i < n - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[n - 1] = first;
    }

    private boolean canBeSortedWithSwaps(int[] arr, int[] sortedArr) {
        int swapsNeeded = countSwaps(arr, sortedArr);
        return swapsNeeded <= 2;  // If 2 or fewer swaps can sort the array, we can handle it
    }

    private int countSwaps(int[] arr, int[] sortedArr) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i] || arr[i] == sortedArr[i]) {
                continue;
            }

            // Cycle detection
            int cycleSize = 0;
            int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = findIndex(sortedArr, arr[j]);
                cycleSize++;
            }

            // If cycle contains more than 1 element, we need (cycleSize - 1) swaps
            if (cycleSize > 1) {
                swaps += cycleSize - 1;
            }
        }
        return swaps;
    }

    private int findIndex(int[] sortedArr, int value) {
        for (int i = 0; i < sortedArr.length; i++) {
            if (sortedArr[i] == value) {
                return i;
            }
        }
        return -1; // This should not happen since arr is a permutation of sortedArr
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minOperationsToSort(new int[]{5, 3, 6, 2, 1, 7, 4})); // Example test case
    }
}
