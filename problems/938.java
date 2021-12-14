class Solution {
    private int res = 0;

    public int rangeSumBST(TreeNode root, int low, int high) {
        dfs(root, low, high);
        return res;
    }

    private void dfs(TreeNode node, int low, int high) {
        if (node != null) {
            if (node.val >= low && node.val <= high)
                res += node.val;
            if (low < node.val)
                dfs(node.left, low, high);
            if (node.val < high)
                dfs(node.right, low, high);
        }
    }
}