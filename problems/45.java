package problems;

class Solution {
    public int jump(int[] nums) {
        int res = 0, farthest = 0, currentJumpEnd = 0;
        for(int i = 0; i < nums.length - 1; i++){
            farthest = Math.max(farthest, i + nums[i]);
            // if current jump is to the end, need to make another jump
            if(i == currentJumpEnd) {
                res++;
                currentJumpEnd = farthest;
            }
        }
        return res;
    }
}