/*
Problem: Find an element in array such that sum of left array is equal to sum of right array. Exclude the middle elem.
Input: 1 4 2 5 0
Output: 2
Explanation: If 2 is the partition, subarrays are : [1, 4] and [5]

Input: 2 3 4 1 4 5
Output: 1
Explanation: If 1 is the partition, Subarrays are : [2, 3, 4] and [4, 5]
**/
package Array;

public class LeftRightSum {

    public static void main(String[] args) {
        int[] arr={2,3,4,5,1,4,0,2,8};
        LeftRightSum obj = new LeftRightSum();
        int result = obj.findSplitPoint(arr);
        System.out.println("Splitting Point : "+ result);
    }

    public int findSplitPoint(int[] arr) {
        int n = arr.length;
        int leftSum =0, rightSum=0;
        int i=0, j = n-1, result=-1;

        while (i<=j){
            if(leftSum == rightSum && i==j){
                result = arr[i];
                return result;
            }
            else if(leftSum == rightSum && i==j){
                leftSum += arr[i];
                rightSum += arr[j];
            }
            else if(leftSum<rightSum){
                leftSum += arr[i];
                i++;
            }
            else{
                rightSum += arr[j];
                j--;
            }
        }
        return result;
    }
}
