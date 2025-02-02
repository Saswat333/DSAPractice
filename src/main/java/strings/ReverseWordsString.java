package strings;

import java.util.Arrays;

public class ReverseWordsString {
    public static void main(String[] args) {
        String input = "hello to  the world ";
        ReverseWordsString obj = new ReverseWordsString();

        String result1 = obj.reverseWordsApproach1(input);
        System.out.println("Reversed Word: "+result1);

        String result2 = obj.reverseWordsApproach2(input);
        System.out.println("Reversed Word: "+result2);
    }

    private String reverseWordsApproach1(String input){
        String[] str = input.trim().split("\\s+");
        System.out.println(Arrays.toString(str));

        //Using StingBuilder as the appending using + operator will create a new string always
        StringBuilder out  = new StringBuilder();

        for(int i=str.length-1;i>0;i--){
            //iterate through each word and reverse
            out.append(str[i]+" ");
        }
        return out.append(str[0]).toString();
    }

    //Using two pointers approach, it will be inplace solution
    private String reverseWordsApproach2(String input){
        String[] str = input.trim().split("\\s+");
        int i=0, j= str.length-1;
        while(i<j){
            String temp = str[i];
            str[i] = str[j];
            str[j] = temp;
            i++;j--;
        }
        System.out.println("without join: "+Arrays.toString(str));
        return String.join(" ",str);
    }
}
