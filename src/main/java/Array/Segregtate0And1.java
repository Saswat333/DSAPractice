package Array;

import java.util.Arrays;

public class Segregtate0And1 {
    public static void main(String[] args) {

        int[] arr = {0, 1, 0, 1, 1, 1};
        Segregtate0And1 obj = new Segregtate0And1();
        int[] result1 = obj.approach1(arr);
        System.out.println("Approach1: "+ Arrays.toString(result1));
    }

    private int[] approach1(int[] arr) {
        //only single traversal, no ectra space
        int left=0, right=arr.length-1;

        while(left<right){
            while(arr[left]==0 && left<right){
                left++;
            }
            while(arr[right]==1 && left<right){
                right--;
            }
            if(left<right){
                arr[left]=0;
                arr[right] = 1;
                left++;right--;
            }
        }
        return arr;
    }
}

/*
Approaches
1. We can use 2 loopgs, 1st loop count the 1's and 0's, 2nd loop fill the 1and0 in a new result array for the count time.
2. using single loop and no extra space:
    - using 2 pointer approach
    - i th pointer from left and jth from right
    - if ith pointer is value 0 and left<right then increment i
    - if jth pointer value is 1, left<right then decrement j
    - if not then either ith pointer found a 1 or jth pointer found a 0
    - swap them or just make arr[i]=0 and arr[j]=1, increment the pointer
* */