package BinaryTree;

public class MinTimeToCompleteTrip {
    public static void main(String[] args) {
        int[] time = {1,2,3};
        int totalTrips = 5;

        MinTimeToCompleteTrip obj = new MinTimeToCompleteTrip();
//        long result = obj.minimumTimeBruteForce(time, totalTrips);
        long result = obj.minimumTime(time, totalTrips);
        System.out.println("Minimum time to complete trip: "+ result);
    }

//gradually increase the time and check how many trips can be possible in that time range
    private long minimumTimeBruteForce(int[] time, int totalTrips) {
        int len =time.length;
        int tripsDone=0, t=0;
        int[] trip = new int[len]; //keeps track of number of trips done for time 't'
        while(tripsDone<totalTrips){
            t++;
            for(int i=0;i<len;i++){
                trip[i] = (trip[i]+1)%time[i];
                if(trip[i]==0){
                    tripsDone++;
                }
            }
        }
        return t;
    }

    private long minimumTime(int[] time, int totalTrips){
        //idea is to do a binary search on the range of trips from 1 to MAX, instead of increasing trips by 1 every time.
        long lo = 1, hi = Long.MAX_VALUE;
        long ans=0;
        while(lo<=hi){
            long mid = lo+(hi-lo)/2;
            long tripDone=0;

            if(isPossible(time, mid, totalTrips)){
                ans = mid;
                hi = mid-1;
            }
            else{
                lo = mid+1;
            }
        }
        return ans;
    }

    private boolean isPossible(int[] time, long givenTrips, int totalTrips) {
        //find if it's possible to do total trips that the givenTrips

        long tripCount = 0;
        for(int t:time){
            tripCount += givenTrips/t;
        }
        if(tripCount>=totalTrips){
            return true;
        }
        return false;
    }
}


/*
Leetcode : 2187
You are given an array 'time', where time[i] denotes the time taken by the ith bus to complete one trip.

Each bus can make multiple trips successively; that is, the next trip can start immediately after completing the current trip.
Also, each bus operates independently; that is, the trips of one bus do not influence the trips of any other bus.

You are also given an integer totalTrips, which denotes the number of trips all buses should make in total.
Return the minimum time required for all buses to complete at least totalTrips trips.

Example 1:
Input: time = [1,2,3], totalTrips = 5
Output: 3
Explanation:
- At time t = 1, the number of trips completed by each bus are [1,0,0].
  The total number of trips completed is 1 + 0 + 0 = 1.
- At time t = 2, the number of trips completed by each bus are [2,1,0].
  The total number of trips completed is 2 + 1 + 0 = 3.
- At time t = 3, the number of trips completed by each bus are [3,1,1].
  The total number of trips completed is 3 + 1 + 1 = 5.
So the minimum time needed for all buses to complete at least 5 trips is 3.
* */