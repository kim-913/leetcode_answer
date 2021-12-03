// classic dfs
class Solution {
    // use map because need to track if we have already add it into the new graph
    private Map<Integer, Node> map;

    public Node cloneGraph(Node node) {
        if (node == null || node.neighbors == null)
            return node;
        map = new HashMap<>();
        return dfs(node);
    }

    public Node dfs(Node node) {
        if (map.containsKey(node.val))
            return (map.get(node.val));
        Node cur = new Node(node.val);
        map.put(node.val, cur);
        for (Node neighbor : node.neighbors) {
            cur.neighbors.add(dfs(neighbor));
        }
        return cur;
    }
}