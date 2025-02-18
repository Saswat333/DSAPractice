package binarysearchtree;

public class ShipCapacity {
    public static void main(String[] args) {
        ShipCapacity obj = new ShipCapacity();

        int[] weights = {1,2,3,4,5};
        int days = 2;
        int result = obj. shipWithinDays(weights, days);

        System.out.println("Minimum Ship Capacity: "+result);

    }

    private int shipWithinDays(int[] weights, int days) {
        int maxWeight = -1, totalWeight = 0;
        //find maxWeight and totalWeight
        for(int weight: weights){
            maxWeight = Math.max(maxWeight, weight);
            totalWeight += weight;
        }

        //binary search on the search space
        int left = maxWeight, right=totalWeight;
        while(left<right){
            int mid = (left+right)/2;
            int daysNeeded = 1, currWeight=0;
            for(int weight:weights){
                if(currWeight +weight >mid){
                    daysNeeded++;
                    currWeight=0;
                }
                currWeight += weight;
            }
            //if days needed is more than given days then increase lower bound
            if(daysNeeded > days){
                left = mid+1;
            }
            else{
                right = mid;
            }
        }
        return left;
    }
}

/*
Leetcode 1011: Capacity to ship packages within d days.

A conveyor belt has packages that must be shipped from one port to another within days days.
The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor
belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped
within days days.
Example 1:
Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15
Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10
Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into
parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.


APPROACH:
eg: [1,2,3,4,5] ship=2, find min capacity.
Observations:
    - instead of number of days its basically how many ships do we need
    - from example 5 ships with 15 capacity can do the job.
    - Capacity range: we need capacity of at least the highest number of the array, to put it in 1 ship atleast
    - the max capacity of ship will be sum of the array.
    - we can search the optimal using binary search.
        - we need to put lo as 5 and max as 15
        - mid 10, so we can put 1,2,3,4 in one ship and 5 in one ship, which satisfies the condition of 2 ships,
            but we need to try to reduce more and check if any lower number is possible.
        - reduce hi by -1 , hi=9 and lo=5...wont work will need 3 ship, so increase low
        - increase low by 1, ...and go on
        - at mid=9, we get the most optimal answer as 1,2,3 in one ship and 4,5 in one ship.
        - keep track of possible capacities using a variable and take min of it in every iteration.

Time: N + NlogS (where S is total sum of the weight)
Space: O(1)

Resource: https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/solutions/3216547/day-53-binary-search-easiest-beginner-friendly-sol/
* */