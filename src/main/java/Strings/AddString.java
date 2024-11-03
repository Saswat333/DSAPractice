package Strings;

public class AddString {
    public static void main(String[] args) {
        AddString obj = new AddString();
//        String num1 = "12,345";
//        String num2 = "98,765";

        String num1 = "1,000,000";
        String num2 = "98,765";

        String result = obj.addNumbers(num1, num2);
        System.out.println("Sum of "+num1+" and "+num2+" is: "+result);

        //test fibonacci
        int fibo=50;
        String fiboResult = obj.nthFibonacci(fibo);
        System.out.println("Fibonacci of "+fibo+" is: "+fiboResult);

    }

    private String addNumbers(String num1, String num2) {
        if(num1.length() == 0 || num2.length() == 0)
            return "0";

        //remove commas from the input numbers
        num1 = num1.replace(",","");
        num2 = num2.replace(",","");

        //taker length of each input
        int p1 = num1.length()-1;
        int p2 = num2.length()-1;
        StringBuilder res = new StringBuilder();
        int carry=0, base=10;

        while(p1>=0 || p2>=0){
            int s1=0,s2=0, sum=0;
            if(p1>=0)
                s1 = num1.charAt(p1--)-'0';
            if(p2>=0)
                s2=num2.charAt(p2--)-'0';
            sum=s1+s2+carry;
            if(sum>=base){
                carry=1;
                sum = sum-base;
            }
            else{
                carry=0;
            }
            res.append(sum);
        }
        if(carry==1)
            res.append(carry);

        return addCommas(res.reverse().toString());
    }

    private static String addCommas(String str){
        StringBuilder formattedString = new StringBuilder();
        int count=0;

        //add commas after every 3 digit from the right
        for(int i=str.length()-1;i>=0;i--){
            formattedString.append(str.charAt(i));
            count++;
            if(count==3 && i!=0){
                formattedString.append(",");
                count=0;
            }
        }
        return formattedString.reverse().toString();
    }

    // Helper function to format a string with commas in Indian numbering system
    private static String addIndianCommas(String str) {
        StringBuilder formattedResult = new StringBuilder();
        int length = str.length();

        // Handle first three digits
        int firstGroupLength = length % 2 == 0 ? 3 : length % 3;
        if (firstGroupLength == 0) firstGroupLength = 3;

        // Add first group without commas
        formattedResult.append(str.substring(0, firstGroupLength));
        int remainingLength = length - firstGroupLength;

        // Add commas every two digits for the remaining string
        for (int i = firstGroupLength; i < length; i += 2) {
            formattedResult.append(",");
            formattedResult.append(str.substring(i, Math.min(i + 2, length)));
        }

        return formattedResult.toString();
    }

    public String nthFibonacci(int n){
        //handle base case
        if(n==0)
            return "0";
        if(n==1)
            return "1";
        String fib1="0", fib2="1", fibN="";
        for(int i=2;i<=n;i++){
            fibN = addNumbers(fib1, fib2);
            fib1 = fib2;
            fib2=fibN;
        }

        return fibN;
    }

    //handle special chars (like @,&,$) if present in input string with exception and throw the appropriate exception message
    //CAN TRY STRING .CONTAINS() first
    public void validateInput(String num) throws Exception {
        // Regular expression to check for non-digit characters except commas
        if (num.matches(".*[^0-9,].*")) {
            throw new Exception("Invalid input: Special characters found in the input string.");
        }
    }

    // .(dot) = matches any character except line break
    // * quantifier: match 0 or more of the preceding tokens
    // ^ => negate any number from 0-9
    //, (comma) -> matches a character
}

/*
415. Add Strings
Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.
You must solve the problem without using any built-in library for handling large integers (such as BigInteger).
You must also not convert the inputs to integers directly.
Example 1:

Input: num1 = "11", num2 = "123"
Output: "134"
Example 2:

Input: num1 = "456", num2 = "77"
Output: "533"
Example 3:

Input: num1 = "0", num2 = "0"
Output: "0"

--------------------------------------------------
- Use simple technique of reading each number from end and reading the ascii value by substracting with '0'.
- now sum each char, check if there is carry, if yes then add carry at the end
* */