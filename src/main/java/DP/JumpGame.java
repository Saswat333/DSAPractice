package DP;

import java.util.Arrays;

public class JumpGame {
    public static void main(String[] args) {
        JumpGame obj = new JumpGame();
        //approach1: recursion+memoization
        int[] nums = {2,3,1,1,4};
//        int[] nums = {3,2,1,0,4};

        int n = nums.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        boolean ans = obj.solveMemoization(nums, n,0, dp);
        System.out.println("Result: "+ ans);
    }

    private boolean solveMemoization(int[] nums, int n, int indx, int[] dp) {
        if(indx==n-1)
            return true;
        if(indx>=n-1)
            return true;
        if(dp[indx] != -1)
            return dp[indx]==0?false:true;
        for(int i=1;i<=nums[indx];i++){
            if(solveMemoization(nums,n, indx+i, dp)){
                dp[indx] = 1;
                return  true;
            }
        }
        dp[indx] = 0;
        return false;
    }

}
