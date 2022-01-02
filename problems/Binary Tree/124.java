class Solution {
    int max_sum = Integer.MIN_VALUE;

    public int max_gain(TreeNode node) {
        if (node == null)
            return 0;
        int left_gain = Math.max(0, max_gain(node.left));
        int right_gain = Math.max(0, max_gain(node.right));
        int path_value = node.val + left_gain + right_gain;
        max_sum = Math.max(path_value, max_sum);
        return node.val + Math.max(left_gain, right_gain);
    }

    public int maxPathSum(TreeNode root) {
        max_gain(root);
        return max_sum;
    }
}