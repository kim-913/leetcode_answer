import java.util.*;
public class solution_9_22 {
    
    public static void main(String[] args) {
        
    }

    // LC 1239
    public int maxLength(List<String> arr) {
        if(arr == null || arr.size() == 0) return 0;
        return concatenation(arr, 0, "");
    }
    public static int concatenation(List<String> arr, int index, String cur){
        Set<Character> unique = new HashSet<>();
        for(char c: cur.toCharArray()){
            unique.add(c);
        }
        // important, check the end of the recursion
        if(unique.size() != cur.length()) return 0;
        int res = unique.size();
        for(int i = index; i < arr.size(); i++){
            res = Math.max(res, concatenation(arr, i + 1, cur + arr.get(i)));
        }
        return res;
    }



    // LC 79. Word Search
    boolean[][] used;
    int[][] directions = new int[][]{{1,0},{-1,0},{0,-1},{0,1}};
    public boolean exist(char[][] board, String word) {
        int row = board.length, col = board[0].length;
        used = new boolean[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(dfs(board, word, i, j, 0)) return true;
            }
        }
        return false;
    }
    
    public boolean dfs(char[][] board, String word, int x, int y, int length){
        if(word.length() == length) return true;
        if(x >= board.length || x < 0 || y >= board[0].length || y < 0) return false;
        boolean res = false;
        Character ch = word.charAt(length);
        /*
        if(board[x][y] == ch && !used[x][y]){
            used[x][y] = true;
            res = dfs(board, word, x + 1, y, length + 1) || dfs(board, word, x - 1, y, length + 1) || dfs(board, word, x, y + 1, length + 1) || dfs(board, word, x, y - 1, length + 1);
            used[x][y] = false;
        }
        */
        
        for(int[] direction: directions){
            int newX = x + direction[0];
            int newY = y + direction[1];
            if(!used[x][y] && board[x][y] == ch) {
                used[x][y] = true;
                dfs(board, word, newX, newY, length + 1);
                used[x][y] = false;
            }
        }
        return res;
    }
}
