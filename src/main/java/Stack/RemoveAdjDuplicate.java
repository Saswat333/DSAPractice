package Stack;

import java.util.Stack;

public class RemoveAdjDuplicate {
    public static void main(String[] args) {
        String inp = "abbaca";
        RemoveAdjDuplicate obj = new RemoveAdjDuplicate();
        String result1 = obj.removeDuplicate1(inp);
        System.out.println(result1);

        String result2 = obj.removeDuplicate2(inp);
        System.out.println(result2);
    }

    //using stringbuilder as stack
    private String removeDuplicate2(String s) {
        StringBuilder sb = new StringBuilder();
        for(char c: s.toCharArray()){
            int size = sb.length();
            //if char at top of string builder same as the same char then remove the char from stringbuilder
            if(size>0 && sb.charAt(size-1) == c){
                sb.deleteCharAt(size-1);
            }
            else{
                return sb.toString();
            }
        }
        return sb.toString();
    }

    private String removeDuplicate1(String s) {
        Stack<Character> stk = new Stack<>();

        for(int i=0;i<s.length();i++){
            if(!stk.isEmpty()){
                if(s.charAt(i)==stk.peek()){
                    stk.pop();
                }
                else{
                    stk.push(s.charAt(i));
                }
            }
            else
                stk.push(s.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        while(!stk.isEmpty()){
            sb.append(stk.pop());
        }
        return sb.reverse().toString();
    }
}
