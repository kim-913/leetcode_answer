package problems;
import java.util.*;
// similar to 1371, 1915 
// https://leetcode.com/problems/find-longest-awesome-substring/discuss/779893/C%2B%2BJavaPython3-with-picture-(similar-to-1371)
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