/**
 * solution_7_28
 */
import java.util.*;
public class solution_7_27 {

    public static void main(String[] args) {
        

    }

    // LeetCode 322. Coin Change
    public int coinChange(int[] coins, int amount) {
        //dp[0] fewest number of coins needed to make to 0 cents
        //... dp[n] fewest number of coins needed to make to n cents
        int[] dp = new int[amount+1];
        
        // fill it with something invalid cuz the array is initialized to all 0
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for(int i = 0; i <= amount; i++){
            for(int j = 0; j < coins.length; j++){
                if(coins[j] <= i) {
                    // important
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                }
            }
        }
        //if we've modified dp, return the smallest, if not, return -1
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // LeetCode 152. Maximum Product Subarray
    public int maxProduct(int[] nums) {
        //dp[i] represents the maximum product we can have for the ith index
        /*
        int[] dp = new int[nums.length];
        if(nums.length == 1) return nums[0];
        dp[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            dp[i] = Math.max(dp[i-1]*nums[i], nums[i]);
        }
        return dp[nums.length-1];
        */
        
        // since we may have the case of even number of negative number, we need to also keep track of the min
        int max = nums[0];
        int min = nums[0];
        int res = nums[0];
        for(int i = 1; i < nums.length; i++){
            int temp = Math.max(nums[i] * max, Math.max(nums[i]*min, nums[i]));
            min = Math.min(nums[i] * max, Math.min(nums[i]*min, nums[i]));
            
            max = temp;
            res = Math.max(max, res);
        }
        return res;
    }

    // LeetCode 300. Longest Increasing Subsequence
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        if(nums.length == 1) return 1;
        int max = 1;
        Arrays.fill(dp, 1);
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
}