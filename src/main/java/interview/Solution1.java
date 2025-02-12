package interview;

import java.util.Arrays;

public class Solution1 {
    public int[] minimalOperations(String[] words) {
        // Create a list to store the result for each word
        int[] result = new int[words.length];

        // Loop through each word in the input list
        for (int i = 0; i < words.length; i++) {
            int changes = countMin(words[i]);
            // Store the result for this word
            result[i] = changes;
        }

        // Return the result array
        return result;
    }

    private int countMin(String s){
        // n stores the length of the string s
        int n = s.length();

        // ans will store the required ans
        int ans = 0;

        // i is the current index in the string
        int i = 0;

        while (i < n)
        {
            int j = i;

            // Move j until characters s[i] & s[j]
            // are equal or the end of the
            // string is reached
            while (j < n && s.charAt(j) ==
                    s.charAt(i))
            {
                j++;
            }

            // diff stores the length of the
            // substring such that all the
            // characters are equal in it
            int diff = j - i;

            // We need atleast diff/2 operations
            // for this substring
            ans += diff / 2;
            i = j;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution1 obj = new Solution1();
        // Example usage
        String[] words = {"add", "boook", "break"};
        int[] result = obj.minimalOperations(words);

        // Print the result array
        System.out.println(Arrays.toString(result));
    }
}
