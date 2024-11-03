package Array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class NonReaptingNumberInArray {
    public static void main(String[] args) {
        NonReaptingNumberInArray obj = new NonReaptingNumberInArray();
        int[] arr = {1,2,-1,1,3,1};
        obj.findElement(arr);
    }

    private void findElement(int[] arr) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int it: arr){
            hm.put(it, hm.getOrDefault(it, 0)+1);
        }

        for(Map.Entry<Integer, Integer> entry: hm.entrySet()){
            if(entry.getValue()==1){
                System.out.print(entry.getKey()+", ");
            }
        }
    }
}
