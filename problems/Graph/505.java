class Solution {
    private int m;
    private int n;
    private int[][] distance;
    private int[][] directions = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        m = maze.length;
        n = maze[0].length;
        distance = new int[m][n];
        for (int[] row : distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        dfs(maze, start);
        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1
                : distance[destination[0]][destination[1]];
    }

    private void dfs(int[][] maze, int[] start) {
        for (int[] dir : directions) {
            int x = start[0] + dir[0];
            int y = start[1] + dir[1];
            int countStep = 0;
            while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
                countStep++;
                // meet wall, add another step
                x += dir[0];
                y += dir[1];
            }
            // already inside the wall, want it to return back
            if (distance[start[0]][start[1]] + countStep < distance[x - dir[0]][y - dir[1]]) {
                distance[x - dir[0]][y - dir[1]] = distance[start[0]][start[1]] + countStep;
                dfs(maze, new int[] { x - dir[0], y - dir[1] });
            }
        }
    }
}