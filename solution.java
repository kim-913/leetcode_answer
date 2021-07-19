import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 7.19
 */
public class solution {

    public static void main(String[] args) {
        int[] test = { 1, 3, 3, 4, 6, 6, 5, 8 };
        List<Integer> result = findDuplicates(test);
        System.out.print(result);

    }

    // LeetCode 442
    public static List<Integer> findDuplicates(int[] nums) {
        // mark seen element in the array
        int[] mark = new int[nums.length];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (mark[nums[i] - 1] != -1)
                mark[nums[i] - 1] = -1;
            else
                res.add(nums[i]);
        }
        return res;
    }

    // LeetCode 73
    public static void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        Set<Integer> col = new HashSet();
        Set<Integer> row = new HashSet();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    col.add(j);
                    row.add(i);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row.contains(i) || col.contains(j))
                    matrix[i][j] = 0;
            }
        }
        return;
    }
}