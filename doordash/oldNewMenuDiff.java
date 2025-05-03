package doordash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class oldNewMenuDiff {
    public static int getModifiedItems(Node oldNode, Node newNode) {
        if (oldNode == null && newNode == null)
            return 0;
        int res = 0;
        if (oldNode == null || newNode == null || !oldNode.equals(newNode))
            res++;
        Map<String, Node> oldMap = getChildNodes(oldNode);
        Map<String, Node> newMap = getChildNodes(newNode);
        for (String key : oldMap.keySet()) {
            res += getModifiedItems(oldMap.get(key), newMap.getOrDefault(key, null));
        }
        for (String key : newMap.keySet()) {
            if (!oldMap.containsKey(key)) {
                res += getModifiedItems(null, newMap.get(key));
            }
        }
        return res;
    }

    private static Map<String, Node> getChildNodes(Node menu) {
        Map<String, Node> map = new HashMap<>();
        if (menu == null) {
            return map;
        }
        for (Node node : menu.children) {
            map.put(node.key, node);
        }
        return map;
    }

    public static class Node {
        String key;
        int value;
        boolean isActive;
        List<Node> children;

        public Node(String key, int value, boolean isActive) {
            this.key = key;
            this.value = value;
            this.isActive = isActive;
            this.children = new ArrayList<>();
        }

        public boolean equals(Node node) {
            if (node == null) {
                return false;
            }
            return this.key == node.key
                    && this.value == node.value
                    && this.isActive == node.isActive;
        }
    }

    public static void main(String[] args) {

        /*
         * Existing tree
         * a(1, T)
         * / \
         * b(2, T) c(3, T)
         * / \
         * d(4, T) e(5, T)
         * 
         * New tree
         * a(1, T)
         * / \
         * b(2, T) c(3, T)
         * / \
         * d(4, T) e(5, T)
         * 
         */

        Node a = new Node("a", 1, true);
        Node b = new Node("b", 2, true);
        Node c = new Node("c", 3, true);
        Node d = new Node("d", 4, true);
        Node e = new Node("e", 5, true);
        Node g = new Node("g", 7, true);

        a.children.add(b);
        a.children.add(c);

        b.children.add(d);
        b.children.add(e);

        // c.children.add(g);

        Node a1 = new Node("a", 1, true);
        Node b1 = new Node("b", 2, true);
        Node c1 = new Node("c", 3, true);
        Node d1 = new Node("d", 4, true);
        Node e1 = new Node("e", 5, true);
        Node f1 = new Node("f", 6, true);
        Node g1 = new Node("g", 7, false);

        a1.children.add(b1);
        a1.children.add(c1);

        b1.children.add(d1);
        // b1.children.add(e1);
        // b1.children.add(f1);

        c1.children.add(e1);

        int count = getModifiedItems(a, a1);
        System.out.println("Changed Items are: " + count);
    }
}
