package strings;

public class MultiplyTwoStringInteger {
    public static void main(String[] args) {
        String s1 = "120";
        String s2 = "113";

        MultiplyTwoStringInteger obj = new MultiplyTwoStringInteger();
        String result = obj.mulitplyString(s1,s2);
        System.out.println("Result: "+result);
    }

    private String mulitplyString(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();

        if(len1==0 || len2==0)
            return "0";
        int[] result = new int[len1+len2];

        int indx1 =0, indx2=0;

        //go from right to left
        for(int i=len1-1;i>=0;i--){
            int carry=0;
            int n1 = num1.charAt(i)-'0';
            indx2 = 0;
            //multiply with other number
            for(int j=len2-1;j>=0;j--){
                int n2 =num2.charAt(j)-'0';
                int sum = n1*n2+result[indx1+indx2]+carry;
                carry = sum/10;
                result[indx1+indx2] = sum%10;
                indx2++;
            }
            //store the carry for next call
            if(carry>0)
                result[indx1+indx2] +=carry;
            indx1++;
        }

        //refine the result
        int i=result.length-1;
        while(i>=0 && result[i]==0)
            i--;
        //if i==-1, then aall were 0 or one of them were 0 return 0
        if(i==-1)
            return "0";
        StringBuilder res = new StringBuilder();
        while(i>=0){
            res.append(result[i--]);
        }

        return res.toString();
    }
}
