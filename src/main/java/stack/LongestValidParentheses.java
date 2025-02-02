package stack;


import java.util.ArrayList;
import java.util.Stack;

public class LongestValidParentheses {
    public static void main(String[] args) {
        LongestValidParentheses obj = new LongestValidParentheses();

        String str = ")()())";
        int result1 = obj.findValidParenthesesApproach1(str);
        System.out.println("Approach-1: Longest valid parentheses length : "+result1);

        int result2 = obj.findValidParenthesesApproach2(str);
        System.out.println("Approach-2: Longest valid parentheses length : "+result2);

    }

    //OPTIMAL APPROACH: USIGN SINGLE STACK AND PARSE, JUST FIND THE MOST RECENT INVALID PARENTHESIS
    private int findValidParenthesesApproach2(String str) {
        Stack<Integer> stk = new Stack<>();
        int max=0;
        stk.push(-1);

        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(ch=='('){
                stk.push(i);
            }
            else{
                //now we have ')'
                stk.pop();
                if(stk.isEmpty()){
                    stk.push(i);
                }
                max = Math.max(max, i-stk.peek());
            }
        }
        return max;
    }

    private int findValidParenthesesApproach1(String str) {
        //remove all valid parenthesis
        Stack<Integer> stk = new Stack<>();
        removeValidParenthesis(str, stk);
        //if stk is empty then there is no invalid parenthesis
        if(stk.isEmpty()){
            return str.length(); //whole input is valid
        }
        ArrayList<Integer> arr = new ArrayList<>();//we will use this arraylist to store all invalid indexs
        formArrayOfInvalidIndexes(arr, stk, str);

        //find the max between two invalid parentheses
        int max=0;
        for(int i=1;i<arr.size();i++){
            int prev = arr.get(i-1);
            int diff = arr.get(i)-prev-1;
            max =Math.max(max, diff);
        }
        return max;
    }

    private void formArrayOfInvalidIndexes(ArrayList<Integer> arr, Stack<Integer> stk, String s) {
        //adding all elem in 0th index will shift the position of all elements to left
        //edge case: add the -1 and length of str as boarder values, if one side doesn't have invalid parenthesis
        arr.add(0, s.length());
        while(!stk.isEmpty()){
            arr.add(0,stk.pop());
        }
        arr.add(0,-1);
    }

    private void removeValidParenthesis(String str, Stack<Integer> stk) {
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(ch=='('){
                stk.push(i);
            }
            else{
                //if we see a ) and the stack is empty then its invalid
                // for '))', for 2nd ) it is invalid and we have to push its index
                if(stk.isEmpty() || str.charAt(stk.peek())==')'){
                    stk.push(i);
                }
                else{
                    stk.pop();
                }
            }

        }
    }
}
/*
Leetcode -32
Given a string containing just the characters '(' and ')', return the length of the longest valid
(well-formed) parentheses substring.
Example 1:
Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".

Example 2:
Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".

Approach-1 logic:
- remove the valid parentheses and keep track of the first and last invalid parentheses index
- now substract those indexes to get the length of the valid paranthese
- take max out of it
edge case: ()()(
there is no invalid paranthesis in left end, so we have to keep (-1 and size of input) in stack in the beginning

Approach-2
- try to keep most recent invalid parenthesis on top of the stack and remove the valid parenthesis from stack
* */