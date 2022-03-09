// LC 547. Number of Provinces
class Solution {
    private boolean[] visited;

    public int findCircleNum(int[][] isConnected) {
        visited = new boolean[isConnected.length];
        int res = 0;
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                dfs(isConnected, i);
                res++;
            }
        }
        return res;
    }

    private void dfs(int[][] isConnected, int cur) {
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i] && isConnected[cur][i] == 1) {
                visited[i] = true;
                dfs(isConnected, i);
            }
        }
    }
}


// LC 133. Clone Graph
class Solution {
    Map<Integer, Node> nodeMap = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null || node.neighbors == null)
            return node;
        Node cur = new Node(node.val);
        if (nodeMap.containsKey(node.val))
            return nodeMap.get(node.val);
        nodeMap.put(node.val, cur);
        for (Node nei : node.neighbors) {
            cur.neighbors.add(cloneGraph(nei));
        }
        return cur;
    }
}


// LC 694. Number of Distinct Islands
class Solution {

    int[][] dirs = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public int numDistinctIslands(int[][] grid) {
        Set<String> set = new HashSet<>();
        int res = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    helper(grid, i, j, 0, 0, sb);
                    String s = sb.toString();
                    System.out.println(s);
                    if (!set.contains(s)) {
                        res++;
                        set.add(s);
                    }
                }
            }
        }
        return res;
    }

    public void helper(int[][] grid, int i, int j, int xpos, int ypos, StringBuilder sb) {
        grid[i][j] = 0;
        sb.append(xpos + "" + ypos);
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == 0)
                continue;
            helper(grid, x, y, xpos + dir[0], ypos + dir[1], sb);
        }
    }
}


// LC 337. House Robber III
class Solution {
    public int[] helper(TreeNode node) {
        // return [rob this node, not rob this node]
        if (node == null) {
            return new int[] { 0, 0 };
        }
        int[] left = helper(node.left);
        int[] right = helper(node.right);
        // if we rob this node, we cannot rob its children
        int rob = node.val + left[1] + right[1];
        System.out.println(rob);
        // else, we free to choose rob its children or not
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[] { rob, notRob };
    }

    public int rob(TreeNode root) {
        int[] answer = helper(root);
        return Math.max(answer[0], answer[1]);
    }
}


// LC 472. Concatenated Words
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();
        HashSet<String> wordSet = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            if (dfs(word, wordSet)) {
                ans.add(word);
            }
        }
        return ans;
    }

    private boolean dfs(String word, HashSet<String> wordSet) {
        for (int i = 1; i < word.length(); i++) {
            if (wordSet.contains(word.substring(0, i))) {
                String suffix = word.substring(i);
                if (wordSet.contains(suffix) || dfs(suffix, wordSet)) {
                    wordSet.add(word); // can treat concatenated word as a new word for quickly lookup later
                    return true;
                }
            }
        }
        return false;
    }
}


//
class Solution {
    int res = 0;

    public int numberOfArithmeticSlices(int[] nums) {
        dfs(nums, 0);
        return res;
    }

    private int dfs(int[] nums, int index) {
        if (index > nums.length - 3)
            return 0;
        int cur = 0;
        if (nums[index + 1] - nums[index] == nums[index + 2] - nums[index + 1]) {
            cur = 1 + dfs(nums, index + 1);
            res += cur;
        } else {
            dfs(nums, index + 1);
        }
        return cur;
    }
}


// 天平，找不平衡的element
class Solution {
    public static int find(int[] nums) {
        int half = nums.length / 2;
        Compare com = new Compare();
        dfs(nums, com, 0, half - 1, half, 2 * half - 1);
    }

    private static int dfs(int[] nums, Compare com, int a, int b, int c, int d) {
        if (a == b && c == d) {
            res = nums[a] > nums[c] ? nums[a] : nums[c];
            return;
        }
        if (com.calculate(a, b) == com.calculate(c, d)) {
            res = nums[d + 1];
            return;
        }
        if (com.calculate(a, b) > com.calculate(c, d)) {
            int count = (b - a + 1) / 2;
            dfs(nums, com, a, a + count - 1, a + count, a + 2 * count - 1);
        } else {
            int count = (d - c + 1) / 2;
            dfs(nums, com, c, c + count - 1, c + count, c + 2 * count - 1);
        }
    }
}


// LC 490. The Maze
class Solution {
    int[][] directions = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
    boolean[][] visited;
    int m;
    int n;

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        m = maze.length;
        n = maze[0].length;
        visited = new boolean[m][n];
        return dfs(maze, start, destination);
    }

    private boolean dfs(int[][] maze, int[] cur, int[] dest) {
        if (visited[cur[0]][cur[1]])
            return false;
        if (cur[0] == dest[0] && cur[1] == dest[1])
            return true;
        visited[cur[0]][cur[1]] = true;
        for (int[] dir : directions) {
            int x = cur[0];
            int y = cur[1];
            while (x + dir[0] >= 0 && x + dir[0] < m && y + dir[1] >= 0 && y + dir[1] < n
                    && maze[x + dir[0]][y + dir[1]] != 1) {
                x += dir[0];
                y += dir[1];
            }
            if (dfs(maze, new int[] { x, y }, dest))
                return true;
        }
        return false;
    }
}