package stack;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElem {
    public static void main(String[] args) {
        NextGreaterElem obj = new NextGreaterElem();
        int[] arr = {3,10,5,1,15,10,7,6}; // 10,15,15,15,-1,-1,-1,-1
//        int arr[] = {1, 3, 0, 2, 5};
        int n = arr.length;
        int[] result = obj.findNextGreaterElem(arr, n);
        System.out.println(Arrays.toString(result));
    }

    private int[] findNextGreaterElem(int[] arr, int n) {
        int[] result = new int[n];
        Stack<Integer> stk = new Stack<>();

        for(int i=n-1;i>=0;i--){
            while(!stk.isEmpty() && arr[i] >= stk.peek()){
                stk.pop();
            }
            if(stk.isEmpty()){
                result[i] = -1;
            }
            else{
                result[i] = stk.peek();
            }
            stk.push(arr[i]);
            System.out.println(stk);
        }
        return result;
    }
}
