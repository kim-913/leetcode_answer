import java.util.*;
public class solution_8_2 {
    
    public static void main(String[] args) {
        
    }


    // LeetCode 73. Number of Longest Increasing Subsequence
    public static int findNumberOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        // initialize to 1s
        Arrays.fill(dp, 1);
        // Use an array of how many of such length
        int[] count = new int[nums.length];
        Arrays.fill(count, 1);
        int maxLen = 1;
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    if(dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if(dp[j] + 1 == dp[i]){
                        count[i] += count[j];
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        int res = 0; 
        for(int i = 0; i < dp.length; i++){
            if(dp[i] == maxLen) res+=count[i];
        }
        return res;
    }

    // LeetCode 98. Partition to K Equal Sum Subsets
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums == null ||nums.length == 0){
            return false;
        }
        int sum = 0;
        for(int num: nums){
            sum += num;
        }
        if(sum % k != 0){
            return false;
        }
        Arrays.sort(nums);
        int[] visited = new int[nums.length];
        return canPartition(nums, visited, 0, k, 0, 0, sum/k);
    }
    
        public static boolean canPartition(int[] nums, int[] visited, int start_index,                                       int k, int cur_sum, int cur_num, int target){
        if(k==1)return true;
        // base case
        if(cur_sum == target && cur_num>0){
            return canPartition(nums, visited, 0, k-1, 0, 0, target);
        }
        for(int i = start_index; i<nums.length; i++){
            if(visited[i] == 0){
                visited[i] = 1;
                if(canPartition(nums, visited, i+1, k, cur_sum + nums[i],                                         cur_num++, target)){
                    return true;
                } else {
                    visited[i] = 0;
                }
            }
        }
        return false;
    }

    // LeetCode 416. Partition Equal Subset Sum
    public static boolean canPartition(int[] nums) {
        int total = 0;
        for(int num: nums) total+=num;
        if(total % 2 == 1) return false;
        Map<String, Boolean> map = new HashMap<>();
        return dfs(nums, 0, 0, total, map);
    }
    
    public static boolean dfs(int[] nums, int sum, int cur, int total, Map<String, Boolean> map){
        // current state -> if we've computed current index of current sum
        String cur_state = cur + "" + sum;
        if(map.containsKey(cur_state)) return map.get(cur_state);
        if(sum == total / 2) return true;
        if(sum > total / 2 || cur >= nums.length) return false;
        // store into map
        boolean recursion = dfs(nums,sum, cur + 1, total, map) || dfs(nums, sum + nums[cur], cur+1, total, map);
        map.put(cur_state, recursion);
        return recursion;
    }
}