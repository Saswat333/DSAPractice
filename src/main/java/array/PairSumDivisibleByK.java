package array;

//This problem is different than 2 sum as the sum of the pair have to be divisible by k.
public class PairSumDivisibleByK {
    public static void main(String[] args) {
        PairSumDivisibleByK obj = new PairSumDivisibleByK();
        int inp[] = { 2, 2, 1, 7, 5, 3 };
        int n = 6;
        int K = 4;
//        System.out.print(obj.countKdivPairs(inp, n, K));
        System.out.println(obj.countPairs(inp, K));
    }

    private int countKdivPairs(int[] inp, int n, int k) {
        //create a freq array to count occurrences of all reminders when divided by k
        int[] freq = new int[k];
        int ans=0;
        for(int i=0;i<n;i++){
            int rem1 = inp[i] %k;
            if(rem1 !=0){
                int complimentRem = k-rem1;
                ans += freq[complimentRem];
            }
            else{
                ans += freq[0];
            }
            freq[rem1]++;
        }
        return ans;
    }

    public long countPairs(int[] nums, int k) {
        int n = nums.length;
        int[] freq = new int[k];
        long ans=0;
        for(int i=0;i<n;i++){
            int rem1 = nums[i] %k;
            if(rem1 !=0){
                int complimentRem = k-rem1;
                ans += freq[complimentRem];
            }
            else{
                ans += freq[0];
            }
            freq[rem1]++;
        }
        return ans;
    }
}

/*
GFG: https://www.geeksforgeeks.org/count-pairs-in-array-whose-sum-is-divisible-by-k/
(its not same as leetcode 2183, which asks if inp[i]*inp[j] is multiple of k)
IDEA:
Given an array A[] and positive integer K, the task is to count the total number of pairs in the array whose sum is
divisible by K.

Input : A[] = {2, 2, 1, 7, 5, 3}, K = 4
Output : 5
Explanation :
There are five pairs possible whose sum
is divisible by '4' i.e., (2, 2),
(1, 7), (7, 5), (1, 3) and (5, 3)

Input : A[] = {5, 9, 36, 74, 52, 31, 42}, K = 3
Output : 7

IDEA:
- Logic: for k =4, eg. 5+3 = 8 %4=0, here 5%4=1 and 3mod4=3, sum of the mode value =k [reminder are complimentary]
- or if both the number mods value are 0 then also it will be divisible by k
- here we have to use hashing to find the complimentary reminder for the current element
- when we first visit the element we have to find its modular value and increment the count of mod value in freq array.
- so we take array FREQ of size k-1, which will be a bucket to store the input x in the freq bucket
* */