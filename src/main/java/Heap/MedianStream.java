package Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedianStream {
    ArrayList<Integer> store = new ArrayList<>();
    List<Integer> data = new ArrayList<>();

    public int findMedianAprroachFirst(int input){
        store.add(input);
        for(int i=0;i<store.size()-1;i++){
            int lastElem = store.get(store.size()-1);
            if(store.get(i)>lastElem){
                Collections.swap(store, i, store.size()-1);
            }
        }
        System.out.println(store);
        int n = store.size();
        int mid = n/2; // 2
        System.out.println("mid: "+mid);
        int medianValue = 0;
        if(n%2==0){
            medianValue = (store.get(mid)+store.get(mid-1))/2;
        }
        else{
            medianValue = store.get(mid);
        }
        return medianValue;
    }


    public void inputFunctionFirst(){
        int median = 0;
//         int[] arr ={3,2,4,5,2,7,8,3};
        int[] arr = {8,2,5,4};
//        int[] arr ={40,10,100,120,30,40,140,20};
        for(int i=0;i<arr.length;i++){
            median = findMedianAprroachFirst(arr[i]);

            System.out.println("Input: "+arr[i]+ " Median: "+median);
            System.out.println();
        }

    }

    public void addNum(int num){
        System.out.println("Post Array: "+ data);
        int indx = Collections.binarySearch(data, num);
        System.out.println("indx: "+indx);
        if(indx >=0){
            data.add(indx, num);
        }
        else{
            data.add(-indx-1, num);
        }
        System.out.println("Pre Array: "+ data);
    }

    public int findMedian(){
        int len = data.size();
        int mid = data.get(len/2); //mid element

        if(len%2 ==1){
            return mid;
        }
        else{
            int ans = (data.get(len/2 - 1)+mid)/2;
            return ans;
        }
    }

    public void inputFunctionInsertationSort(){
//         int[] arr ={3,2,4,5,2,7,8,3};
//        int[] arr = {8,2,5,4};
        int[] arr ={40,10,100,120,30,40,140,20};

        for(int i=0;i<arr.length;i++){
            addNum(arr[i]);
            int median = findMedian();
            System.out.println("Input: "+arr[i]+ " Median: "+median);
            System.out.println();
        }

    }

    public static void main(String[] args) {
        MedianStream obj = new MedianStream();
//        obj.inputFunctionFirst();

        obj.inputFunctionInsertationSort();
    }

}
