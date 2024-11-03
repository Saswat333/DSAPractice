package Array;

import java.util.Arrays;

public class Sort012 {
    public static void main(String[] args) {
        Sort012 obj = new Sort012();

        int[] input = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        int[] result = obj.sortArray(input);
        System.out.println(Arrays.toString(result));
    }

    private int[] sortArray(int[] arr) {
        int lo=0, mid=0,hi= arr.length-1;

        while(mid<=hi){
            if(arr[mid]==0){
                swap(arr,lo, mid);
                lo++;mid++;
            } else if (arr[mid]==1) {
                mid++;
            }
            else{
                swap(arr, mid, hi);
                hi--;
            }
        }
        return arr;
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
/*
The idea is to sort the array of size N using three pointers: lo = 0, mid = 0 and hi = N – 1 such that the array is divided into three parts:

arr[0] to arr[lo – 1]: This part will have all the zeros.
arr[lo] to arr[mid – 1]: This part will have all the ones.
arr[hi + 1] to arr[N – 1]: This part will have all the twos.
Traverse over the array till mid <= hi, according to the value of arr[mid] we can have three cases:

- arr[mid] = 0, then swap arr[lo] and arr[mid] and increment lo by 1 because all the zeros are
    till index lo – 1 and move to the next element so increment mid by 1.
- arr[mid] = 1, then move to the next element so increment mid by 1.
- arr[mid] = 2, then swap arr[mid] and arr[hi] and decrement hi by 1 because all the twos are from index
    hi + 1 to N – 1. Now, we don’t move to the next element because the element which is now at index mid can be a 0 and therefore needs to be checked again.

 */