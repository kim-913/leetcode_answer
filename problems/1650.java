package problems;
import java.util.*;
class Solution {
    /*
    // Definition for a Node.
    */
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };

    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> parents = new HashSet<>();
        while(p != null){
            parents.add(p);
            p = p.parent;
        }
        while(!parents.contains(q)){
            q = q.parent;
        }
        return q;
    }
}