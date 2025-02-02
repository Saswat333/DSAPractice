package heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedianStream {
    List<Integer> data;

    public MedianStream() {
        this.data = new ArrayList<>();
    }

    public void addNum(int num){
//        if(data.size()==0)
//            data.add(num);
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
        obj.inputFunctionInsertationSort();
    }

}


/*
If we can sort the data as it appears, we can easily locate the median element. Insertion Sort is one such online algorithm that sorts the data appeared so far.
At any instance of sorting, say after sorting i-th element, the first i elements of the array are sorted. The insertion sort doesn’t depend on future data to sort data
input till that point. In other words, insertion sort considers data sorted so far while inserting the next element. This is the key part of insertion sort that makes it an online algorithm.

However, insertion sort takes O(n2) time to sort n elements. Perhaps we can use binary search on insertion sort to find the location of the next element in O(log n) time.
Yet, we can’t do data movement in O(log n) time. No matter how efficient the implementation is, it takes polynomial time in case of insertion sort.
TC: O(n^2) , Space: O(n)
* */

/*
Collections.binarySearch(array, elem)
Return:
    1. if the elem is present in the array it will return a +ve value of the index
    2. If the elem is not present in the array it will return a -ve value which will be the index value of place where
        can insert the value in the list.
* */