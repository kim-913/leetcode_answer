package problems;
class Solution {
    private int m;
    private int n;
    private int[][] directions = new int[][]{{1 ,0}, {0 ,1}, {-1, 0}, {0, -1}};
    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int res = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }
    
    public void dfs(char[][] grid, int x, int y){
        if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '0') return;
        grid[x][y] = '0';
        for(int[] direction: directions){
            dfs(grid, x + direction[0], y + direction[1]);
        }
        /*
        dfs(grid, x + 1, y);
        dfs(grid, x - 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x, y - 1);
        */
    }
}