class Solution {
    private int max;
    public int diameterOfBinaryTree(TreeNode root) {
        max = 0;
        currentNode(root);
        return max;
    }

    private int currentNode(TreeNode current) {
        if (current == null) {
            return 0;
        }
        int left = currentNode(current.left);
        int right = currentNode(current.right);
        // update the global variable here to calculate diameter
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }
}