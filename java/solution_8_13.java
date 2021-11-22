import java.util.*;
public class solution_8_13 {
    
    public static void main(String[] args) {
        
    }

    //LeetCode 74. Search a 2D Matrix
    public boolean searchMatrix(int[][] matrix, int target) {
        // search for column?
        if(matrix == null || matrix.length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int rowNum = 0;
        while(rowNum < m-1 && target > matrix[rowNum][n-1]){
            rowNum++;
        }
        int left = 0, right = n - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(target == matrix[rowNum][mid]) return true;
            else if(target < matrix[rowNum][mid]) right = mid-1;
            else left = mid+1;
        }
        return false;
    }
}
