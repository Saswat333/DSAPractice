package strings;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    public static void main(String[] args) {
        TextJustification obj = new TextJustification();
        String[] words = {"What","must","be","acknowledgment","shall","be"};
        int maxWidth = 16;

        List<String> result = obj.fullJustify(words, maxWidth);
        System.out.println(result);
    }

    private List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int curLength=0, numWords=0;
        List<String> line = new ArrayList<>();//used to form the current line

        for(String word: words){
            //if it can fit in current line
            if(word.length()+curLength+numWords <=maxWidth){
                line.add(word);
                curLength += word.length();
                numWords++;
            }
            else{
                //form a new line
                result.add(justifyLine(line, maxWidth, curLength, numWords));
                line = new ArrayList<>();
                //add the word in new line
                line.add(word);
                curLength = word.length();
                numWords=1;
            }
        }
        //todo: handle the last line
        String lastLine = String.join(" ",line);
        int padding = maxWidth - lastLine.length();
        lastLine += " ".repeat(padding);
        result.add(lastLine);

        return result;
    }

    private String justifyLine(List<String> line, int maxWidth, int curLength, int numWords) {
        if(numWords==1){
            /*single word on the line and it's not the end of the words, left-justify
                "What   must   be",
                "acknowledgment  ",
                "shall be        "             * */
            return String.format("%-" + maxWidth + "s", line.get(0));
        }
        int totalSpaces = maxWidth-curLength;
        int spaceBetweenWords = numWords-1;
        int extraSpaces = totalSpaces % spaceBetweenWords;//to be added in left side at the end
        int spaces = totalSpaces/spaceBetweenWords;//to be added equally

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<line.size()-1;i++){//except last char
            sb.append(line.get(i));
            sb.append(" ".repeat(spaces));
            if(extraSpaces>0){
                sb.append(" ");
                extraSpaces--;
            }
        }
        sb.append(line.get(line.size()-1));//append the last character
        return sb.toString();
    }

}
/*
Leetcode 68:
Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
Note that the second line is also left-justified because it contains only one word.

Solution Explanation:
1. Iterate through words:
    - add words to the line list untill the current line length exceeds the maxWidth.
    - when the line is full, call jusitfyLine() to format the line and add it to the result.
2. JustifyLine:
    - Handles the case for a single word on the line(left-justied)
    - calculates the total spaces, spaces between words, and extra spaces
    - builds the justifed line by appending words with appropriate spaces
3. Handle the last line:
    - left-justified the last line and add it to the result
    - this code handles different line configurations, distributing spaces evenly and ensuring the last line is left
    justified.
* */