package array;

public class MaxProductSubarray {
    public static void main(String[] args) {
        MaxProductSubarray obj = new MaxProductSubarray();
//        int[] arr = {2,3,-2,4};
//        int[] arr= {2,-1,2,3,0,-2,4};
        int[] arr = {-2,0,-1};
        int product = obj.findMaxProductApproach2(arr);
        System.out.println("Product: "+product);

        int[] result = obj.findMaxProductIndex(arr);
        System.out.println("Start index= "+result[0]+" End index: "+result[1]);
    }

    private int findMaxProduct(int[] nums) {
        if(nums==null || nums.length==0)
            return 0;

        int n = nums.length;
        int leftProd = 1, rightProd=1;
        int maxProd = Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            leftProd = leftProd * nums[i];
            rightProd = rightProd * nums[n-1-i];

            maxProd = Math.max(maxProd, Math.max(leftProd, rightProd));

            //reset product to 1 if it becomes 0
            if(leftProd==0)
                leftProd=1;
            if(rightProd==0)
                rightProd=1;
        }
        return maxProd;
    }

    //same logic but calculated separately
    private int findMaxProductApproach2(int[] nums){
        if(nums==null || nums.length==0)
            return -1;

        int n = nums.length;
        int leftProd = 1, rightProd=1;
        int maxProd = Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            leftProd = leftProd * nums[i];
            if(leftProd>maxProd)
                maxProd=leftProd;
            if(leftProd==0)
                leftProd=1;
        }

        for(int i=n-1;i>=0;i--){
            rightProd = rightProd * nums[i];
            if(rightProd>maxProd)
                maxProd=rightProd;
            if(rightProd==0)
                rightProd=1;
        }
        return maxProd;
    }

    //for index we have to calculate separately
    private int[] findMaxProductIndex(int[] nums) {
        if(nums==null || nums.length==0)
            return new int[]{-1,-1};

        int n = nums.length;
        int leftProd = 1, rightProd=1;
        int maxProd = Integer.MIN_VALUE;
        int start=0, end=0, tempStart=0;

        //left to right
        for(int i=0;i<n;i++){
            leftProd *= nums[i];
            if(leftProd>maxProd){
                maxProd = leftProd;
                //we have to save the start variable in variable
                //initially tempStart for prefix will be 0, but if we have 0 then we have to restart the index count
                start = tempStart;
                end = i;
            }
            if(leftProd==0){
                leftProd=1;
                tempStart=i+1;
            }
        }

        tempStart=n-1;//reset tempStart for right to left pass

        //Right to left pass
        for(int i=n-1;i>=0;i--){
            rightProd *= nums[i];

            if(rightProd>maxProd){
                maxProd = rightProd;
                //in right to left first we get end and then start
                //by the time we found a max product i will already be in start,we have to save the tempStart
                // as we would have already pass that value
                start=i;
                end=tempStart;
            }
            if(rightProd==0){
                rightProd=1;
                tempStart = i-1;
            }
        }
        return new int[]{start, end};
    }
}
