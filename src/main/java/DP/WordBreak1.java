package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBreak1 {
    public static void main(String[] args) {
        String s = "catsandog";
//        List<String> wordDict = new ArrayList<>(Arrays.asList("leet","code"));
        List<String> wordDict = new ArrayList<>(Arrays.asList("cats","dog","sand","and","cat"));
        WordBreak1 obj = new WordBreak1();
        boolean ans = obj.findResult(s,wordDict);
        System.out.println(ans);
    }

    private boolean findResult(String s, List<String> wordDict) {
        int indx=0;
        Boolean[] dp = new Boolean[s.length()];
        return helper(indx, s, wordDict, dp);
    }

    private boolean helper(int indx, String s, List<String> wordDict, Boolean[] dp) {
        if(indx==s.length()){
            return true;
        }
        if(dp[indx]!=null)
            return dp[indx];
        for(int k=indx;k<=s.length();k++){
            String split = s.substring(indx, k);
            if(wordDict.contains(split) && helper(k, s, wordDict, dp)){
                return dp[indx]=true;
            }
        }
        return dp[indx]=false;
    }
}
