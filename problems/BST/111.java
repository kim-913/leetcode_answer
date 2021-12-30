class Solution {
    private int res = Integer.MAX_VALUE;

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null)
            return 1;
        if (root.left != null)
            res = Math.min(res, minDepth(root.left));
        if (root.right != null)
            res = Math.min(res, minDepth(root.right));
        return res + 1;
    }
}