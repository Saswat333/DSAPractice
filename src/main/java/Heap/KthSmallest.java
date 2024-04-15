package Heap;

import java.util.PriorityQueue;

public class KthSmallest {
    public static void main(String[] args) {
        int arr[] = {11, 12, 16, 19, 15, 13}  ;

        kthLargestMaxHeap(arr, 3)  ;
        kthLargestQuickSelect(arr, 3);
//        kth_Smallest_MinHeap(arr, 3)  ;
    }

    private static void kthLargestQuickSelect(int[] arr, int i) {
        return;
    }

    private static void kthLargestMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        int n = arr.length;

        for(int i=0;i<n;i++)
            pq.add(arr[i]);

        int key = k-1;

        while(key>0){
            pq.poll();
            key--;
        }

        System.out.println("kth largest element: "+ pq.peek());
    }

}
