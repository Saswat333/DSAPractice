package DP;

import java.util.*;

public class WordBreak2 {
    public static void main(String[] args) {
        String s = "pineapplepenapple";
        List<String> wordDict = new ArrayList<>(Arrays.asList("apple","pen","applepen","pine","pineapple"));
        WordBreak2 obj = new WordBreak2();
        List<String> res = obj.wordBreak(s, wordDict);
        System.out.println(res);
    }

    private List<String> wordBreak(String s, List<String> wordDict) {
        HashMap<Integer, List<String>> hm = new HashMap<>();
        HashSet<String> hs = new HashSet<>(wordDict);
        return wordBreakHelper(s, 0, hs, hm);
    }

    //In DP we have a hashmap which will have the <start_indx , list of words> which are valid, if we again at some
    //branch have to recalculate the same indx we can return from hashmap
    private List<String> wordBreakHelper(String s, int start, HashSet<String> dict, HashMap<Integer, List<String>> dp) {
        if(dp.containsKey(start)){
            return dp.get(start);
        }
        List<String> result = new ArrayList<>();
        if(start == s.length()){
            result.add("");
        }
        for(int end=start+1;end<=s.length();end++){
            String prefix = s.substring(start, end);
            if(dict.contains(prefix)){
                List<String> validSubString = wordBreakHelper(s, end, dict, dp);
                for(String suf: validSubString){
                    String valid = prefix+(suf.equals("")?"":" ")+suf;
                    result.add(valid);
                }
            }
        }
        dp.put(start, result);
        return result;
    }


    //Recursive solution
    //At each index, need to check the substring from index to index + maxLength, if any substring forms a word,
    // check the suffix of the string.
//    private List<String> wordBreakHelper(String s, int start, HashSet<String> dict) {
//        List<String> result = new ArrayList<>();
//        if(start == s.length()){
//            result.add("");
//        }
//        for(int end=start+1;end<=s.length();end++){
//            String prefix = s.substring(start, end);
//            if(dict.contains(prefix)){
//                List<String> validSubString = wordBreakHelper(s, end, dict);
//                for(String suf: validSubString){
//                    String valid = prefix+(suf.equals("")?"":" ")+suf;
//                    result.add(valid);
//                }
//            }
//        }
//        return result;
//    }


}
/*
* length of key = n
* #max length of a word inside given dict let say K, number of words in dict = m
* total =
*
* */