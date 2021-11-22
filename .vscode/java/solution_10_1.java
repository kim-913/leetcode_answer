import java.util.*;

public class solution_10_1 {

    public static void main(String[] args) {

    }

    // LC 209. Minimum Size Subarray Sum
    public int minSubArrayLen(int target, int[] nums) {
        // sum records the current sum
        int curSum = 0;
        int left = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            // determine if the curSum is greater than the target, if is, determine the mim
            // length first and then pop out the left one
            // need to notice here that the condition has to be greater than or equal to
            while (curSum >= target) {
                res = Math.min(res, i - left + 1);
                curSum -= nums[left];
                left++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    // LC 56 Merge intervals
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> res = new ArrayList<>();
        if (intervals.length <= 1)
            return intervals;
        int[] cur = intervals[0];
        res.add(cur);
        for (int i = 0; i < intervals.length; i++) {
            int cur_begin = cur[0];
            int cur_end = cur[1];
            int next_begin = intervals[i][0];
            int next_end = intervals[i][1];
            if (next_begin <= cur_end) {
                cur[1] = Math.max(cur_end, next_end);
            } else {
                cur = intervals[i];
                res.add(cur);
            }
        }
        return res.toArray(new int[res.size()][2]);
    }

    // LC 695. Max Area of Island
    int res = 0;

    public int maxAreaOfIsland(int[][] grid) {
        // visited = new boolean[grid.length][grid[0].length];
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                    max = Math.max(max, res);
                    res = 0;
                }
            }
        }
        return max;
    }

    public void dfs(int[][] grid, int x, int y) {
        // base case
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0)
            return;
        // mark current cell as seen
        grid[x][y] = 0;
        res++;
        // visited[x][y] = true;
        dfs(grid, x + 1, y);
        dfs(grid, x - 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x, y - 1);
    }

    // LC 394. Decode String
    public String decodeString(String s) {
        Stack<Integer> count = new Stack<>();
        Stack<StringBuilder> build_String = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                k = k * 1 + c - '0';
            } else if (c == '[') {
                count.push(k);
                build_String.push(cur);
                cur = new StringBuilder();
                k = 0;
            } else if ((c == ']')) {
                StringBuilder decoded = build_String.pop();
                for (int currentK = count.pop(); currentK > 0; currentK--) {
                    decoded.append(cur);
                }
                cur = decoded;
            } else {
                cur.append(c);
            }
        }
        return cur.toString();
    }
}
