/**
 * review_google_vo
 */
public class review_google_vo {

    public static void main(String[] args) {

    }

    // LC 900 RLE Iterator
    class RLEIterator {
        int index;
        int[] A;

        public RLEIterator(int[] A) {
            this.A = A;
            index = 0;
        }

        public int next(int n) {
            while (index < A.length && n > A[index]) {
                n = n - A[index];
                index += 2;
            }

            if (index >= A.length) {
                return -1;
            }

            A[index] = A[index] - n;
            return A[index + 1];
        }
    }

    // LC 388. Longest Absolute File Path
    /*
     * private int res = 0;
     * 
     * public int lengthLongestPath(String input) { String[] arr =
     * input.split("\n"); dfs(arr, -1, 0, 0); return res; }
     * 
     * private void dfs(String[] arr, int curLength, int index, int level) { for
     * (int i = index; i < arr.length; i++) { int current = curLength; if
     * (countTabs(arr[i]) == level) { current += arr[i].length() - level + 1; if
     * (isFile(arr[i])) { res = Math.max(current, res); } dfs(arr, current, i + 1,
     * level + 1); } if (countTabs(arr[i]) < level) { break; } } }
     * 
     * // first we have to judge if the input is a file or not private boolean
     * isFile(String input) { for (char c : input.toCharArray()) { if (c == '.')
     * return true; } return false; }
     * 
     * // the \t marks the current level we are in private int countTabs(String
     * input) { int count = 0; for (char c : input.toCharArray()) { if (c == '\t')
     * count++; } return count; }
     */
    public int lengthLongestPath(String input) {
        String[] paths = input.split("\n");
        int[] stack = new int[paths.length + 1];
        int maxLen = 0;
        for (String s : paths) {
            int lev = s.lastIndexOf("\t") + 1, curLen = stack[lev + 1] = stack[lev] + s.length() - lev + 1;
            if (s.contains("."))
                maxLen = Math.max(maxLen, curLen - 1);
        }
        return maxLen;
    }

    //

    private static double probability(int t, int n, int x, int y) {
        if (t == 0)
            return (x == n && y == 0) ? 1 : 0;
        if (t == 1)
            return (x == n - 1 && y == 1) ? 1 : 0;
        // starting from day2, there are two different cases, either
        /*
         * 1. we take a socks from a whole pair, which initially we have one more whole
         * pair, to each the state of x whole pairs and y one socks, we must have x + 1
         * whole pairs and y - 1 one socks OR 2. we take a socks from a one sock, to
         * each x, y we have x and y + 1 one socks in the previous day
         */
        double[][][] dp = new double[t + 1][n + 1][n + 1];
        dp[0][n][0] = 1.0;
        dp[1][n - 1][1] = 1.0;
        dp[2][n - 2][2] = 0.5;
        dp[2][n - 1][0] = 0.5;
        for (int i = 3; i <= t; i++) {
            // dp[i][n - i][]
            dp[i][x][y] = (double) (dp[i - 1][x + 1][y - 1] * (2 * x + 2) / (2 * x + 2 + y - 1))
                    + (dp[i - 1][x][y + 1] * (y + 1) / (2 * x + y + 1));
        }
        return dp[t][x][y];
    }
    // private static double recursion(int x, int y, int n){
    // // if(x > n || y < 0) return 0.0;
    // if(x == n && y == 0) return 1.0;
    // if(x == n - 1 && y == 1) return 1.0;
    // if(x == n - 2 && y == 2) return 0.5;
    // if(x == n - 1 && y == 0) return 0.5;
    // double a = recursion(x + 1, y - 1, n);
    // double b = recursion(x, y + 1, n);
    // return a * (2 * x + 2) / (2 * x + 2 + y - 1) + b * (y + 1) / (2 * x + y + 1);
    // }

    // LC 366 Find Leaves of Binary Tree (remove leaves until root is empty)
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        // note here that we have to set root != null
        while (root != null) {
            List<Integer> temp = new ArrayList<Integer>();
            root = removeLeaves(root, temp);
            res.add(temp);
        }
        return res;
    }

    private TreeNode removeLeaves(TreeNode root, List<Integer> temp) {
        if (root == null)
            return null;
        if (root.left == null && root.right == null) {
            temp.add(root.val);
            return null;
        }
        root.left = removeLeaves(root.left, temp);
        root.right = removeLeaves(root.right, temp);
        return root;
    }

    // Follow up
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null)
            return false;
        if (compareTree(root, subRoot))
            return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean compareTree(TreeNode cur, TreeNode subRoot) {
        if (cur == null && subRoot == null)
            return true;
        if (cur == null || subRoot == null)
            return false;
        if (cur.val == subRoot.val)
            return compareTree(cur.left, subRoot.left) && compareTree(cur.right, subRoot.right);
        else
            return false;
    }

    //

    private static int[] intervals(String[] arr, String prefix) {
        int[] res = new int[] { -1, -1 };
        if (arr[0].indexOf(prefix) == 0)
            res[0] = 0;
        int flag = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].indexOf(prefix) == 0) {
                res[0] = i;
                res[1] = i;
                while (res[1] < arr.length && arr[res[1]].indexOf(prefix) == 0) {
                    res[1]++;
                }
                res[1]--;
                break;
            }
        }
        return res;
    }

    // 990. Satisfiability of Equality Equations
    public boolean equationsPossible(String[] equations) {
        int[] parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        for (String str : equations) {
            if (str.charAt(1) == '=') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                union(parent, index1, index2);
            }
        }
        for (String str : equations) {
            if (str.charAt(1) == '!') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                if (find(parent, index1) == find(parent, index2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public int find(int[] parent, int index) {
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }

    //
    private static int minimumFlights(int[][] graph, int start, int end) {
        //
        if (start == end)
            return 0;
        int res = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                if (cur == end)
                    return res;
                for (int neighbor : graph[cur]) {
                    q.add(neighbor);
                }
            }
            res++;
        }
        return res;
    }

    private static int minimumVisa(int[][] graph, int start, int end, int[] visa) {
        visited = new boolean[graph.length];
        dfs(graph, start, end, visa, start, 0);
        return res;
    }

    private void dfs(int[][] graph, int start, int end, int[] visa, int curCity, int curVisa) {
        if (curCity == end) {
            res = Math.min(res, curVisa);
            return;
        }
        visited[curCity] = true;
        for (int city : graph[start]) {
            if (visa[city] == 1)
                curVisa++;
            if (!visited[city]) {
                dfs(graph, start, end, visa, city, curVisa);
            }
        }
        visited[curCity] = false;
    }
}