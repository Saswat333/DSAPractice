package misc;

public class StringMatching {
    public static int firstOccurrence(String s, String x) {
        int n = s.length();
        int m = x.length();

        // Handle the case where x is empty
        if (m == 0) {
            return 0;
        }

        // Create a pattern array to store the longest prefix-suffix match for each suffix
        int[] pattern = new int[m];
        computeLPSArray(x, m, pattern);

        // Do the KMP algorithm
        int i = 0, j = 0;
        while (i < n) {
            if (s.charAt(i) == x.charAt(j) || x.charAt(j) == '*') {
                i++;
                j++;
                if (j == m) {
                    return i - m;
                }
            } else {
                if (j != 0) {
                    j = pattern[j - 1];
                } else {
                    i++;
                }
            }
        }

        return -1;
    }

    private static void computeLPSArray(String x, int m, int[] pattern) {
        int len = 0;
        pattern[0] = 0;
        int i = 1;

        while (i < m) {
            if (x.charAt(i) == x.charAt(len)) {
                len++;
                pattern[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = pattern[len - 1];
                } else {
                    pattern[i] = 0;
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        String s = "juliasamanthantjulia";
        String x = "ia*a";
        int index = firstOccurrence(s, x);
        System.out.println(index); // Output: 1
    }
}