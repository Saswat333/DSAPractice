package Strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class ShortestUncommonSubstring {
    public static void main(String[] args) {
        ShortestUncommonSubstring obj = new ShortestUncommonSubstring();
        String[] arr = {"cab","ad","bad","c"};
        String[] res = obj.findSubstring(arr);
        System.out.println(Arrays.toString(res));
    }

    private String[] findSubstring(String[] arr) {
        String[] res = new String[arr.length];
        int ctr=0;
        HashMap<String, Integer> hm = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            String s = arr[i];
            HashSet<String> hs = new HashSet<>();
            //find all subsets and put it into hashmap
            for(int j=0;j<arr[i].length();j++){
                for(int k=j+1;k<=arr[i].length();k++){
                    hs.add(s.substring(j,k));
                }
            }
            for(String str: hs){
                hm.put(str, hm.getOrDefault(s,0)+1);
            }
            System.out.println("Original HM:"+hm);
        }
        for(String str: arr){
            //check condition
            String shortest = "";
            for(int j=0;j<str.length();j++){
                for(int k=j+1;k<=str.length();k++){
                    String subStr = str.substring(j,k);
                    System.out.println("Substring:"+subStr);
                    System.out.println(hm);
                    if(hm.get(subStr)==1 &&
                            (shortest.equals("")||subStr.length()<shortest.length()||
                                    (subStr.length()==shortest.length()&&subStr.compareTo(shortest)<0))){
                        shortest = subStr;
                    }
                }

            }
            res[ctr++] = shortest;
        }
        return res;
    }
}

/* ## Bruteforce
        1. for each array element, find all subtring of each element
        2. for each substring
            a. iterate over each every array element, if j==i, means its same elem continue(skip)
            b. loop: check if substring i is not present in any of the substring of current arr elem j
            c. if we get have the smallest substring and should be lexicographically sorted

    Better:
        - precompute all substring and store it in a hashmap with the freq. As the map will have substring of all the
            array elem, we have to keep freq to know if it has occured only for the element of for any other elem.
        - if freq of the substring is 1 then it has only occured in 1 elem, else it might be present somewhere else

        checking condition when you get a substring:(shortest has stored any substring if already found for that elem)
        - the substring should be only present 1 time in map &&
        - (shortest should be empty) or (if there is elem in shortest shortest.length > curSubstring.length)
        - if there is elem in shortest btu its len == curSubstring len && lexicographically shortest should be selected

 */