package Strings;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        MinimumWindowSubstring obj = new MinimumWindowSubstring();
        String res = obj.findWindow(s,t);
        System.out.println("Result: "+res);
    }

    private String findWindow(String s, String t) {
        int i=0, j=0, start_i=0, requiredCount=t.length();
        int minWindowSize = Integer.MAX_VALUE;

        Map<Character, Integer> map = new HashMap<>();
        for(Character ch:t.toCharArray()){
            map.put(ch, map.getOrDefault(ch,0)+1);
        }
        while(j < s.length()){
            char ch = s.charAt(j);
            if(map.containsKey(ch) && map.get(ch)>0)
                requiredCount--;
            map.put(ch, map.getOrDefault(ch, 0)-1);
            while(requiredCount==0){
                //jab tak required count ==0, tab tak our substring window has its target characters, so keep shrinking
                int curWinSize = j-i+1;
                if(curWinSize<minWindowSize){
                    start_i = i;
                    minWindowSize = curWinSize;
                }
                //As we move i ahead(shrink) we have to add back the counts in map and requriedcount
                map.put(s.charAt(i), map.getOrDefault(s.charAt(i),0)+1);
                if(map.get(s.charAt(i))>0){
                    requiredCount++;
                }
                i++;
            }
            j++;
        }
        return minWindowSize == Integer.MAX_VALUE ? "":s.substring(start_i, start_i+minWindowSize);
    }
}

//TIME : O(m+n)
//space: O(n+m) <- hasha,p will store char of both strings