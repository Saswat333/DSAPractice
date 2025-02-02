package array;

import java.util.HashSet;

public class SingleNumber {
    public static void main(String[] args) {
        int[] input = {4,1,2,1,2,8,0,4,0};
//        int[] input = {4,1,2,1,2};
        SingleNumber obj = new SingleNumber();

        int result = obj.findSingleNumber(input);
        System.out.println("[Hashset] Non repeating number: "+result);

        int result1 = obj.findSingleNumberXOR(input);
        System.out.println("[XOR] Non repeating number: "+result1);
    }

    private int findSingleNumber(int[] input) {
        HashSet<Integer> set = new HashSet<Integer>();

        for(Integer it: input){
            if(!set.contains(it)){
                //element not present in the set
                set.add(it);
            }
            else{
                //if element present in the set
                set.remove(it);

            }
        }
        int result=0;
        for(Integer it: set){
            result=it;
        }
        return result;
    }

    // XOR PROPERTY
    // XOR of a number with same number is 0, xor of a number with 0 is the same number
    private int findSingleNumberXOR(int[] input){
        int num=0;
        for(int it:input){
            num = num ^ it;
        }
        return num;
    }
}

/*
Leetcode 136:

Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

You must implement a solution with a linear runtime complexity and use only constant extra space.



Example 1:

Input: nums = [2,2,1]
Output: 1
Example 2:

Input: nums = [4,1,2,1,2]
Output: 4

* */
