import java.util.ArrayList;
import java.util.List;

/**
 * solution7_20
 */
public class solution7_20 {

    public static void main(String[] args) {
        
    }

    //48. Rotate Image
    public static void rotate(int[][] matrix) {
        int m = matrix.length;
        //transpose first
        /*
        [1][2][3]    [1][4][7]
        [4][5][6] -> [2][5][8]
        [7][8][9]    [3][6][9]
        */
        for(int i = 0; i < m; i++){
            for(int j = i; j < m; j++){
                int cur = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = cur;
            }
        }
        //left to right
        for(int i = 0; i < m; i++){
            for(int j = 0; j < m/2; j++){
                int cur = matrix[i][j];
                matrix[i][j] = matrix[i][m-j-1];
                matrix[i][m-j-1] = cur;
            }
        }
    }

    public static List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        dfs(s.toCharArray(), 0, res);
        return res;
    }
    public static void dfs(char[] cur, int index, List<String> res){
        if(index == cur.length){
            res.add(new String(cur));
            return;
        }
        if(Character.isLetter(cur[index])) {
            cur[index] = Character.toUpperCase(cur[index]);
            dfs(cur, index+1, res);
            cur[index] = Character.toLowerCase(cur[index]);
            dfs(cur, index+1, res);
        } else{
            dfs(cur, index+1, res);
        }
    }
}
