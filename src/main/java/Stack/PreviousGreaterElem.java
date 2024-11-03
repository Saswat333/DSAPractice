package Stack;

import java.util.Arrays;
import java.util.Stack;

public class PreviousGreaterElem {
    public static void main(String[] args) {
        PreviousGreaterElem obj = new PreviousGreaterElem();
        int arr[] = {1, 3, 0, 2, 5};
        int n = arr.length;
        int[] result = obj.findPreviousLargerElem(arr, n);
        System.out.println(Arrays.toString(result));
    }

    private int[] findPreviousLargerElem(int[] arr, int n) {
        int[] result = new int[n];
        Stack<Integer> stk = new Stack<>();

        for(int i=0;i<n;i++){
            while(!stk.isEmpty() && stk.peek()<=arr[i]){
                stk.pop();
            }
            if(stk.isEmpty()){
                result[i] = -1;
            }else{
                result[i] = stk.peek();
            }
            stk.push(arr[i]);
        }
        return result;
    }
}
