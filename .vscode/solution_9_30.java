import java.util.*;

public class solution_9_30 {

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    };

    Map<Integer, Employee> emap;

    public int getImportance(List<Employee> employees, int id) {
        emap = new HashMap<>();
        for (Employee e : employees)
            emap.put(e.id, e);
        return dfs(id);
    }

    public int dfs(int eid) {
        Employee employee = emap.get(eid);
        int ans = employee.importance;
        for (int subid : employee.subordinates)
            ans += dfs(subid);
        return ans;
    }

    // LC 1277 Count Square Submatrices with All Ones
    private int m;
    private int n;
    int res = 0;

    public int countSquares(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        if (matrix == null || matrix.length == 0)
            return 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    res++;
                    dfs(matrix, i, j);
                }
            }
        }
        return res;
    }

    public void dfs(int[][] matrix, int x, int y) {
        int offset = 1;
        int bound = Math.min(m, n);
        while (offset < bound) {
            int newX = x + offset;
            int newY = y + offset;
            if (newX >= m || newY >= n || matrix[newX][newY] == 0)
                return;
            for (int i = y; i <= newY; i++) {
                if (matrix[x][i] == 0)
                    return;
                if (matrix[newX][i] == 0)
                    return;
            }
            for (int i = x; i <= newX; i++) {
                if (matrix[i][y] == 0)
                    return;
                if (matrix[i][newY] == 0)
                    return;
            }
            res++;
            offset++;
        }
    }

    // LC 1277, dp solution
    public int countSquares_dp(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int sum = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // firstly, for the first row and the first column, initiate dp[i][j] in to the
                // same value as matrix[i][j]
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 1) {
                    // here, we want to check that the previous result and update the current
                    // dp[i][j] value
                    if (dp[i - 1][j - 1] >= 1 && dp[i][j - 1] >= 1 && dp[i - 1][j] >= 1) {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                    } else {
                        dp[i][j] = 1;
                    }
                }
                sum += dp[i][j];
            }
        }
        return sum;
    }
}
