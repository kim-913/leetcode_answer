import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    Map<TreeNode, TreeNode> parentMap = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        construct(root); // Step 1: Build parent mapping

        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        q.offer(target);
        visited.add(target);
        int depth = 0;
        while (!q.isEmpty()) {
            if (depth == k)
                break;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                for (TreeNode neighbor : getNeighbors(curr)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        q.offer(neighbor);
                    }
                }
            }
            depth++;
        }
        // Collect all nodes exactly at distance k
        List<Integer> res = new ArrayList<>();
        while (!q.isEmpty()) {
            res.add(q.poll().val);
        }

        return res;
    }

    private void construct(TreeNode node) {
        if (node == null)
            return;
        if (node.left != null) {
            parentMap.put(node.left, node);
            construct(node.left);
        }
        if (node.right != null) {
            parentMap.put(node.right, node);
            construct(node.right);
        }
    }

    private List<TreeNode> getNeighbors(TreeNode node) {
        List<TreeNode> neighbors = new ArrayList<>();
        if (node.left != null)
            neighbors.add(node.left);
        if (node.right != null)
            neighbors.add(node.right);
        if (parentMap.containsKey(node))
            neighbors.add(parentMap.get(node));
        return neighbors;
    }
}
