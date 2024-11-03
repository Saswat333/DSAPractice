package greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class CourseScheduler3 {
    public static void main(String[] args) {
        int[][] courses = {{100,200},{200,1300},{1000,1250},{2000,3200}};
        CourseScheduler3 obj = new CourseScheduler3();

        int result = obj.scheduleCourse(courses);
        System.out.println("Result : "+result);
    }

    private int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a,b)->a[1]-b[1]); //sort asc based on end time
        //max-heap
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        int total = 0;
        for(int[] course: courses){
            int duration =course[0];
            int endTime = course[1];
            pq.offer(duration);
            total += duration;
            if(total>endTime && pq.size()>0){
                total -= pq.poll();
            }
        }
        return pq.size();
    }
}
//Time = N*logN [nlogn +