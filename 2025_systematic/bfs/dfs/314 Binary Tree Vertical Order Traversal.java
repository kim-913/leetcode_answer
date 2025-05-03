import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        // col, val in col
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> col = new LinkedList<>();
        q.offer(root);
        col.offer(0);
        int max = 0, min = 0;
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            int curCol = col.poll();
            if (!map.containsKey(curCol))
                map.put(curCol, new ArrayList<>());
            map.get(curCol).add(cur.val);
            if (cur.left != null) {
                q.offer(cur.left);
                col.offer(curCol - 1);
                min = Math.min(min, curCol - 1);
            }
            if (cur.right != null) {
                q.offer(cur.right);
                col.offer(curCol + 1);
                max = Math.max(max, curCol + 1);
            }
        }
        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }
        return res;
    }
}