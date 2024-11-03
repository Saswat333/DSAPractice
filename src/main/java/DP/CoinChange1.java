package DP;

import java.util.Arrays;

public class CoinChange1 {
    public static void main(String[] args) {
        CoinChange1 obj = new CoinChange1();
        int amount =11;
        int[] coins = {1,2,5};
//        int[] coins = {2,5,3,6};
//        int amount = 10;

        int result = obj.change(amount, coins);
        System.out.println("Minimum number of coins to make the amount: "+result);
    }

    private int change(int amount, int[] coins) {
//        int ans = changeUtil(0,amount, coins);
        int ans = changeUtilBottomUp(amount, coins);
        System.out.println("Final:"+ans);
        return ans==Integer.MAX_VALUE ? -1:ans;

    }

    private int changeUtil(int i, int amount, int[] coins){
        if(amount==0)
            return 0;
        if(i>=coins.length)
            return Integer.MAX_VALUE;

        int take=Integer.MAX_VALUE;

        int skip = changeUtil(i+1, amount, coins);
        if(coins[i]<=amount)
            take = 1+changeUtil(i, amount-coins[i],coins);
        return Math.min(take, skip);
    }

    private int changeUtilBottomUp(int amount, int[] coins){
        //Question: How many coins can we use to compute the amount
        //for each amount we can save how many minimum coins were needed, we can save that
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);//dont fill -1 as we will do min calc, either give int.max or amount+1
        dp[0]=0;

        //for each amount we will check if that coin can be of any use
        for(int i=1;i<=amount;i++){
            for(int j=0;j<coins.length;j++){
                //if the coin is less than the current amount
                if(coins[j]<=i){
                    //checking if we already have better ans or the next coin will give better ans
                    dp[i] = Math.min(dp[i], dp[i-coins[j]]+1);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[amount]>amount?-1:dp[amount];
    }
}

/*best explanation
* button up: neetcode https://www.youtube.com/watch?v=H9bfqozjoqs&t=400s
*
* */
