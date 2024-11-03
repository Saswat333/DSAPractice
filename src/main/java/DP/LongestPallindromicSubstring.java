package DP;

public class LongestPallindromicSubstring {
    public static void main(String[] args) {
        String s = "babad";
        LongestPallindromicSubstring obj = new LongestPallindromicSubstring();
        String result = obj.solution1(s);
        System.out.println("Result: "+result);
    }

    private String solution1(String s) {
        if(s.length()<=1)
            return s;
        String maxStr = s.substring(0,1);

        for(int i=0;i<s.length()-1;i++){
            String odd = expandFromCenter(s, i, i);
            String even = expandFromCenter(s, i, i+1);

            if(odd.length()>maxStr.length())
                maxStr=odd;
            if(even.length()>maxStr.length())
                maxStr = even;
        }
        return maxStr;
    }

    private String expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }
}
/*
Approach1: n^3
    - take start point i and increase j and for each substring check if its pallindrom
    - meanwhile store the max length
Approach :n^2
-We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded from its center,
and there are only 2n - 1 such centers.
- why there are 2n - 1 but not n centers? The reason is the center of a palindrome can be in between two letters.
Such palindromes have even number of letters (such as "abba") and its center are between the two 'b's.'
- Since expanding a palindrome around its center could take O(n) time, the overall complexity is O(n^2).
Algo:
- At starting we have maz_str = s[0] and max_len = 1 as every single character is a palindrome.
- Now, we will iterate over the string and for every character we will expand around its center.
- For odd length palindrome, we will consider the current character as the center and expand around it.
- For even length palindrome, we will consider the current character and the next character as the center and expand around it.
- We will keep track of the maximum length and the maximum substring.
- Print the maximum substring.
* */