//Given a string as an input. We need to write a program that will print all non-empty substrings of that given string.
//approach1: n3 (using 3 loop, 1st- pick first char, 2nd-pick the endpoint(on right of 1st elem) for the 1st char,
// 3rd- iterate between 1st and 2nd point
package strings;

import java.util.ArrayList;

public class AllSubString {
    public static void main(String[] args) {
        String str = "abcd";
        char[] ch = str.toCharArray();
        AllSubString obj = new AllSubString();
//        obj.approach1(ch, ch.length);
//        obj.approach2(str);
        obj.approach3(str);
    }

    private void approach2(String str) {
        int counter =0;
        for(int i=0;i<str.length();i++){
            StringBuilder substr = new StringBuilder();
            for(int j=i;j<str.length();j++){
                substr.append(str.charAt(j));
                counter++;
                System.out.print(substr.toString()+"\n");
            }
        }
        System.out.println("total: "+counter);
    }

    //n3 approach using 3 pointers, no-extra space
    private void approach1(char[] ch, int length) {
        int counter=0;
        // of all length type
        for(int len=1;len<=length;len++){
            //pick start point, if string len=2, start point can be max 2 char before last char
            for(int i=0;i<=length-len;i++){
                int j = i+len-1; //ending point
                //iterator to iterate between i and j
                for(int k=i;k<=j;k++){
                    counter++;
                    System.out.print(ch[k]);
                }
                System.out.println();
            }
        }
        System.out.println("total: "+counter);
    }

    //return in a arraylist
    private void approach3(String str) {
        ArrayList<String> res = new ArrayList<>();
        int n= str.length();
        for(int i=0;i<n;i++){
            for(int j=i+1;j<=n;j++){
                res.add(str.substring(i,j));
            }
        }

        //print list
        System.out.println(res);
    }
}
