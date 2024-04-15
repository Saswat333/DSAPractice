package Strings;

public class LongestCommonPrefixDS {

    private String longestCommPre(String[] s){
        if (s.length==0)
            return "";
        String prefix = s[0];
        for(int i=1;i<s.length;i++){
            //indexOf(): It returns the position of the first occurrence of specified character(s) in a string
            while (s[i].indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length()-1);
                System.out.println(prefix);
                if(prefix.isEmpty())
                    return "";
            }
        }
        return prefix;
    }
    public static void main(String[] args) {
        LongestCommonPrefixDS obj = new LongestCommonPrefixDS();
        String[] str = {"apps", "apple", "ape"};
        String res = obj.longestCommPre(str);
        System.out.println(res);

    }
}
