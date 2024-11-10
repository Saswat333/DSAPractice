package Strings;

public class ValidPalindrome4 {
    public static void main(String[] args) {
        ValidPalindrome4 obj = new ValidPalindrome4();
//        String s = "abcdba";
        String s= "abcdef";

        boolean result = obj.validPalindrome(s);
        System.out.println(result);
    }

    private boolean validPalindrome(String s) {
        int l=0,r=s.length()-1;
        int misMatchCount=0;

        while(l<r){
            if(s.charAt(l) != s.charAt(r)){
                ++misMatchCount;
            }
            l++;r--;
        }
        return misMatchCount<=2;
    }
}

/*
Leetcode: 2330 ValidPalindrome IV [lock]
You are given a 0-indexed string s made up of lowercase English letters. To address the problem, you are required to
determine if the string s can be turned into a palindrome through exactly one or two operations.
In this context, an operation is defined as changing any single character in the string s to any other character.
The expected return value is a boolean true if it is possible to make s a palindrome with one or two changes, else false.
IMPORTANT: EXACTLY ONE OR TWO OPERATION
Example1:
Input: s = "abcdba"
Output: true
Explanation: One way to make s a palindrome using 1 operation is:
- Change s[2] to 'd'. Now, s = "abddba".
One operation could be performed to make s a palindrome so return true.

Example2:
Input: s = "aa"
Output: true
Explanation: One way to make s a palindrome using 2 operations is:
- Change s[0] to 'b'. Now, s = "ba".
- Change s[1] to 'b'. Now, s = "bb".
Because it needs exactly 1-2 operation, above operations could be performed to make s palindrome so return true.
* */