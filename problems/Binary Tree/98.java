class Solution {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode root, long left, long right) {
        if (root == null)
            return true;
        if (root.val <= left || root.val >= right)
            return false;
        if (dfs(root.left, left, root.val) && dfs(root.right, root.val, right))
            return true;
        return false;
    }
}