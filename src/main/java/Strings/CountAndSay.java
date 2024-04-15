package Strings;

public class CountAndSay {
    private String countAndSayFun(int n){
        if(n==1) return "1";
        if(n==2) return "11";
        StringBuilder s = new StringBuilder("11"); //previous solution to refer to find next one
        // find next num from 3 as we already returned for 1 and 2
        for(int i=3;i<=n;i++) {
            StringBuilder st = new StringBuilder();
            s.append('&');
            int cntr = 1;
            //interate over previous sequence and find next.
            for(int j=1;j<s.length();j++){
                if(s.charAt(j) != s.charAt(j-1)){
                    st = st.append(cntr);
                    st = st.append(s.charAt(j-1));
                    cntr=1;
                }
                else{
                    cntr++;
                }
            }
            //replace previous sequence with current sequence
            s = st;
        }
        return s.toString();
    }
    public static void main(String[] args) {
        CountAndSay obj = new CountAndSay();
        int n =4;
        String res = obj.countAndSayFun(n);
        System.out.println(res);
    }
}
