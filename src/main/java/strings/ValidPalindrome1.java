package strings;

public class ValidPalindrome1 {
    public static void main(String[] args) {
        ValidPalindrome1 obj = new ValidPalindrome1();

        String s= "A man, a plan, a canal: Panama";
        boolean value = obj.isPalindrome(s);
        System.out.println("Result: "+value);


    }

    private boolean isPalindrome(String s) {
        int n = s.length();
        int left=0,right=n-1;

        while(left<right){
            char l=s.charAt(left), r=s.charAt(right);
            if(!Character.isLetterOrDigit(l))
                left++;
            else if(!Character.isLetterOrDigit(r))
                right--;
            else if (Character.toLowerCase(l) != Character.toLowerCase(r)) {
                return false;
            }
            else {
                left++; right--;
            }
        }
        return true;
    }
}
/*
LEETCODE: Valid Palindrome-I [125]

A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all
non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.
Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.

Solution:
1. Solve using 2 pointer. Take two pointer left and right
2. For both left and right, if the character is not Character.isLetterOrDigit(r), then skip, goto next char
3. If it is a char, then first convert to lower case and then check if they are not equal then return FALSE
4. else increment both pointer to next char.

Time: O(N)
* */