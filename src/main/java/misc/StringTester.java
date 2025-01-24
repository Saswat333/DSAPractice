package misc;

import java.util.ArrayList;
import java.util.List;

public class StringTester {
    public static void main(String[] args) {
        StringTester obj = new StringTester();

        String inp1 = "abc";
        obj.solvePart1(inp1);

        String inp2 = "practice makes perfect";
        obj.solvePart2(inp2);

        String inp3 = "I'm great, thanks!";
        String result = obj.solvePart3(inp3);
        System.out.println(result);
    }

    private void solvePart1(String inp) {
        StringBuilder reverse = new StringBuilder(inp);
        System.out.println(reverse.reverse().toString());
    }

    private void solvePart2(String inp){
        String[] words = inp.split(" ");
        StringBuilder reverse = new StringBuilder();

        for(int i=words.length-1;i>=0;i--){
            reverse.append(words[i]);
            if(i!=0)
                reverse.append(" ");

        }
        //result
        System.out.println(reverse.toString());
    }

    private String solvePart3(String str) {
        // Split the string into words and process each word
        String[] words = str.split(" ");
        List<String> result = new ArrayList<>();

        for (String word : words) {
            // If the word contains alphanumeric characters, reverse the word
            if (containsAlphanumeric(word)) {
                result.add(reverseWord(word));
            } else {
                // If it's a non-word (punctuation), leave it as is
                result.add(word);
            }
        }

        // Join the processed words with spaces between them
        return String.join(" ", result);
    }

    // Helper method to check if a word contains any alphanumeric characters
    private static boolean containsAlphanumeric(String word) {
        for (char c : word.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                return true;
            }
        }
        return false;
    }

    // Helper method to reverse a word
    private static String reverseWord(String word) {
        StringBuilder sb = new StringBuilder(word);
        return sb.reverse().toString();
    }
}


/*
Solving some easy string problems:
 Part 1: Reverse a string
abc -> cba

Part 2: Reverse the words in a string
practice makes perfect -> perfect makes practice

Part 3: Reverse words in a string, but keep non-alphanumeric character placement.
Asked about ' and was told we can count that with alphanumeric
I'm great, thanks! -> Thanks I'm, great!
* */