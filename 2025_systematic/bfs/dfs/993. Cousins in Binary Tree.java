import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    int dx = 0;
    int dy = 0;
    TreeNode px = new TreeNode(-1);
    TreeNode py = new TreeNode(-1);

    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, x, y, 0, null);
        return dx == dy && px != py;
    }

    private void dfs(TreeNode root, int x, int y, int depth, TreeNode parent) {
        if (root == null)
            return;
        if (root.val == x) {
            px = parent;
            dx = depth;
        }
        if (root.val == y) {
            py = parent;
            dy = depth;
        }
        dfs(root.left, x, y, depth + 1, root);
        dfs(root.right, x, y, depth + 1, root);
    }
}

class SolutionBFS {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null)
            return false;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        TreeNode px = null, py = null;
        int dx = -1, dy = -1;
        int level = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();

                if (cur.left != null) {
                    if (cur.left.val == x) {
                        px = cur;
                        dx = level;
                    }
                    if (cur.left.val == y) {
                        py = cur;
                        dy = level;
                    }
                    q.add(cur.left);
                }

                if (cur.right != null) {
                    if (cur.right.val == x) {
                        px = cur;
                        dx = level;
                    }
                    if (cur.right.val == y) {
                        py = cur;
                        dy = level;
                    }
                    q.add(cur.right);
                }
            }

            level++;
        }

        return dx == dy && px != py;
    }
}