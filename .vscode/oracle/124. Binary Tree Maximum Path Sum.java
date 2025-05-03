import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private int maxSum = Integer.MIN_VALUE;
    private List<Integer> maxPath = new ArrayList<>();

    public int maxPathSum(TreeNode root) {
        dfs(root);
        System.out.println("Max path: " + maxPath);
        return maxSum;
    }

    private List<Integer> dfs(TreeNode node) {
        if (node == null)
            return new ArrayList<>();
        List<Integer> leftPath = dfs(node.left);
        List<Integer> rightPath = dfs(node.right);
        int leftGain = Math.max(0, sum(leftPath));
        int rightGain = Math.max(0, sum(rightPath));
        int curSum = node.val + leftGain + rightGain;
        if (curSum > maxSum) {
            maxSum = curSum;
            maxPath = new ArrayList<>();
            if (leftGain > 0)
                maxPath.addAll(leftPath);
            maxPath.add(node.val);
            if (rightGain > 0) {
                List<Integer> revRight = new ArrayList<>(rightPath);
                Collections.reverse(revRight);
                maxPath.addAll(revRight);
            }
        }
        // Return the better one of left or right + root, for upward propagation
        if (leftGain > rightGain) {
            leftPath.add(node.val);
            return leftPath;
        } else {
            rightPath.add(node.val);
            return rightPath;
        }
    }

    private int sum(List<Integer> list) {
        int s = 0;
        for (int val : list)
            s += val;
        return s;
    }
}