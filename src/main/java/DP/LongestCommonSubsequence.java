package DP;

import java.util.Arrays;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        LongestCommonSubsequence obj = new LongestCommonSubsequence();
        //only recursion
        int result1 = obj.solveApproach1(text1, text2, 0, 0);//i and j are starting indx for text1&2
        System.out.println("Result Approach1: "+result1);

        //recursion+memoization
        int m =text1.length();
        int n =  text2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0;i<m+1;i++){
            Arrays.fill(dp[i], -1);
        }
        int result2 = obj.solveApproach2(text1, text2, 0, 0, dp);//i and j are starting indx for text1&2
        System.out.println("Result Approach2: "+result2);

        //Approach3: buttom up
        int result3 = obj.solveApproach3(text1, text2);
        System.out.println("Result approach3: "+result3);
    }
    //using only recursion
    private int solveApproach1(String s1, String s2, int i, int j) {
        if(i>=s1.length() || j>=s2.length())
            return 0;
        if(s1.charAt(i)==s2.charAt(j)){
            return 1+solveApproach1(s1, s2, i+1, j+1);
        }
        return Math.max(solveApproach1(s1, s2, i+1, j), solveApproach1(s1, s2, i, j+1));
        //time = 2^m *2^n= 2^(m+n) [for every char of text1 we are checking every possibility of text2]
    }

    //because both i and j are changing we will need 2d array for dp
    //i & j are indexes and max length for string is 1000 so take dp[1001][1001]
    //here i and j are actually length of string not indexes
    private int solveApproach2(String s1, String s2, int i, int j, int[][] dp) {
        if(i>=s1.length() || j>=s2.length())
            return 0;
        if(dp[i][j] != -1)
            return dp[i][j];
        if(s1.charAt(i)==s2.charAt(j)){
            return dp[i][j]=1+solveApproach1(s1, s2, i+1, j+1);
        }
        return dp[i][j] = Math.max(solveApproach1(s1, s2, i+1, j), solveApproach1(s1, s2, i, j+1));
        //time:  2^m *2^n= 2^(m+n) [for every char of text1 we are checking every possibility of text2]
        //space: max dp size m+1 * n+1 states, so O(m*n)
    }

    private int solveApproach3(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];

        //first row and col will be 0
        for(int i=0;i<m+1;i++)
            dp[i][0]=0;
        for(int i=0;i<n+1;i++)
            dp[0][i] = 0;

        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1))
                    dp[i][j] = 1+dp[i-1][j-1];
                else
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }

        return dp[m][n];
        //time: O(m*n)
        //space: O(m*n)
    }
}
