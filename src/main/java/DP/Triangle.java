package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle {
    public static void main(String[] args) {
        Triangle obj = new Triangle();
        List<List<Integer>> input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(2)));
        input.add(new ArrayList<>(Arrays.asList(3,4)));
        input.add(new ArrayList<>(Arrays.asList(6,5,7)));
        input.add(new ArrayList<>(Arrays.asList(4,1,8,3)));

//        int result = obj.minimumTotalTopDown(input);
//        System.out.println("Result: "+result);

//        int result = obj.minimumTotalButtomUp(input);
//        System.out.println("Result: "+result);

//        int result = obj.minimumTotalButtomUpSpace1DArray(input);
//        System.out.println("Result: "+result);

        int result = obj.minimumTotalButtomUpConstantSpace(input);
        System.out.println("Result: "+result);

    }

    private int minimumTotalButtomUpConstantSpace(List<List<Integer>> triangle) {
        int n = triangle.size();

        //build the dp array button up
        for(int i=n-2;i>=0;i--){
            for(int j=0;j<=i;j++){
                //curVal + min(prev row j &j+1)
                int minSum = Math.min(triangle.get(i+1).get(j),triangle.get(i+1).get(j+1));
                int curVal = triangle.get(i).get(j);
                triangle.get(i).set(j, curVal+minSum);
            }
        }
        return triangle.get(0).get(0);
    }

    private int minimumTotalButtomUpSpace1DArray(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];

        //initialize the dp with the last row of triangle
        for(int i=0;i<n;i++){
            dp[i] = triangle.get(n-1).get(i);
        }

        //build the dp array button up
        for(int i=n-2;i>=0;i--){
            for(int j=0;j<=i;j++){
                //curVal + min(prev row j &j+1)
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j+1]);
            }
        }
        return dp[0];
        //Time= O(n^2), space=O(n)
    }

    private int minimumTotalTopDown(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] memo = new int[n][n];
        return dfsTopDown(0,0,triangle, memo);
        //Time= O(n^2), space=O(n^2)
    }

    private int dfsTopDown(int row, int j, List<List<Integer>> triangle, int[][] dp) {
        if(dp[row][j] != 0)
            return dp[row][j];
        int path = triangle.get(row).get(j);
        if(row<triangle.size()-1){
            path += Math.min(dfsTopDown(row+1, j,triangle,dp), dfsTopDown(row+1,j+1,triangle, dp));
        }
        return dp[row][j] =path;
    }

    private int minimumTotalButtomUp(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n+1][n+1];

        for(int i=n-1;i>=0;i--){
            //each row will have row'th number of elements
            for(int j=0;j<=i;j++){
                //add current value to the min value in the below row in jth or jth +1 col
                dp[i][j] = triangle.get(i).get(j)+Math.min(dp[i+1][j], dp[i+1][j+1]);
            }
        }

        return dp[0][0];
        //Time= O(n^2), space=O(n^2)
    }
}
