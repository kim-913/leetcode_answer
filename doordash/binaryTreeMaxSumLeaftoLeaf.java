import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    private int maxSum = Integer.MIN_VALUE;
    private List<Integer> maxPath = new ArrayList<>();

    public int somePathSum(TreeNode root) {
        dfs(root);
        System.out.println("Best Path: " + maxPath);
        return maxSum;
    }

    // without path print
    private int dfs(TreeNode root) {
        if (root == null)
            return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        int curSum = root.val + left + right;
        if (root.left == null && root.right == null) {
            maxSum = Math.max(maxSum, curSum);
        }
        if (root.left == null)
            return right + root.val;
        if (root.right == null)
            return left + root.val;
        return Math.max(left, right) + root.val;
    }

    // print path dfs
    private Pair dfsPath(TreeNode node) {
        if (node == null)
            return new Pair(Integer.MIN_VALUE, new ArrayList<>());
        Pair left = dfsPath(node.left);
        Pair right = dfsPath(node.right);
        List<Integer> curPath = new ArrayList<>();
        int curSum = 0;
        if (left.sum > right.sum) {
            curSum = left.sum + node.val;
            curPath = new ArrayList<>(left.path);
        } else {
            curSum = right.sum + node.val;
            curPath = new ArrayList<>(right.path);
        }
        curPath.add(node.val);
        if (node.left != null && node.right != null) {
            int total = left.sum + right.sum + node.val;
            if (total > maxSum) {
                maxSum = total;
                maxPath = new ArrayList<>();
                maxPath.addAll(left.path);
                maxPath.add(node.val);
                List<Integer> rev = new ArrayList<>(right.path);
                Collections.reverse(rev);
                maxPath.addAll(rev);
            }
        }
        return new Pair(curSum, curPath);
    }
}

class Pair {
    int sum;
    List<Integer> path;

    public Pair(int sum, List<Integer> path) {
        this.sum = sum;
        this.path = new ArrayList<>();
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
