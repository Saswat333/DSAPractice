package Stack;

import java.util.Stack;

public class LargestRectangularHistogram {
    public static void main(String[] args) {
        LargestRectangularHistogram obj = new LargestRectangularHistogram();
        int[] heights = {2,1,5,6,2,3};

        int result = obj.findLargestRectangle(heights);
        System.out.println("Result: "+result);
    }
    //find previos smaller element and then next smaller element for the array and then find the area
    //time= n+n+n =3n
    private int findLargestRectangle(int[] heights) {
        int len = heights.length;
        int[] pse = new int[len];
        int[] nse = new int[len];
        Stack<Integer> stk = new Stack<Integer>();
        //previous smaller elem
        for(int i=0;i<len;i++){
            while(!stk.isEmpty() && heights[i] <= heights[stk.peek()]){
                stk.pop();
            }
            if(stk.isEmpty()){
                pse[i] = -1;
            }
            else{
                pse[i] = stk.peek();
            }
            stk.push(i);
        }
        //next smaller elem
        stk = new Stack<>();
        for(int i=len-1;i>=0;i--){
            while(!stk.isEmpty() && heights[i] <= heights[stk.peek()]){
                stk.pop();
            }
            if(stk.isEmpty()){
                nse[i] = len;
            }
            else{
                nse[i] = stk.peek();
            }
            stk.push(i);
        }

        //actual area cal
        int maxArea = Integer.MIN_VALUE;
        for(int i=0;i<len;i++){
            int height = heights[i];
            int width = nse[i]-pse[i]-1;
            int currentArea = height*width;
            maxArea = Math.max(currentArea, maxArea);

        }
        return maxArea;
    }
}
/*
Approach1:
    - pick one element traverse left and right side of each elem till the element smaller than current and find the
    width.
    - so for each element we will have 2 while loops
        for(int i=0;i<n;i++){
            int left=i, right=i;
            while(a[left]>= ar[i]
                left--;
            while(a[right]>=a[i])
                right++;
            area = (right-left+1)+ a[i]
        }
    - here time complexity is O(n^2)

Approach2: O(n)
    - better approach is using previous smaller elem and next smaller elem
    - find the PSE and NSE for the entire array = (n+n)
    - calculate actual area by height*wedth , height = arr[i] , wedth= nse[i]-pse[i]-1
* */