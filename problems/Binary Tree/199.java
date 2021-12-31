class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        dfs(root, 0, res);
        return res;
    }
    
    private void dfs(TreeNode root, int size, List<Integer> res){
        if(size == res.size()) res.add(root.val);
        if(root.right != null) dfs(root.right, size + 1, res);
        if(root.left != null) dfs(root.left, size + 1, res);
    }
}