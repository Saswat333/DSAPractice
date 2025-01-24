package Array;

import java.util.HashSet;
import java.util.Set;

public class DistinctCharCount {
    public static void main(String[] args) {
        DistinctCharCount obj = new DistinctCharCount();
        StringBuilder str = new StringBuilder();
        str.append("fffilllttteeeeerrrreddd");
        String result = obj.charCount(str);
        System.out.println(result);
    }

    private String charCount(StringBuilder str) {
        Set<Character> hs = new HashSet<>();
        char[] inp = str.toString().toCharArray();

        for(char c: inp){
            if(c!=' ')
                hs.add(c);
        }

        int result = hs.size();
        return String.valueOf(result);
    }

}
