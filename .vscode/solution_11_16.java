import javax.swing.tree.TreeNode;

package.vscode;

public class solution_11_16 {

    public static void main(String[] args) {

    }

    // 668. Kth Smallest Number in Multiplication Table
    public int findKthNumber(int m, int n, int k) {
        // use binary search
        int left = 1, right = m * n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (!enough(mid, m, n, k))
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }

    public boolean enough(int x, int m, int n, int k) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            count += Math.min(x / i, n);
        }
        return count >= k;
    }

    // LC 42. Trapping Rain Water
    public int trap(int[] height) {
        int res = 0;
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        // The most important point for this problem is that it should take the global
        // left max and right max is because that
        // the trapped water is determined by these two variables instead of the current
        // left and right.
        for (int i = 0; i < height.length; i++) {
            left[i] = i == 0 ? height[i] : Math.max(height[i], left[i - 1]);
        }
        for (int i = height.length - 1; i >= 0; i--) {
            right[i] = i == height.length - 1 ? height[i] : Math.max(height[i], right[i + 1]);
        }

        // current trapped water amount = min(right, left) - h[i]
        for (int i = 0; i < height.length; i++) {
            res += Math.min(right[i], left[i]) - height[i];
        }
        return res;
    }

    // LC 37. Sudoku Solver
    public void solveSudoku(char[][] board) {
        /*
         * use backtrack to solve this problem, the key point is that we have to check
         * the current cell, check if we place number from 1-9, i s it valid? if it is,
         * we can start to do recursion from here. otherwise, we have to backtrack and
         * change the state for the cell
         */
        if (board == null || board.length == 0)
            return;
        solve(board);
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            // if there are same character in the same row
            if (board[i][col] != '.' && board[i][col] == c)
                return false;
            // if there are same character in the same col
            if (board[row][i] != '.' && board[row][i] == c)
                return false;
            // check the 3 * 3 smaller grid
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.'
                    && board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c)
                return false;
        }
        return true;
    }

    private boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solve(board)) {
                                return true;
                            } else {
                                // backtrack to previous state
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // LC 114 Flatten Binary Tree to Linked List
    public void flatten(TreeNode root) {
        dfs(root);
    }

    public TreeNode dfs(TreeNode root) {
        // use dfs to solve this problem
        if (root == null)
            return null;
        // if node is a leaf, then return
        if (root.left == null && root.right == null)
            return root;
        // if not, then continue to do recursion
        // we need to record the left subtree to avoid losing track
        TreeNode leftSub = dfs(root.left);
        // similaly, record right
        TreeNode rightSub = dfs(root.right);
        // current step, connect together the tree, connect left to right
        // the most important step, example:
        /*
         * 2 / \ 3 4 gets in to 2 -> 3 -> 4
         */
        if (leftSub != null) {
            leftSub.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        return rightSub == null ? leftSub : rightSub;
    }
}
