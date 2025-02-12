package interview;


public class FindPairOfNumbersWithMaxSum {
    public static int countMaxPairs(int[] arr) {
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        int maxCount = 0;
        int secondMaxCount = 0;

        for (int num : arr) {
            if (num > max) {
                secondMax = max;
                secondMaxCount = maxCount;
                max = num;
                maxCount = 1;
            } else if (num == max) {
                maxCount++;
            } else if (num > secondMax) {
                secondMax = num;
                secondMaxCount = 1;
            } else if (num == secondMax) {
                secondMaxCount++;
            }
        }

        if (maxCount > 1) {
            return (maxCount * (maxCount - 1)) / 2;
        } else {
            return secondMaxCount;
        }
    }

    public static void main(String[] args) {

        int[] arr1 = {1, 2, 1, 2, 0, 2, 1};
        System.out.println(countMaxPairs(arr1)); // Output: 3

        int[] arr2 = {1, 4, 3, 3, 5, 1};
        System.out.println(countMaxPairs(arr2)); // Output: 1

        int[] arr3 = {1,4,2,3,4,1,2,4,1,5};
        System.out.println(countMaxPairs(arr3)); // Output:
    }
}


/*
Source: https://www.geeksforgeeks.org/number-pairs-maximum-sum/
Maria is the event organizer of the Sarah Birthday party. Maria has planned a multilevel game for the kids.
She has planned a level-1 game for kids that is to collect balls in the bucket given a certain time period.
Each pair of kids who has collected the maximum number of balls will be eligible for the level-2 game.
Please help Maria to find out all the pairs of kids who have won level-1 games.

You are given a array arr of number of balls collected by kid i, find out the pairs of kids who have collected
maximum number of balls

Example :
Input : arr[] = {1, 2, 1, 2, 0, 2, 1}
Output : 3
Explanation: The maximum possible pair
sum where i<j is 4, which is given
by 3 pairs, so the answer is 3
the pairs are (2, 2), (2, 2) and (2, 2)


Input : arr[] = {1, 4, 3, 3, 5, 1}
Output : 1
Explanation: The pair 4, 5 yields the
maximum sum i.e, 9 which is given by 1 pair only.


SOLUTION EXPLANATION:
If we take a closer look, we can notice following facts.
Maximum element is always part of solution:
    - If maximum element appears more than once, then result is maxCount * (maxCount – 1)/2.
    We basically need to choose 2 elements from maxCount (maxCountC2).
    - If maximum element appears once, then result is equal to count of second maximum element. We can form a pair with
    every second max and max. Now right the code according to above explanation
* */