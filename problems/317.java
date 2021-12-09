// 317. Shortest Distance from All Buildings
class Solution {
    int[][] directions = new int[][] { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } };

    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n];
        int houseCount = 0;
        int[][] houseReached = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // from each house to all empty land
                if (grid[i][j] == 1) {
                    // create a queue for each house
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[] { i, j });
                    houseCount++;
                    // have a house, set curLevel = 1;
                    int level = 1;
                    // set a visited[]
                    boolean[][] visited = new boolean[m][n];
                    while (!q.isEmpty()) {
                        int size = q.size();
                        for (int k = 0; k < size; k++) {
                            int[] cur = q.poll();
                            for (int[] d : directions) {
                                int newX = cur[0] + d[0];
                                int newY = cur[1] + d[1];
                                if (newX < 0 || newX >= m || newY < 0 || newY >= n || grid[newX][newY] != 0
                                        || visited[newX][newY])
                                    continue;
                                dist[newX][newY] += level;
                                houseReached[newX][newY]++;
                                visited[newX][newY] = true;
                                q.offer(new int[] { newX, newY });
                            }
                        }
                        level += 1;
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && houseReached[i][j] == houseCount) {
                    res = Math.min(res, dist[i][j]);
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}