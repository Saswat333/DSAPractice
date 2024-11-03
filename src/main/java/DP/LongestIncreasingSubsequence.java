package DP;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        LongestIncreasingSubsequence obj = new LongestIncreasingSubsequence();
        int[] nums = {10,9,2,5,3,7,101,18};
//        int[] nums = {0,1,0,3,2,3};

        //Approach 1: Recurssion
//        int result = obj.lengthOfLISTopDownRecurssive(0,-1, nums);
//        System.out.println("Length of LIS: "+result);

        //Approach 2: Memoization: here the current index and the prev elem are changing so we will need a 2d dp
//        int n = nums.length;
//        int[][] dp = new int[n+1][n+1];
//        for(int i=0;i<=n;i++){
//            Arrays.fill(dp[i], -1);
//        }
//        int resultMem = obj.lengthOfLISTopDownMemoize(0,-1, nums, dp);
//        System.out.println("Memoize approach result : "+resultMem);

        //Approch 3: Button up
        int result = obj.lengthOfLISBottomUp(nums);
        System.out.println("Buttom up approach: "+result);
    }

    //cur = current index, prev = prev index whic is part of the subsequence
    private int lengthOfLISTopDownRecursive(int cur, int prev, int[] nums) {
        if(cur>=nums.length)
            return 0;
        int take = 0;
        //take can be either first elem of subseq or if not first elem it has to be greater than prev elem
        if(prev == -1 || nums[cur]>nums[prev])
            take = 1+ lengthOfLISTopDownRecursive(cur+1, cur, nums); //if you take the elem the prev will the cur elem for next iteration

        int skip = lengthOfLISTopDownRecursive(cur+1, prev, nums);// if elem is skipped, then the prev elem will be as it is

        return Math.max(take, skip);
    }

    private int lengthOfLISTopDownMemoize(int cur, int prev, int[] nums, int[][] dp) {
        if(cur>=nums.length)
            return 0;
        //edge case: prev elem index can be -1, out dp dont have value for that
        if(prev!=-1 && dp[cur][prev]!=-1)
            return dp[cur][prev];

        int take = 0;
        //take can be either first elem of subseq or if not first elem it has to be greater than prev elem
        if(prev == -1 || nums[cur]>nums[prev])
            take = 1+lengthOfLISTopDownMemoize(cur+1, cur, nums, dp); //if you take the elem the prev will the cur elem for next iteration

        int skip = lengthOfLISTopDownMemoize(cur+1, prev, nums, dp);// if elem is skipped, then the prev elem will be as it is

        //same edge case of prev elem is -1
        if(prev != -1)
            dp[cur][prev] = Math.max(take, skip);
        return Math.max(take, skip);
        //Time : O(n2) and space=O(n2),
        //total states = n*n (as we fetch from he dp array)
        //else without memoize, worst case it will be 2^n
    }

    //For button up we are check for each combination of numbers or subsequence using 2 points i and j
    // DP STATE: dp[i] represents the length of the longest increasing subsequence ending at index i
    //the result will be the max value in dp array
    private int lengthOfLISBottomUp(int[] nums) {
        int n= nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); //every number in itself is a subsequence
        int maxLIS=1; //default maxLIS will be 1 the number itself

        //check every subsequence size
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j]){
                    //if condition satisfy add +1 with the prev smaller elem maxLis value seen
                    dp[i] = Math.max(dp[i],dp[j]+1);
                    maxLIS = Math.max(maxLIS,dp[i]);
                }
            }
        }

//        int maxLen = Arrays.stream(dp).max().orElse(0);
        return maxLIS;

        //TC: O(n2) and space=O(n)
    }
}
