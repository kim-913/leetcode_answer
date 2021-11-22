import java.util.*;

import javax.swing.tree.TreeNode;

/**
 * solution_11_15
 */
public class solution_11_15 {

    public static void main(String[] args) {

    }

    // 938. Range Sum of BST
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null)
            return 0;
        if (root.val >= low && root.val <= high) {
            return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
        } else if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        } else {
            return rangeSumBST(root.left, low, high);
        }
    }

    // 1650. Lowest Common Ancestor of a Binary Tree III
    public Node lowestCommonAncestor(Node p, Node q) {
        /*
         * Node a = p, b = q; while (a != b) { a = a == null? q : a.parent; b = b ==
         * null? p : b.parent; } return b;
         */
        Set<Node> parents = new HashSet<>();
        while (p != null) {
            parents.add(p);
            p = p.parent;
        }
        while (!parents.contains(q)) {
            q = q.parent;
        }
        return q;
    }
}