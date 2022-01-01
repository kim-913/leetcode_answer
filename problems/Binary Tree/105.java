class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }

    public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        // preorder: root, left, right
        // inorder: left, root, right
        if (preStart > preorder.length - 1 || inStart > inEnd)
            return null;
        // construct the root node
        TreeNode res = new TreeNode(preorder[preStart]);
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == res.val)
                index = i;
        }
        // inside preorder, left is right after root, inside inorder, left is before
        // root
        res.left = helper(preStart + 1, inStart, index - 1, preorder, inorder);
        // need to notice here that inside preorder right isn't just +2, but we have to
        // iterate through the whole leftsubtree
        res.right = helper(preStart + index - inStart + 1, index + 1, inEnd, preorder, inorder);
        return res;
    }
}