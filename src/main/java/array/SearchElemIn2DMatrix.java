package array;

public class SearchElemIn2DMatrix {
    public static void main(String[] args) {
        SearchElemIn2DMatrix obj = new SearchElemIn2DMatrix();
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int target = 21;
        
        boolean isAvailable = obj.findTarget(matrix, target);
        System.out.println("Is " +target+" Available: "+isAvailable);
    }

    private boolean findTarget(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int row=0, col=m-1;

        while(row<n && col>=0){
            if(matrix[row][col]==target){
                return true;
            }
            else if (target > matrix[row][col]) {
                row++;
            }
            else{
                col--;
            }
        }
        return false;
    }
}
/*
Leetcode 240:

Approach:
Start from point (0, m-1): right top element
Till: row < n && col >= 0
Idea: try to eliminate row or a col in every iteration
If  target < current element : We need the smaller elements to reach the target. But the column is in increasing order
and so it contains only greater elements. So, we will eliminate the column by decreasing the current column value
by 1(i.e. col–) and thus we will move row-wise.

If target > current element : In this case, We need the bigger elements to reach the target. But the row is in
decreasing order and so it contains only smaller elements. So, we will eliminate the row by increasing the current
row value by 1(i.e. row++) and thus we will move column-wise.
time complexity: O(n+m)
space= constant
* */