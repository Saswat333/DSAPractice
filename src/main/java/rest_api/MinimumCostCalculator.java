package rest_api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumCostCalculator {
    public static List<Integer> minimumCost(List<Integer> red, List<Integer> blue, int blueCost) {
        int n = red.size();  // Number of cities minus 1 (since 0-based)
        List<Integer> result = new ArrayList<>();

        // DP arrays for minimum cost to reach each city on the respective lines
        int[] dpRed = new int[n + 1];   // min cost to reach city i on Red line
        int[] dpBlue = new int[n + 1];  // min cost to reach city i on Blue line

        // Initialize the DP arrays
        dpRed[0] = 0;   // No cost at the start on Red line
        dpBlue[0] = blueCost;  // Starting on Blue line incurs blueCost

        // Result array for the minimum cost to reach each city
        result.add(0);  // The cost to reach city 0 is always 0

        // Iterate through each city
        for (int i = 1; i <= n; i++) {
            // Cost to reach city i using the Red line:
            dpRed[i] = Math.min(dpRed[i - 1] + red.get(i - 1), dpBlue[i - 1] + red.get(i - 1));

            // Cost to reach city i using the Blue line:
            dpBlue[i] = Math.min(dpBlue[i - 1] + blue.get(i - 1), dpRed[i - 1] + blue.get(i - 1) + blueCost);

            // Minimum of both options (Red or Blue) for city i
            result.add(Math.min(dpRed[i], dpBlue[i]));
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> red = new ArrayList<>(Arrays.asList(2,3,4));
        List<Integer> blue = new ArrayList<>(Arrays.asList(3,1,1));
        int blueCost = 2;
        MinimumCostCalculator obj = new MinimumCostCalculator();
        List<Integer> ans = obj.minimumCost(red, blue, blueCost);
        System.out.println(ans); // Output: [0, 2, 5, 6]
    }
}