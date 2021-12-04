class Solution {
    int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    // distance problem -> bfs
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        int res = -1;
        // first second index records coordinates and third records distance
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // since we r calculating the distance between water(0) and 1, we need to
                // initialize the land to 0 distance
                if (grid[i][j] == 1) {
                    q.offer(new int[] { i, j, 0 });
                }
            }
        }
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];
            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                if (newX < 0 || newX >= n || newY < 0 || newY >= n || grid[newX][newY] != 0)
                    continue;
                q.offer(new int[] { newX, newY, dist + 1 });
                grid[newX][newY] = dist + 1;
                res = Math.max(res, dist + 1);
            }
        }
        return res;
    }
}