package Heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight {
    public static void main(String[] args) {
        int[] input = {2,7,4,1,8,1};
        LastStoneWeight obj = new LastStoneWeight();

        int result = obj.calculate(input);
        System.out.println(result);
    }

    private int calculate(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int it: arr){
            pq.add(it);
        }

        while(pq.size()>0){
            if(pq.size()==1)
                return pq.poll();
            int y = pq.poll();
            int x = pq.poll();

            if(x!=y){
                y -= x;
                pq.add(y);
            }
        }
        return 0;
    }
}
