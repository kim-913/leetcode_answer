class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Set<Integer> zeroRow = new HashSet<>();
        Set<Integer> zeroCol = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    zeroRow.add(i);
                    zeroCol.add(j);
                }
            }
        }
        for (int num : zeroRow) {
            for (int j = 0; j < n; j++) {
                matrix[num][j] = 0;
            }
        }
        for (int num : zeroCol) {
            for (int i = 0; i < m; i++) {
                matrix[i][num] = 0;
            }
        }
    }
}