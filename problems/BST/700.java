class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode res = new TreeNode();
        if (root == null)
            return null;
        if (root.val == val)
            res = root;
        else if (root.val < val)
            res = searchBST(root.right, val);
        else if (root.val > val)
            res = searchBST(root.left, val);
        return res;
    }
}