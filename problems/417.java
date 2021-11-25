package problems;
class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        if(heights == null || heights.length == 0 || heights[0].length == 0) return res;
        int row = heights.length;
        int col = heights[0].length;
        boolean[][] pacific = new boolean[row][col];
        boolean[][] atlantic = new boolean[row][col];
        // next to the ocean
        for(int i = 0; i < row; i++){
            dfs(heights, pacific, i, 0);
            dfs(heights, atlantic, i, col - 1);
        }
        for(int i = 0; i < col; i++){
            dfs(heights, pacific, 0, i);
            dfs(heights, atlantic, row - 1, i);
        }
        // then we've fixed the boolean array, ready to add to result
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(pacific[i][j] && atlantic[i][j]) res.add(List.of(i, j));
            }
        }
        return res;
    }
    
    private void dfs(int[][] heights, boolean[][] bool, int curRow, int curCol){
        // first, mark that we've reached 
        bool[curRow][curCol] = true;
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        for(int[] direction: directions){
            int newRow = curRow + direction[0];
            int newCol = curCol + direction[1];
            if(newRow < 0 || newRow >= heights.length || newCol < 0 || newCol >= heights[0].length){
                continue;
            }
            if(bool[newRow][newCol]) continue;
            // determine if cur location is grarter than or equal to previous
            if(heights[newRow][newCol] < heights[curRow][curCol]) continue;
            dfs(heights, bool, newRow, newCol);
        }
    }
}