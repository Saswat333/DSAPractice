package Strings;

public class ValidPalindrome {
    public static void main(String[] args) {
        ValidPalindrome obj = new ValidPalindrome();

        String s= "A man, a plan, a canal: Panama";
        boolean value = obj.isPalindrome(s);
        System.out.println("Result: "+value);


    }

    private boolean isPalindrome(String s) {
        int n = s.length();
        int left=0,right=n-1;

        while(left<right){
            char l=s.charAt(left), r=s.charAt(right);
            if(!Character.isLetterOrDigit(l))
                left++;
            else if(!Character.isLetterOrDigit(r))
                right--;
            else if (Character.toLowerCase(l) != Character.toLowerCase(r)) {
                return false;
            }
            else {
                left++; right--;
            }
        }
        return true;
    }
}
