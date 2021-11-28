package problems;
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        // dynamic programming from left to right and then right to left
        // from left to right
        res[0] = 1;
        for(int i = 1; i < n; i++){
            res[i] = res[i - 1] * nums[i - 1];
        }
        // from right to left
        int right = 1;
        for(int i = n - 1; i >= 0; i--){
            res[i] = right * res[i];
            right = right * nums[i];
        }
        return res;
    }
}