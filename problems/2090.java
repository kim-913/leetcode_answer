package problems;
import java.util.*;
class Solution {
    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        // from left to right and then right to left
        int left = 0;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        if(k >= 2 * n) return res;
        long sum = 0;
        for(int right = 0; right < n; right++){
            sum += nums[right];
            while(right - left > 2 * k){
                sum -= nums[left++];
            }
            // important part
            if(right - left == 2 * k) {
                int avg = (int)(sum / (2 * k + 1));
                res[(right + left) / 2] = avg;
            }
        }
        return res;
    }
}