class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                list.add(cur.val);
                if (cur.left != null)
                    q.add(cur.left);
                if (cur.right != null)
                    q.add(cur.right);
            }
            res.add(new ArrayList<>(list));
        }
        return res;
    }
}