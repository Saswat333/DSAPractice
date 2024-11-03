package rest_api;

import java.util.*;

public class SlotMachine {
    public static int slotSpins(String[] history) {
        int n = history.length;
        int m = history[0].length();

        int[] maxValues = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maxValues[j] = Math.max(maxValues[j], history[i].charAt(j) - '0');
            }
        }

        int totalStops = 0;
        for (int maxValue : maxValues) {
            totalStops += maxValue;
        }

        return totalStops;
    }
//    private static int getMaxValue(String spin) {
//        int maxValue = 0;
//        for (char c : spin.toCharArray()) {
//            maxValue = Math.max(maxValue, c - '0');
//        }
//        return maxValue;
//    }

    public static void main(String[] args) {
        String[] history = {"137", "364", "115", "724"};
        int result = slotSpins(history);
        System.out.println("Total stops: " + result);  // Output should be 7 + 5 = 12
    }
}
