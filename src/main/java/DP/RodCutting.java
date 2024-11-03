package DP;

public class RodCutting {
    public static void main(String[] args) {
        RodCutting obj = new RodCutting();
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20};
        int result = obj.findMaximumValue(prices);
        System.out.println("Result: "+result);
    }

    private int findMaximumValue(int[] prices) {
        //buttom up approach
        int n = prices.length;
        int[] dp = new int[n+1];

        for(int i=1;i<=n;i++){
            for(int j=0;j<i;j++){
                dp[i] = Math.max(dp[i], prices[j]+dp[i-j-1]);
            }
        }
        return dp[n];
    }
}

/* {GFG}
Given a rod of length N inches and an array of prices, price[]. price[i] denotes the value of a piece of length i.
Determine the maximum value obtainable by cutting up the rod and selling the pieces.

Note: Consider 1-based indexing.
Input:
N = 8
Price[] = {1, 5, 8, 9, 10, 17, 17, 20}
Output:
22
Explanation:
The maximum obtainable value is 22 by
cutting in two pieces of lengths 2 and
6, i.e., 5+17=22.
* */