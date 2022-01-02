class Solution {
    // double linkedlist, tail connect again to head
    Node head = null;
    Node tail = null;

    public Node treeToDoublyList(Node root) {
        if (root == null)
            return root;
        dfs(root);
        tail.right = head;
        head.left = tail;
        return head;
    }

    private Node dfs(Node root) {
        if (root == null)
            return null;
        dfs(root.left);
        if (tail != null) {
            tail.right = root;
            root.left = tail;
        } else {
            head = root;
        }
        tail = root;
        /*
         * 1
         * 1
         * 1
         * 2
         * 1
         * 3
         * 1
         * 4
         * 1
         * 5
         * Output: [1,2,3,4,5]
         */
        // System.out.println(head.val);
        // System.out.println(tail.val);
        dfs(root.right);
        return head;
    }
}