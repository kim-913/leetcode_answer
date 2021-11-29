package problems;
import java.util.*;
class Solution {
    public int longestAwesome(String s) {
        int[] count = new int[1024];
        int res = 0;
        int mask = 0;
        int n = s.length();
        Arrays.fill(count, n);
        count[0] = -1;
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            mask ^=1 << (c - '0');
            for(int j = 0; j <= 9; j++){
                res = Math.max(res, i - count[mask ^ (1 << j)]);
            }
            res = Math.max(res, i - count[mask]);
            count[mask] = Math.min(count[mask], i);
        }
        return res;
    }
}