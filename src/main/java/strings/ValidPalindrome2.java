package strings;

public class ValidPalindrome2 {
    public static void main(String[] args) {
        ValidPalindrome2 obj = new ValidPalindrome2();
//        String s = "aba";
        String s = "abca";
        boolean result = obj.validPalindromeCheck(s);
        System.out.println(result);

    }

    private boolean validPalindromeCheck(String s) {
        int l=0,r=s.length()-1;
        while(l<=r){
            if(s.charAt(l)==s.charAt(r)){
                l++;r--;
            }
            else{
                return isPalindrome(s, l+1,r) || isPalindrome(s, l,r-1);
            }
        }
        return true;
    }

    private boolean isPalindrome(String s, int l, int r){
        while(l<=r){
            if(s.charAt(l)==s.charAt(r)){
                l++;r--;
            }
            else return false;
        }
        return true;
    }
}

/*
LEETCODE: Valid Palindrome II[680]
Given a string s, return true if the s can be palindrome after deleting at most one character from it.

Example 1:
    Input: s = "aba"
    Output: true

Example 2:
    Input: s = "abca"
    Output: true
    Explanation: You could delete the character 'c'.
Example 3:
    Input: s = "abc"
    Output: false, any deletion wont make it palindrome

SOLUTION:
So, what I can do is create two pointer's & start comparing them from.
    - One will start from 0th Index & another will start from last index.
    - We'll check, if they are equal then continue checking
    - But if they are undequal we can have 2 cases :-
        - Case 1 : Skip e to check whether it's forming an palindrome
        - Case 2 : Skip d to check whether it's forming an plaindrome
    - But still if after deleting one character we are not gettong palindrome return false
    - Otherwise return true

Time: O(n)
* */