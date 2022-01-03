class Solution {
    public int jump(int[] nums) {
        int jump = 0;
        int farthest = 0;
        int curJumpEnd = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, nums[i] + i);
            if (i == curJumpEnd) {
                curJumpEnd = farthest;
                jump++;
            }
        }
        return jump;
    }
}