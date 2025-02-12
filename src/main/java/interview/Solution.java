package interview;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        // Example usage
        List<Integer> sprints = Arrays.asList(1,5,10,3);
        int n = 10;
//        int result = getMostVisited(n, sprints);
        int result = getMostVisited(n, sprints);
        // Print the result
        System.out.println(result);
    }

    public static int getMostVisited(int n, List<Integer> sprints) {
        // We use an array to track the visit counts for each marker
        int[] ans = new int[n + 2];  // n + 2 to handle the "end + 1" case

        // Process each sprint in the list
        for (int i = 0; i < sprints.size() - 1; ++i) {
            int start = Math.min(sprints.get(i), sprints.get(i + 1));
            int end = Math.max(sprints.get(i), sprints.get(i + 1));

            ans[start] += 1;
            if (end < n - 1) {
                ans[end + 1] -= 1;
            }
        }

        // Now, calculate the prefix sum and find the most visited marker
        int prev = ans[0];
        int max = -1;
        int result = -1;

        for (int i = 1; i < n; ++i) {
            // Cumulative sum and capture max sum with its index
            ans[i] += prev;
            prev = ans[i];
            if (ans[i] > max) {
                max = ans[i];
                result = i;
            }
        }

        // Return the result, the most visited marker
        return result;
    }

//    private static int getMostVisited(int n, List<Integer> sprints) {
//        // Create a list to count visits for each marker
//        int[] visits = new int[n + 1]; // We use n+1 because marker numbers are 1-indexed
//
//        // Process each sprint
//        for (int i = 0; i < sprints.size() - 1; i++) {
//            int start = sprints.get(i);
//            int end = sprints.get(i + 1);
//
//            // Mark the range of visits between start and end
//            if (start < end) {
//                visits[start]++;
//                visits[end]--;
//            } else {
//                visits[end]++;
//                visits[start]--;
//            }
//        }
//
//        // Calculate the prefix sum to get the actual visit counts
//        int maxVisits = 0;
//        int currentVisits = 0;
//        int result = 0;
//
//        for (int i = 1; i <= n; i++) {
//            currentVisits += visits[i];
//            if (currentVisits > maxVisits) {
//                maxVisits = currentVisits;
//                result = i;
//            }
//        }
//
//        return result;
//    }
}
