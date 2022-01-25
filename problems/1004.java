class Solution {
    public int longestOnes(int[] nums, int k) {
        if (nums.length <= k)
            return nums.length;
        int left = 0;
        int i = 0;
        for (i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                k--;
            if (k < 0) {
                k += nums[left] == 0 ? 1 : 0;
                left++;
            }
        }
        return i - left;
    }
}