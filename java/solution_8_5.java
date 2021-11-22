import java.util.*;

public class solution_8_5 {
    
    public static void main(String[] args) {
        
    }


    // LeetCode 133. Clone Graph
    public Node cloneGraph(Node node) {
        if(node == null) return node;
        //Node res = new Node();
        Map<Node, Node> res = new HashMap<>();
        // bfs
        LinkedList<Node> q = new LinkedList<Node>();
        q.add(node);
        res.put(node, new Node(node.val, new ArrayList<Node>()));
        while(!q.isEmpty()){
            Node cur = q.remove();
            for(Node neighbor: cur.neighbors){
                if(!res.containsKey(neighbor)) {
                    res.put(neighbor, new Node(neighbor.val, new ArrayList<Node>()));
                    q.add(neighbor);
                }
                res.get(cur).neighbors.add(res.get(neighbor));
            }
        }
        return res.get(node);
    }

    // LeetCode 417. Pacific Atlantic Water Flow
    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        if(heights.length == 0 || heights[0].length == 0) return res;
        int m = heights.length, n = heights[0].length;
        Queue<int[]> pacific = new LinkedList<>();
        Queue<int[]> atlantic = new LinkedList<>();
        // add first row to pacific, last row to atlantic
        for(int i = 0; i < m; i++){
            pacific.add(new int[]{i, 0});
            atlantic.add(new int[]{i, n-1});
        }
        // add first col to pacific, last col to atlantic
        for(int i = 0; i < n; i++){
            pacific.add(new int[]{0, i});
            atlantic.add(new int[]{m-1, i});
        }
        // start bfs, record build two boolean arrays to record whether cur position can flow to each of pacific and atlantic
        boolean[][] pacificFlow = bfs(m, n, heights, pacific);
        boolean[][] atlanticFlow = bfs(m, n, heights, atlantic);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(pacificFlow[i][j] && atlanticFlow[i][j]) res.add(List.of(i, j));
            }
        }
        return res;
        
    }
    
    public static boolean[][] bfs(int m, int n, int[][] heights, Queue<int[]> queue){
        boolean[][] res = new boolean[m][n];
        int[][] directions = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        while(!queue.isEmpty()){
            int[] cur = queue.remove();
            res[cur[0]][cur[1]] = true;
            for(int[] dir: directions){
                // 
                int newR = cur[0] + dir[0];
                int newC = cur[1] + dir[1];
                if(newR < 0 || newR >= m || newC < 0 || newC >= n) continue;
                // check if cur position have been visited
                if(res[newR][newC]) continue;
                // check new position is heigher than cur position
                if(heights[newR][newC] < heights[cur[0]][cur[1]]) continue;
                // checked new pos is heigher than cur pos, add res.
                queue.add(new int[]{newR, newC});
            }
        }
        return res;
    }


    // LeetCode 200. Number of Islands
    public static int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        if(m == 0 || n == 0) return 0;
        int res = 0;
        // loop through row and col, check if cur is '1'
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1') res += dfs(grid, i, j, m, n);
            }
        }
        return res;
    }
    
    public static int dfs(char[][] grid, int row, int col, int m , int n){
        if(row < 0 || col < 0 || row >= m || col >= n || grid[row][col] == '0') return 0;
        // mark grid[row][col] as 0
        grid[row][col] = '0';
        // look search for the four directions(neighbors of grid[row][col])
        dfs(grid, row+1, col, m, n);
        dfs(grid, row-1, col, m, n);
        dfs(grid, row, col+1, m, n);
        dfs(grid, row, col-1, m, n);
        return 1;
    }
}
