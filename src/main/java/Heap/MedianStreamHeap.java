package Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class MedianStreamHeap {
    PriorityQueue<Integer> maxHeap ;
    PriorityQueue<Integer> minHeap ;
    private boolean even = true;
    public MedianStreamHeap() {
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder()); //smaller side
        this.minHeap = new PriorityQueue<>(); //larger side
    }

    public void addNum(int num){
        //we can keep one extra elem on smaller side, so we can pop the max in 0(1) time from smaller side
        if(even){
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        }
        else{
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
        even = !even;
    }

    public double findMedian(){
        //if even is true means till now we have even number of elems in array
        //even = true mean, current size is even, next index is odd
        if(even){
            return (maxHeap.peek()+minHeap.peek())/2.0;
        }
        else{
            return maxHeap.peek();
        }
    }

    public void inputFunctionHeap(){
//         int[] arr ={3,2,4,5,2,7,8,3};
//        int[] arr = {8,2,5,4};
        int[] arr ={40,10,100,120,30,40,140,20};

        for(int i=0;i<arr.length;i++){
            addNum(arr[i]);
            double median = findMedian();
            System.out.println("Input: "+arr[i]+ " Median: "+median);
            System.out.println();
        }

    }

    public static void main(String[] args) {
        MedianStreamHeap obj = new MedianStreamHeap();
        obj.inputFunctionHeap();
    }
}
