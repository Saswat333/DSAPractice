package strings;

public class LongestPallindromeSubstring {
    public static void main(String[] args) {
        LongestPallindromeSubstring obj = new LongestPallindromeSubstring();
        String str = "forgeeksskeegfor";
//        int result = obj.approach1(str);
//        System.out.println(result);
        int result1 = obj.approach2(str);
        System.out.println(result1);

    }

    // expansion from the centre, using 2 pointer
    private int approach2(String str) {
        int n= str.length();
        int lo=0,hi=0;
        int start=0, maxLen=1; //keep the start and end char of the pallindrome

        //make each char of string as centre
        for(int i=0;i<str.length();i++){
            //for even len
            lo=i-1; hi=i;
            while(lo>=0 && hi<n && str.charAt(lo)==str.charAt(hi)){
                // check for maxlen
                if(hi-lo+1>maxLen){
                    start = lo;
                    maxLen = hi-lo+1;
                }
                lo--;
                hi++;
            }
            //for odd len
            lo=i-1; hi=i+1;
            while(lo>=0 && hi<n && str.charAt(lo)==str.charAt(hi)){
                // check for maxlen
                if(hi-lo+1>maxLen){
                    start = lo;
                    maxLen = hi-lo+1;
                }
                lo--;
                hi++;
            }
        }
        System.out.println(str.substring(start, start+maxLen));
        return maxLen;
    }

    private int approach1(String str) {
        int maxLen=0, start=0;
        for(int i=0;i<str.length();i++){
            for(int j=i;j<str.length();j++){
                int flag = 1;
                //the substring will be from i to j, length = j-i+1
                for(int k=0;k<(j-i+1)/2;k++){
                    if(str.charAt(i+k)!=str.charAt(j-k)){
                        flag=0;
                    }
                }
                //if it is a palindrome then check its length
                if(flag!=0 && (j-i+1)>maxLen){
                    start = i;
                    maxLen=j-i+1;
                }
            }
        }
        System.out.println(str.substring(start, start+maxLen));
        return maxLen;
        //time=O(n^3)
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