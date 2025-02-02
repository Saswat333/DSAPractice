package stack;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperature {
    public static void main(String[] args) {
        //similar to next greater element
        int[] temp  = {73,74,75,71,69,72,76,73};
        DailyTemperature obj = new DailyTemperature();
        int[] result = obj.findNextTemperature(temp);
        System.out.println(Arrays.toString(result));
    }

    private int[] findNextTemperature(int[] temp) {
        int n = temp.length;
        int[] ans = new int[n];
        Stack<Integer> stk = new Stack<>();

        for(int i=0;i<n;i++){
            while(!stk.isEmpty() && temp[i]>temp[stk.peek()]){
                int indx = stk.pop();
                ans[indx] = i-indx;
            }
            stk.push(i);
        }

        return ans;
    }
}
/*
Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.



Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
* */