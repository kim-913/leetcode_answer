package doordash;

public class 827. Making A Large Island {
    int m;
    int n;
    int[][] directions = new int[][]{{ -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }};
    public int largestIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int res = 0;
        int key = 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    map.put(key, dfs(i, j, grid, key));
                    res = Math.max(res, map.get(key++));
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    // see if visited current region
                    Set<Integer> visited = new HashSet<>();
                    int flipZero = 1;
                    for (int[] dir: directions) {
                        int newX = i + dir[0];
                        int newY = j + dir[1];
                        if(valid(newX, newY)) {
                            key = grid[newX][newY];
                            if(key > 1 && visited.add(key)) flipZero += map.get(key);
                        }
                    }
                    res = Math.max(res, flipZero);
                }
            }
        }
        return res;
    }
    
    private int dfs (int x, int y, int[][] grid, int key) {
        int area = 0;
        grid[x][y] = key;
        for (int[] dir: directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (valid(newX, newY) && grid[newX][newY] == 1) {
                area += dfs(newX, newY, grid, key);
            } 
        }
        return area + 1;
    }
     private boolean valid(int x, int y){
        return (x >= 0 && x < m && y >= 0 && y < n);
    }
    
}
