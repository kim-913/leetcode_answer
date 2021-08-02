import java.util.*;
public class solution_7_29 {
    

    public static void main(String[] args) {
        
    }

    // LeetCode 62 Unique Path
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        //dp[i][j] records how many ways to get from (0,0) to (i,j)
        // dp[0][0] = 1;
        // dp[1][0] = 1;
        // dp[0][1] = 1;
        // dp[1][1] = 1;
        // base case, memorize!!!!!!, important
        for(int[] arr : dp) {
            Arrays.fill(arr, 1);
        }
        return dfs(m,n, dp, 0, 0);
        
    }
    
    public static int dfs(int m, int n, int[][] dp, int i, int j){
        if(i == m && n == j) return 1;
        for(int row = 1; row < m; row++){
            for(int col = 1; col < n; col++){
                dp[row][col] = dp[row-1][col] + dp[row][col-1];
            }
        }
        return dp[m-1][n-1];
    }

    // LeetCode 55. Jump Game
    public boolean canJump(int[] nums) {
        // dp[i] records whether it can get to the current index or not
        boolean[] dp = new boolean[nums.length];
        // can always get to index 0
        dp[0] = true;
        for (int i = 0; i < nums.length; i++){
            if (dp[i]){
                //Well mark where we can jump from here
                int j = i+nums[i];
                while (j > 0){
                    if (j < nums.length) dp[j] = true;
                    j--;
                }
            }
        }
        return dp[nums.length-1];
        // int availableJumps = nums[0];
        // for (int i = 1; i < nums.length; i++) {
        //     if (availableJumps == 0) return false;
        //     availableJumps = Math.max(availableJumps--, nums[i]);
        // }
        // return true;
    }
}
