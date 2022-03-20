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


// LC 79. Word Search
class Solution {
    int m;
    int n;
    boolean[][] visited;
    int[][] directions = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, 0, i, j))
                    return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int index, int x, int y) {
        if (index == word.length())
            return true;
        if (x < 0 || y < 0 || x >= m || y >= n || visited[x][y] || board[x][y] != word.charAt(index))
            return false;
        visited[x][y] = true;
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (dfs(board, word, index + 1, newX, newY))
                return true;
        }
        visited[x][y] = false;
        return false;
    }
}

// 138. Copy List with Random Pointer
class Solution {
    Map<Node, Node> map = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null)
            return head;
        if (map.containsKey(head))
            return map.get(head);
        Node res = new Node(head.val);
        map.put(head, res);
        res.next = copyRandomList(head.next);
        res.random = copyRandomList(head.random);
        return map.get(head);
    }
}


// LC 17. Letter Combinations of a Phone Number
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0)
            return res;
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        dfs(digits, res, "", 0, map);
        return res;
    }

    public void dfs(String digits, List<String> res, String s, int cur, Map<Character, String> map) {
        if (cur == digits.length()) {
            res.add(s);
            return;
        }
        String current = map.get(digits.charAt(cur));
        for (int i = 0; i < current.length(); i++) {
            String sub = current.substring(i, i + 1);
            dfs(digits, res, s + sub, cur + 1, map);
        }
    }
}





// LC 529. Minesweeper
class Solution {
    int[][] directions = new int[][] { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
            { 1, 1 } };
    int m;
    int n;
    boolean[][] visited;

    public char[][] updateBoard(char[][] board, int[] click) {
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dfs(board, click[0], click[1]);
        return board;
    }

    private void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || board[x][y] == 'M')
            return;
        visited[x][y] = true;
        int mines = count(board, x, y);
        if (mines != 0) {
            board[x][y] = (char) (mines + '0');
            return;
        } else
            board[x][y] = 'B';
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            dfs(board, newX, newY);
        }
    }

    private int count(char[][] board, int x, int y) {
        int mines = 0;
        if (x - 1 >= 0 && board[x - 1][y] == 'M')
            mines++;
        if (x - 1 >= 0 && y + 1 < board[0].length && board[x - 1][y + 1] == 'M')
            mines++;
        if (y + 1 < board[0].length && board[x][y + 1] == 'M')
            mines++;
        if (x + 1 < board.length && y + 1 < board[0].length && board[x + 1][y + 1] == 'M')
            mines++;
        if (x + 1 < board.length && board[x + 1][y] == 'M')
            mines++;
        if (x + 1 < board.length && y - 1 >= 0 && board[x + 1][y - 1] == 'M')
            mines++;
        if (y - 1 >= 0 && board[x][y - 1] == 'M')
            mines++;
        if (x - 1 >= 0 && y - 1 >= 0 && board[x - 1][y - 1] == 'M')
            mines++;
        return mines;
    }
}


// LC 212. Word Search II
public class Solution {
    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;

    }

    int m;
    int n;

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        m = board.length;
        n = board[0].length;
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        if (i < 0 || i >= m || j < 0 || j >= n)
            return;
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null)
            return;
        p = p.next[c - 'a'];
        if (p.word != null) { // found one
            res.add(p.word);
            p.word = null; // de-duplicate
        }

        board[i][j] = '#';
        dfs(board, i - 1, j, p, res);
        dfs(board, i, j - 1, p, res);
        dfs(board, i + 1, j, p, res);
        dfs(board, i, j + 1, p, res);
        board[i][j] = c;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null)
                    p.next[i] = new TrieNode();
                p = p.next[i];
            }
            p.word = w;
        }
        return root;
    }
}


// LC 39. Combination Sum
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    public void dfs(int[] candidates, int target, int cur, List<Integer> list, List<List<Integer>> res) {
        // if(target < 0) return;
        if (target == 0)
            res.add(new ArrayList<>(list));
        for (int i = cur; i < candidates.length; i++) {
            if (target < 0)
                break;
            list.add(candidates[i]);
            dfs(candidates, target - candidates[i], i, list, res);
            list.remove(list.size() - 1);
        }
    }
}


// 695. Max Area of Island
class Solution {
    int res = 0;

    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, dfs(grid, i, j));
                }
            }
        }
        return max;
    }

    public int dfs(int[][] grid, int x, int y) {
        // base case
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
            grid[x][y] = 0;
            return 1 + dfs(grid, x + 1, y) + dfs(grid, x - 1, y) + dfs(grid, x, y + 1) + dfs(grid, x, y - 1);
        }
        return 0;
    }
}