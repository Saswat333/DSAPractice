package Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoom2 {
    public static void main(String[] args) {

    }

    public int minMeetingRoom(int[][] intervals){
        if(intervals == null || intervals.length == 0) return 0;
        //Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int room = 0;
        for(int i = 0; i < intervals.length; i++){
            pq.offer(intervals[i][1]);
            if(intervals[i][0] < pq.peek())  room++;
            else{
                pq.poll();
            }
        }
        return room;
    }
}




/*
Given an array of meeting time intervals consisting of start and
end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
For example, Given [[0, 30],[5, 10],[15, 20]], return 2.


SOLUTION:
Approach1:
https://github.com/Seanforfun/Algorithm-and-Leetcode/blob/master/leetcode/253.%20Meeting%20Rooms%20II.md
Sort the intervals according to the starting time.
Use a minHeap(pq) to save the endTime for all intervals according to the order of start time.
Add end time to the pq.
    if cur start time < pq.peek() => means current start time is before first ending time, which means we must have a new room.
    if cur start time >= pq.peek() => means we can use this room for the meeting, we poll out the the original
    period and add current period to the pq(Means we update the room with the new meeting).

* */