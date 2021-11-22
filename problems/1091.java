package problems;
class Solution {
    int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, -1}, {1, 1}, {-1, 1}, {-1, -1} };
    public int shortestPathBinaryMatrix(int[][] grid) {
        //int res = 1;
        int n = grid.length;
        if(grid[0][0] != 0 || grid[n - 1][n - 1] != 0) return -1;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        queue.add(new int[]{0, 0, 1});
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            if(cur[0] == n - 1 && cur[1] == n - 1) {
                return cur[2];
            }
            for(int[] direction: directions){
                int newX = cur[0] + direction[0];
                int newY = cur[1] + direction[1];
                if(newX < 0 || newX >= n || newY < 0 || newY >= n || visited[newX][newY] || grid[newX][newY] != 0) continue;
                visited[newX][newY] = true;
                queue.offer(new int[]{newX, newY, cur[2] + 1});
            }
        }
        return -1;
    }
}