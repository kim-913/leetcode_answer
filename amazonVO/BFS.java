// LC 909. Snakes and Ladders
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n * n];
        int index = 0;
        for (int i = n - 1; i >= 0; i -= 2) {
            for (int j = 0; j < n; j++) {
                arr[index] = board[i][j];
                index++;
            }
            index += n;
        }
        index = n;
        for (int i = n - 2; i >= 0; i -= 2) {
            for (int j = n - 1; j >= 0; j--) {
                arr[index] = board[i][j];
                index++;
            }
            index += n;
        }

        boolean[] visited = new boolean[n * n];
        Queue<Integer> q = new LinkedList<>();
        int start = arr[0] > -1 ? arr[0] - 1 : 0;
        q.offer(start);
        visited[start] = true;
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                if (cur == n * n - 1) {
                    return step;
                }
                for (int next = cur + 1; next <= Math.min(cur + 6, n * n - 1); next++) {
                    int dest = arr[next] > -1 ? arr[next] - 1 : next;
                    if (!visited[dest]) {
                        visited[dest] = true;
                        q.offer(dest);
                    }
                }
            }
            step++;
        }
        return -1;
    }
}


// LC 815. Bus Routes
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        int n = routes.length;
        Map<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j : routes[i]) {
                map.putIfAbsent(j, new HashSet<>());
                map.get(j).add(i);
            }
        }
        Queue<int[]> q = new LinkedList<>();
        // current stop, how many bus taken
        q.offer(new int[] { source, 0 });
        Set<Integer> visited = new HashSet<>();
        visited.add(source);
        boolean[] seen_routes = new boolean[n];
        while (!q.isEmpty()) {
            int curStop = q.peek()[0];
            int busCnt = q.peek()[1];
            q.poll();
            if (curStop == target)
                return busCnt;
            for (int curRoute : map.get(curStop)) {
                if (seen_routes[curRoute])
                    continue;
                for (int next : routes[curRoute]) {
                    if (!visited.contains(next)) {
                        visited.add(next);
                        q.offer(new int[] { next, busCnt + 1 });
                    }
                }
                seen_routes[curRoute] = true;
            }
        }
        return -1;
    }
}


// LC 127. Word Ladder
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>();
        for (String w : wordList)
            dict.add(w);

        if (!dict.contains(endWord))
            return 0;

        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);

        int l = beginWord.length();
        int step = 0;

        while (!q.isEmpty()) {
            step++;
            int size = q.size();
            for (int s = 0; s < size; s++) {
                String w = q.poll();
                char[] chs = w.toCharArray();
                for (int i = 0; i < l; i++) {
                    char ch = chs[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (ch == c)
                            continue;
                        chs[i] = c;
                        String t = new String(chs);
                        if (t.equals(endWord))
                            return step + 1;
                        if (!dict.contains(t))
                            continue;
                        dict.remove(t);
                        q.offer(t);
                    }
                    chs[i] = ch;
                }
            }
        }
        return 0;
    }
}


// LC 1730. Shortest Path to Get Food
class Solution {
    int[][] dirs = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public int getFood(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        int[] start = new int[2];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '*') {
                    q.offer(new int[] { i, j });
                }
            }
        }

        boolean[][] visited = new boolean[m][n];

        int step = 0;
        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                int[] pos = q.poll();
                int x = pos[0];
                int y = pos[1];
                if (grid[x][y] == '#')
                    return step;
                for (int[] dir : dirs) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];
                    if (isValid(grid, newX, newY) && !visited[newX][newY]) {
                        visited[newX][newY] = true;
                        q.offer(new int[] { newX, newY });
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private boolean isValid(char[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] != 'X';
    }
}



// 909. Snakes and Laddersf
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n * n];
        int index = 0;
        for (int i = n - 1; i >= 0; i -= 2) {
            for (int j = 0; j < n; j++) {
                arr[index] = board[i][j];
                index++;
            }
            index += n;
        }
        index = n;
        for (int i = n - 2; i >= 0; i -= 2) {
            for (int j = n - 1; j >= 0; j--) {
                arr[index] = board[i][j];
                index++;
            }
            index += n;
        }

        boolean[] visited = new boolean[n * n];
        Queue<Integer> q = new LinkedList<>();
        int start = arr[0] > -1 ? arr[0] - 1 : 0;
        q.offer(start);
        visited[start] = true;
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                if (cur == n * n - 1) {
                    return step;
                }
                for (int next = cur + 1; next <= Math.min(cur + 6, n * n - 1); next++) {
                    int dest = arr[next] > -1 ? arr[next] - 1 : next;
                    if (!visited[dest]) {
                        visited[dest] = true;
                        q.offer(dest);
                    }
                }
            }
            step++;
        }
        return -1;
    }
}