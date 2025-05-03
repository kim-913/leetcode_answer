import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaximizePathSum {

    int[][] grid;
    int[][] memo;
    Map<String, List<Integer>> path;
    int n;

    private int dfs(int i, int j) {
        if (i >= n || j >= n)
            return Integer.MIN_VALUE;
        if (i == n - 1 && j == n - 1) {
            memo[i][j] = grid[i][j];
            path.put(i + "," + j, List.of(grid[i][j]));
            return memo[i][j];
        }
        if (memo[i][j] != Integer.MIN_VALUE)
            return memo[i][j];
        int right = dfs(i, j + 1);
        int down = dfs(i + 1, j);

        if (right > down) {
            memo[i][j] = grid[i][j] + right;
            List<Integer> newPath = new ArrayList<>();
            newPath.add(grid[i][j]);
            newPath.addAll(path.get((i) + "," + (j + 1)));
            path.put(i + "," + j, newPath);
        } else {
            memo[i][j] = grid[i][j] + down;
            List<Integer> newPath = new ArrayList<>();
            newPath.add(grid[i][j]);
            newPath.addAll(path.get((i + 1) + "," + (j)));
            path.put(i + "," + j, newPath);
        }
        return memo[i][j];
    }

    public void solve(int[][] matrix) {
        this.grid = matrix;
        this.n = matrix.length;
        this.memo = new int[n][n];
        this.path = new HashMap<>();

        for (int[] row : memo)
            Arrays.fill(row, Integer.MIN_VALUE);

        int maxSum = dfs(0, 0);
        List<Integer> res = path.get("0,0");

        System.out.println("Max Sum: " + maxSum);
        System.out.println("Path: " + res);
    }
}
