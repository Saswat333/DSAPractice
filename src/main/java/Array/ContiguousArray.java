package Array;

import java.util.HashMap;
import java.util.HashSet;

public class ContiguousArray {
    public static void main(String[] args) {
        ContiguousArray obj = new ContiguousArray();
//        int[] nums = {1,1,0,0,1,1,0,1,1};
//        int[] nums = {1,0,1};
        int[] nums = {1,1,1,0,0,1,1};
        int result = obj.findSolution(nums);
        System.out.println("Length of contiguous array: "+ result);
    }

    private int findSolution(int[] nums) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        int sum=0, longestSubArray=0;

        for(int i=0;i<nums.length;i++){
            //if we see 0 then substract -1 to sum
            sum+=nums[i]==0?-1:1;
            //scenario-1
            if(sum==0){
                int len = i-0+1;
                if(longestSubArray<len)
                    longestSubArray = len;
            }
            if (hm.containsKey(sum)) {
                int len = i-hm.get(sum);
                if(longestSubArray<len)
                    longestSubArray = len;
            }
            else{
                hm.put(sum,i);
            }
        }
        return longestSubArray;
    }
}

/*
Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
Example 1:
    Input: nums = [0,1]
    Output: 2
    Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.

Input: nums=[1,1,0,0,1,1,0,1,1]
output=2 => [1,0,0,1,1,0] pr [0,0,1,1,0,1] can be longest
Explanation:
	1,1,0,0,1,1,0,1,1   ans=6
sum=1,2,1,0,1,2,1,2,3

hashmap:<sum, seen_sum_numbers_index>

Two possible scenario's:
1. when sum=0, means we have seen some 0's and the total no 0's and 1's are equal. Its guarenteed that the number is starting from first index
2. when we do not see sum=0, but we see a sum, which we have already seen means there are equal number of 0's and 1's, but we
need to find the length by substracting the current index and the previous sum's index, so we need to store the sum and the index
of the number which made the sum and substract: current_index-seen_sum_index
* */