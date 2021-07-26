import java.util.*;
public class solution_7_5 {
    public static void main(String[] args) {
        
    }
    // LeetCode 53. Maximum Subarray
    public int maxSubArray(int[] nums) {
        /*
        if(nums.length == 1) return nums[0];
        int dp[] = new int[nums.length];
        //int sum = nums[0];
        int sumAll = 0;
        for(int i = 0; i < nums.length; i++){
            int sum = nums[i];
            sumAll = sum;
            for(int j = i+1; j < nums.length; j++){
                sumAll += nums[j];
                sum = Math.max(sum, sumAll);
            }
            dp[i] = sum;
        }
        int res = Integer.MIN_VALUE;
        for(int num: dp){
            res = Math.max(num, res);
        }
        return res;
    }
}
*/
        if(nums.length == 1){
            return nums[0];
        }
        int[] dp = new int[nums.length];
        int max = nums[0];
        dp[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
            max = Math.max(dp[i], max);
        }
        return max;
    }

    // LeetCode: 303. Range Sum Query - Immutable
    private int[] input;
    public void NumArray(int[] nums) {
        
        input = nums;
    }
    public int sumRange(int left, int right) {
        int sum = 0;
        for(int i = left; i <= right; i++){
            sum += input[i];
        }
        return sum;
    }

    // LeetCode: 338. Counting Bits
    public int[] countBits(int n) {
        int[] res = new int[n+1];
        res[0] = 0;
        for(int i = 0; i <= n; i++){
            if(i % 2 == 0) res[i] = res[i/2];
            else res[i] = res[i/2]+1;
        }
            /*
            res[i] = countN(i);
        }
        return res;
    }
    public int countN(int num){
        if(num == 0) return 0;
        int number = 0;
        int sum = 0;
        while(num > 0){
            if(num%2 == 1) sum++;
            num = num/2;
        }
        return sum;
        
    }
    */
        return res;
    }
}
