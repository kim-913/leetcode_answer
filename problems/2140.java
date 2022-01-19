class Solution {
    private long[] dp;

    public long mostPoints(int[][] questions) {
        dp = new long[questions.length];
        return dfs(questions, 0);
    }

    private long dfs(int[][] questions, int index) {
        // break condition
        if (index >= questions.length)
            return 0;
        if (dp[index] > 0)
            return dp[index];
        int q = questions[index][0], jump = questions[index][1];
        dp[index] = Math.max(dfs(questions, index + 1), q + dfs(questions, index + jump + 1));
        return dp[index];
    }
}