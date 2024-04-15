//

package interview;

public class RiceBagAmazon {
    public boolean isPerfectSquare(int n){
        int lo =1, hi=n;

        while(lo<=hi){
            int mid = lo+(hi-lo)/2;
            int sqrtMid = mid*mid;
            if(sqrtMid == n){
                return true;
            }
            else if(sqrtMid>n){
                hi = (int)mid-1;
            }
            else{
                lo = (int)mid+1;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        RiceBagAmazon obj = new RiceBagAmazon();
        int[] inp = {3,9,4,2,16};

        System.out.println(obj.isPerfectSquare(9));
    }
}
