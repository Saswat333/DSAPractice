package misc;

import java.util.*;

public class TruckItem {
    public static void main(String[] args) {
//        List<Integer> trucks = new ArrayList<>(Arrays.asList(4, 5, 7, 2));
        int[] trucks = {4, 5, 7, 2};
        int[] items = {1, 2, 5};
        int[] result = getTrucksForItems(trucks, items);
        System.out.println(Arrays.toString(result));
    }

//    private static int[] getTrucksForItems(int[] trucks, int[] items) {
//        int n = trucks.length;
//        int m = items.length;
//        int[] result = new int[items.length];
//        Arrays.fill(result, -1);
//
//        Map<Integer, Integer> truckMap = new HashMap<>();
//
//        for(int i=0;i<n;i++){
//            truckMap.put(trucks[i], i);
//        }
//        for(int i=0;i<m;i++){
//
//            int itemWeight = items[i];
//            for(int j=m-1;j>=0;j--){
//                if(trucks[j] >= itemWeight){
//                    result[i] = j;
//                    truckMap.remove(trucks[i]);
//                    break;
//                }
//            }
//        }
//        return result;
//    }

    public static int[] getTrucksForItems(int[] trucks, int[] items) {
        Arrays.sort(trucks); // Sorting the trucks array for binary search
        int[] result = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            int idx = binarySearch(trucks, items[i]);
            if (idx == trucks.length) {
                result[i] = -1; // If no truck can carry the item
            } else {
                result[i] = idx;
            }
        }

        return result;
    }

    private static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
