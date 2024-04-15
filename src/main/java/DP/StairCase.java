//You are climbing a staircase. It takes n steps to reach the top.
//
//Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
package DP;

public class StairCase {
    public static void main(String[] args) {
        int n = 5;
        StairCase obj = new StairCase();
        int ans = obj.climbStair1(n);
        System.out.println("Result: "+ans);
    }

// constance space optimal approach
    private int climbStair2(int n) {
        int a =1;
        int b =1;

        for(int i=2;i<=n;i++){
            int c = a+b;
            a = b;
            b = c;
        }
        return b;
    }

    //tablular approach
    private int climbStair1(int n) {
        int[] dp = new int[n+1];
        if(n==1)
            return 1;
        dp[0]=dp[1]=1;


        for(int i=2;i<=n;i++){
            dp[i] = dp[i-2]+dp[i-1];
        }

        return dp[n];
    }
}
