package graph;

public class RunLengthEncoding {
    public static void main(String[] args) {
        String str = "wwwwaaadexxxxxxywww"; //w4a3d1e1x6y1w3
        RunLengthEncoding obj = new RunLengthEncoding();
        String encoded = obj.encoding(str);
        System.out.print("Encoded: "+encoded);
        System.out.println("");
        String decoded = obj.decode(encoded);
        System.out.print("Decoded: "+decoded);
    }

    private String encoding(String str) {
        int n= str.length();
        StringBuilder ans= new StringBuilder();
        for(int i=0;i<n-1;i++){
            char curr = str.charAt(i);
            int count =1;
            while(i<n-1 && curr == str.charAt(i+1)){
                i++;
                count++;
            }
            ans.append(curr+String.valueOf(count));
        }
        return ans.toString();
    }

    private String decode(String str) {
        int n = str.length();
        if(n==0)
            return "";
        if(n==1)
            return str;
        StringBuilder ans = new StringBuilder();

        for(int i=0;i<n-1;i+=2){
            char curr = str.charAt(i);
            int counter = Character.getNumericValue(str.charAt(i+1));
            int itr=0;
            while(itr<counter){
                ans.append(curr);
                itr++;
            }
        }
        return ans.toString();
    }
}
