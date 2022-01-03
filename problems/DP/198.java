package problems;
class Solution {
    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        // will always start from index 0 or 1
        // wrong
        if(nums.length == 2) return Math.max(nums[0], nums[1]);
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for(int i = 2; i <= nums.length-1; i++){
            dp[i] = Math.max(nums[i]+dp[i-2], dp[i-1]);
        }
        return dp[nums.length-1];
    }
}