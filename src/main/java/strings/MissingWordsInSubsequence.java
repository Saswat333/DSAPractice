package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MissingWordsInSubsequence {
    public static void main(String[] args) {
        // Example usage
        String s = "I like cheese and crackers";
        String t = "like crackers";

        List<String> result = missingWords(s, t);

        System.out.println("Missing Words: " + result);
    }

    private static List<String> missingWords(String s, String t) {
        List<String> sWords = splitStringIntoWords(s);//split words of the s string
        List<String> tWords = splitStringIntoWords(t);//split words of the t string
        List<String> missingWords = new ArrayList<>();//to store the missing words

        int sIndex = 0;
        int tIndex = 0;

        while(sIndex<sWords.size() && tIndex <tWords.size()){
            if (!sWords.get(sIndex).equals(tWords.get(tIndex))) {
                missingWords.add(sWords.get(sIndex));
            } else {
                tIndex++;
            }
            sIndex++; //always move with the sIndex whether the word is missing or not
        }

        // Add the remaining words from s, if any
        while (sIndex < sWords.size()) {
            missingWords.add(sWords.get(sIndex));
            sIndex++;
        }


        return missingWords;
    }


    //this method splits the input string into words
    private static List<String> splitStringIntoWords(String input) {
        String[] words = input.split("\\s+");
        List<String> wordList = new ArrayList<>();

        wordList.addAll(Arrays.asList(words));
        return wordList;
    }
}
