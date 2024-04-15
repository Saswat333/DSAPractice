package graph;

public class FindRotation {
    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2, 3};
        int ans = findKRotation(arr);
        System.out.println("The array is rotated " + ans + " times.");

        int ans1 = findKRotation_easy(arr);
        System.out.println("The array is rotated " + ans1 + " times.");

        int lo = 0, hi= arr.length-1;
        int ans2 = findKRotationEndPivot(arr,lo, hi);
        System.out.println("The array is rotated " + ans2 + " times.");
    }

    private static int findKRotation(int[] arr) {
        int low = 0, high = arr.length-1;
        int indx=0, ans = Integer.MAX_VALUE;
        while(low<=high){
            int mid = (low+high)/2;
            //if completely sorted then break and return ans
            if(arr[low]<=arr[high]){
                //if search space is sorted then the ans will the lowest index elem
                if(arr[low]<ans){
                    ans = arr[low];
                    indx = low;
                }
                break;
            }
            //left sorted
            if(arr[low]<=arr[mid]){
                // ans is minimum of low and ans => at minimum we will have 2 sorted array of size 1 and out of both we
                // have to choose the min, so in both left and right side we compare the lowest elem with min ans.
                if(arr[low]<ans){
                    ans = arr[low];
                    indx = low;
                }
                //eliminate left
                low = mid+1;
            }
            else{
                //right sorted
                if(arr[mid]<ans){
                    ans = arr[mid];
                    indx = mid;
                }
                //eliminate right
                high = mid-1;
            }
        }
        return indx;
    }

    private static int findKRotation_easy(int[] arr) {
        int n = arr.length;

        int lo=0,hi=n-1;

        while(lo<=hi){
            int mid = lo+(hi-lo)/2;
            //acceptance condition
            if(mid<hi && arr[mid]>arr[mid+1]){
                int pivotIndex = mid;
                return pivotIndex+1;//as # rotation is index+1 as index starts from 0
            }
            if(lo<mid && arr[mid-1]>arr[mid]){
                int pivotIndex = mid-1;
                return pivotIndex+1;
            }
            //left
            if(arr[lo]>arr[mid]){
                hi=mid-1;
            }
            else{
                lo=mid+1;
            }
        }
        return -1;
    }

    private static int findKRotationEndPivot(int[] ar, int low, int high) {
        int n=ar.length;
        int pivot=0;
        while(low<=high)
        {
            int mid=(low+high)/2;
            if(ar[mid]>ar[n-1])
                low=mid+1;
            else if(ar[mid]<=ar[n-1])
            {
                high=mid-1;
                pivot=mid;
            }
        }
        return pivot;
    }
}
