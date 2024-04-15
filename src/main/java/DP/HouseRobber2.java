package DP;

import java.util.Arrays;

public class HouseRobber2 {
    public static void main(String[] args) {
        HouseRobber2 obj = new HouseRobber2();

        int[] nums = {1,2,3,1};

        int result = obj.robHouse(nums);
        System.out.println("Result: "+ result);
    }

    private int robHouse(int[] nums) {
        int n = nums.length;
        int[] arr1 = new int[n-1];
        int[] arr2 = new int[n-1];

        if(n==1)
            return nums[0];
        if(n==2)
            return Math.max(nums[0], nums[1]);
        int x=0,y=0;
        for(int i=0;i<n;i++){
            if(i!=0)
                arr1[x++] = nums[i];
            if(i!=n-1)
                arr2[y++] = nums[i];
        }

        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));

        int ans1 = solveOptimised(arr1);
        int ans2 = solveOptimised(arr2);

        return Math.max(ans1, ans2);
    }

    public int solveOptimised(int[] nums){
        int n = nums.length;
        int[] dp = new int[n+1];
        //dp[i] = max stolen money till house i
        int prev2 = 0; //conside it for negative indexes
        int prev1 = nums[0]; //for 0th index of num

        for(int i=2;i<=n;i++){
            //because dp array has one extra 0th index for base case use i-1 to fecth ith value from nums
            int steal = nums[i-1]+prev2;
            int noSteal = prev1;

            int cur = Math.max(steal, noSteal);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }
}
