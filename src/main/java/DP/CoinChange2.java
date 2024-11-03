package DP;

import java.util.Arrays;

public class CoinChange2 {
    public static void main(String[] args) {
        CoinChange2 obj = new CoinChange2();
        int amount =5;
        int[] coins = {1,2,5};
//        int[] coins = {2,5,3,6};
//        int amount = 10;

        int result = obj.change(amount, coins);
        System.out.println("Number of ways to make the amount: "+result);
    }

    int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length+1][amount+1];
        for(int[] row: dp)
            Arrays.fill(row,-1);
        //approach1: recursive approach
//        return changeUtil1(0, amount, coins);
        return changeUtil2(0, amount, coins, dp);
    }

    private int changeUtil1(int i, int amount, int[] coins) {
        //if amount is 0 then we have reached the sum and found a way, return 1 way
        if(amount==0)
            return 1;
        //if we have exhausted the coins we have then return 0 ways
        if(i==coins.length)
            return 0;
        //if we are left with a coin which is bigger than the amount left then skip it and check the next coin
        if(amount < coins[i])
            return changeUtil1(i+1, amount, coins);
        //take=take the current coin and reduce the amount, else skip the current coin and keep amount as it is
        int take = changeUtil1(i, amount-coins[i], coins);
        int skip = changeUtil1(i+1, amount, coins);

        //total ways will be #waystotakes and #waystoskips
        return take+skip;
    }

    private int changeUtil2(int i, int amount, int[] coins, int[][] dp) {
        if(amount==0)
            return 1;
        if(i==coins.length)
            return 0;
        if(dp[i][amount]!=-1)
            return dp[i][amount];
        if(amount < coins[i])
            return dp[i][amount] =changeUtil2(i+1, amount, coins, dp);
        int take = changeUtil2(i, amount-coins[i], coins, dp);
        int skip = changeUtil2(i+1, amount, coins, dp);

        return dp[i][amount] = take+skip;
    }
}

/*
TC:
approach1: each coin has 2 change so O(2^n)
* */