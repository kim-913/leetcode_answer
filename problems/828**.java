package problems;
class Solution {
    // https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/discuss/128952/C%2B%2BJavaPython-One-pass-O(N)

    // solution is too smart to come out
    public int uniqueLetterString(String s) {
        int[][] index = new int[26][2];
        for (int i = 0; i < 26; ++i) Arrays.fill(index[i], -1);
        int res = 0, N = s.length(), mod = (int)Math.pow(10, 9) + 7;
        for (int i = 0; i < N; ++i) {
            int c = s.charAt(i) - 'A';
            res = (res + (i - index[c][1]) * (index[c][1] - index[c][0]) % mod) % mod;
            index[c] = new int[] {index[c][1], i};
        }
        for (int c = 0; c < 26; ++c)
            res = (res + (N - index[c][1]) * (index[c][1] - index[c][0]) % mod) % mod;
        return res;
    }

    // https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/discuss/129021/O(N)-Java-Solution-DP-Clear-and-easy-to-Understand
    public int uniqueLetterString(String S) {
        int res = 0;
        if (S == null || S.length() == 0)
            return res;    
        int[] showLastPosition = new int[128];
        int[] contribution = new int[128];
        int cur = 0;
        for (int i = 0; i < S.length(); i++) {
            char x = S.charAt(i);
            cur -= contribution[x]; 
            contribution[x] = (i - (showLastPosition[x] - 1));
            cur += contribution[x]; 
            showLastPosition[x] = i + 1;
            res += cur;
        }   
        return res;
    }
}