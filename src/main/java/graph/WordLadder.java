package graph;

import java.io.IOException;
import java.util.*;

public class WordLadder {
    public static void main(String[] args) throws IOException {
        String startWord = "der", targetWord = "dfs";
        List<String> wordList = new ArrayList<>(Arrays.asList("des", "der", "dfr", "dgt", "dfs"));

        WordLadder obj = new WordLadder();
        int ans = obj.wordLadderLength(startWord, targetWord, wordList);

        System.out.print(ans);

        System.out.println();
    }

    private int wordLadderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<Pair> que = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        int len = wordList.size();
        for(String word: wordList)
            if(!word.equals(beginWord))
                set.add(word);
        que.add(new Pair(beginWord,1));

        while(!que.isEmpty()){
            Pair temp = que.poll();
            String word = temp.first;
            int steps = temp.second;

            // if word is equal to target return steps
            if(word.equals(endWord))
                return steps;

            //create iteration
            for(int i=0;i<word.length();i++){
                for(char ch='a';ch<='z';ch++){
                    char[] curr = word.toCharArray();
                    curr[i] = ch;
                    String replacedWord = new String(curr);

                    //check if that word exist in set
                    if(set.contains(replacedWord)){
                        set.remove(replacedWord);
                        que.add(new Pair(replacedWord, steps+1));
                    }
                }
            }
        }
        return 0;
    }
}
