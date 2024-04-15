package DP;

import java.util.Arrays;

//SD main.DP problem7 striver
public class NinjaTraning {
    public static void main(String[] args) {
        // Define the points for each activity on each day
        int[][] points = {{10, 40, 70},
                {20, 50, 80},
                {30, 60, 90}};
        NinjaTraning obj = new NinjaTraning();
        int n = points.length; // Get the number of days
        System.out.println("Result "+obj.ninjaTraining(n, points)); // Calculate and print the maximum point
    }

    private int ninjaTraining(int n, int[][] points) {
        //n= days, 3 activity are there we keep 4 ignoring 0th indx
        int[][] dp = new int[n][4];
        for(int[] row: dp)
            Arrays.fill(row, -1);

        int result = solve(points, dp, n-1, 3); // start with last day and last activity
        return result;
    }

    private int solve(int[][] points, int[][] dp, int day, int prev){
        int curMaxActivity=Integer.MIN_VALUE;
        if(dp[day][prev] != -1)
            return dp[day][prev];
        //base condition
        if(day==0){
            for(int i=0;i<=2;i++){
                if(i!=prev){
                    //current actity point and recursively call the next day activity
//                    int curActivity = points[day][prev] + solve(points, dp, day-1, i);
                    curMaxActivity = Math.max(points[0][i], curMaxActivity);
                }
            }
            return dp[day][prev] = curMaxActivity;
        }

        //loop over each activity and check it shouldn't be prev day activity
        for(int i=0;i<=2;i++){
            if(i!=prev){
                //current actity point and recursively call the next day activity
                int curActivity = points[day][i] + solve(points, dp, day-1, i);
                curMaxActivity = Math.max(curActivity, curMaxActivity);
            }
        }
        return dp[day][prev] = curMaxActivity;
    }
}
//TC: N*4*3
//SPACE: N(recursion stack) + N*4(dp matrix)