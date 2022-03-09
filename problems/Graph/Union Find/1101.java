class Solution {
    public class UF {
        int[] parent;
        int res;

        UF(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++)
                parent[i] = i;
            res = size;
        }

        public int find(int child) {
            if (parent[child] != child) {
                int p = find(parent[child]); // find parent recursively
                parent[child] = p;// update child's parent
                // System.out.println(child);
            }

            return parent[child];
        }

        public void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa != pb) {
                parent[pb] = pa;
                res--;// when union happens, we decrement one parent
            }
        }
    }

    public int earliestAcq(int[][] logs, int N) {
        Arrays.sort(logs, (a, b) -> (a[0] - b[0]));
        UF uf = new UF(N);
        for (int[] log : logs) {
            uf.union(log[1], log[2]);
            if (uf.res == 1)
                return log[0];
        }
        return -1;
    }
}


// LC 2096. Step-By-Step Directions From a Binary Tree Node to Another
class Solution {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        TreeNode ancestor = LCA(root, startValue, destValue);
        StringBuilder startToAns = new StringBuilder();
        dfs(ancestor, startValue, startToAns);
        StringBuilder ansToEnd = new StringBuilder();
        dfs(ancestor, destValue, ansToEnd);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < startToAns.length(); i++)
            res.append("U");
        for (int i = 0; i < ansToEnd.length(); i++)
            res.append(ansToEnd.charAt(i));
        return res.toString();
    }

    private TreeNode LCA(TreeNode root, int p, int q) {
        if (root == null || p == root.val || q == root.val)
            return root;
        TreeNode left = LCA(root.left, p, q);
        TreeNode right = LCA(root.right, p, q);
        if (left != null && right != null)
            return root;
        else
            return left == null ? right : left;
    }

    private boolean dfs(TreeNode ancestor, int val, StringBuilder path) {
        if (ancestor == null)
            return false;
        if (ancestor.val == val)
            return true;
        path.append("L");
        if (dfs(ancestor.left, val, path))
            return true;
        path.deleteCharAt(path.length() - 1);
        path.append("R");
        if (dfs(ancestor.right, val, path))
            return true;
        path.deleteCharAt(path.length() - 1);
        return false;
    }
}


// LC 199. Binary Tree Right Side View
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        dfs(root, 0, res);
        return res;
    }

    private void dfs(TreeNode root, int size, List<Integer> res) {
        if (size == res.size())
            res.add(root.val);
        if (root.right != null)
            dfs(root.right, size + 1, res);
        if (root.left != null)
            dfs(root.left, size + 1, res);
    }
}