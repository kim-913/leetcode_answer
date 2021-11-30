package problems;
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};

// In order
class Solution {
    Node head = null;
    Node tail = null;
    public Node treeToDoublyList(Node root) {
        if(root == null) return root;
        dfs(root);
        tail.right = head;
        head.left = tail;
        return head;
    }
    
    private void dfs(Node node){
        if(node == null) return;
        dfs(node.left);
        if(tail != null) {
            tail.right = node;
            node.left = tail;
        } else {
            head = node;
        }
        tail = node;
        dfs(node.right);
    }
}