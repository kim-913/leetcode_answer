package problems;
class Solution {
    int[][] directions = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int orange = 0;
        int res = 0;
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 2) queue.add(new int[]{i, j});
                else if(grid[i][j] == 1) orange++;
            }
        }
        // if no good orange, return
        if(orange == 0) return 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                for(int[] direction: directions){
                    int newX = x + direction[0];
                    int newY = y + direction[1];
                    if(newX < 0 || newX >= m || newY < 0 || newY >= n || grid[newX][newY] != 1) continue;
                    grid[newX][newY] = 2;
                    queue.offer(new int[]{newX, newY});
                    orange--;
                }
            }
            res++;
        }
        //if oranges aren't cleaned(some oranges are still left), means there isn't an answer
        return orange > 0 ? - 1 : res - 1;
    }
}