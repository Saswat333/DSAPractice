package array;

import java.util.HashSet;
import java.util.TreeSet;

public class FindDuplicateWithinKDistance {
    public static void main(String[] args) {
        FindDuplicateWithinKDistance obj = new FindDuplicateWithinKDistance();
//        int k = 3;
//        int arr[] = {1, 2, 3, 4, 1, 2, 3, 4};
        int k = 3;
        int arr[] = {1, 2, 3, 1, 4, 5};
        boolean result = obj.findDuplicate(arr, k);
        System.out.println("Has duplicate within "+k+" distance: "+result);

        //follow up: we need to find two buildings with almost the same height (within a height difference H)
        // and at most k distance apart. [check below for approach]
        int[] heights = {5, 8, 12, 6, 14, 10, 7, 15}; //12,6,14
        int window = 3;
        int h = 2;
        boolean resultPart2 = obj.findBuildingClosestHeight(heights, window, h);
        System.out.println("Building within "+h+" height :"+resultPart2);
    }

    private boolean findDuplicate(int[] arr, int k) {
        HashSet<Integer> seenHeight = new HashSet<>();

        for(int i=0;i<arr.length;i++){
            if(seenHeight.contains(arr[i])){
                return true;
            }

            //add current height to set, if not seen
            seenHeight.add(arr[i]);

            //sliding: ensure size of the set is at most k
            if(i>=k){
                seenHeight.remove(arr[i-k]);
            }
        }
        //No such building found return false
        return false;
        //Time = O(n) where n is size of input
        //Space = O(k), window size k for hashset
    }

    private boolean findBuildingClosestHeight(int[] heights, int k, int H) {
        TreeSet<Integer> window = new TreeSet();

        for(int i=0;i<heights.length;i++){
            int currentHeight = heights[i];

            //find the closest smallest or equal height(floor)
            //or larger or equal height(ceiling)
            Integer floor = window.floor(currentHeight);
            Integer ceiling = window.ceiling(currentHeight);

            //check if the floor height is within the height difference H
            if(floor != null && Math.abs(currentHeight-floor)<=H){
                return true;
            }
            //check if the celing height is within height diff
            if(ceiling != null && Math.abs(currentHeight-ceiling)<=H){
                return true;
            }
            //add current height to window
            window.add(currentHeight);

            //maintain the window size to atmost k elem
            if(i>=k){
                window.remove(heights[i-k]);
            }
        }
        //no such building found in k range
        return false;
        // O(n log k), where n is the number of buildings and k is the size of the window.
        //space: O(k), window size
    }
}


/*
Wayfair:https://leetcode.com/discuss/interview-experience/5860990/WayFair-or-SDE2-or-Bengaluru-or-Reject
https://www.geeksforgeeks.org/check-given-array-contains-duplicate-elements-within-k-distance/

Approach1: (bruteforce)
 - for every window we can slide and check each element per window
 - this is O(k*n) or n^2 time

Better approach(using HashSet)
The idea is to add elements to the hash. We also remove elements that are at more than k distance from the current element.
Following is a detailed algorithm.
- Create an empty hashtable.
- Traverse all elements from left to right. Let the current element be ‘arr[i]’
    - If the current element ‘arr[i]’ is present in a hashtable, then return true.
    - Else add arr[i] to hash and remove arr[i-k] from hash if i is greater than or equal to k

-------------------------------------------------
we need to find two buildings with almost the same height (within a height difference H) and at most k distance apart.
- if the elements within the k window can be sorted then we can find if the next elem or prev elem is within H limit
- this can be done if we use treemap to store the elements and to find the next greater of prev we can use floor and ceil.

Sliding Window + TreeSet:
- A TreeSet maintains a sorted collection of building heights within a window of size k. The sorting allows us to
    efficiently find the closest height to the current building's height in logarithmic time (O(log k)).
- For each building, we:
    - Add its height to the TreeSet.
    - Use the TreeSet's floor and ceiling methods to find the closest heights to the current height that are within a difference of H.
    - If such a height exists within the height difference H, return true.
Maintain Window Size:
    - As we iterate through the buildings, we maintain a sliding window of size k. If the window exceeds k, we remove
        the oldest building from the TreeSet.

Insertion and deletion from TreeSet take O(log k) time, and we perform this operation for each building.
Time: O(n log k), where n is the number of buildings and k is the size of the window.
* */