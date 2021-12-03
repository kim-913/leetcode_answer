class Solution {
    private Map<Node, Node> map = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null)
            return null;
        if (!map.containsKey(head)) {
            Node node = new Node(head.val);
            map.put(head, node);
            node.next = copyRandomList(head.next);
            // each node has two corresponding characteristics
            node.random = copyRandomList(head.random);
        }
        return map.get(head);
    }
}