import java.util.List;

/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 * public int get(int row, int col) {}
 * public List<Integer> dimensions {}
 * };
 */

class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int m = binaryMatrix.dimensions().get(0);
        int n = binaryMatrix.dimensions().get(1);
        int ans = -1, row = 0, col = n - 1;
        while (row < m && col >= 0) {
            if (binaryMatrix.get(row, col) == 1) {
                ans = col; // record as current ans
                col--;
            } else {
                row++;
            }
        }
        return ans;
    }

    /*
     * Since each row is sorted, all 1s are to the right.
     * 
     * Starting from top-right, each move left = more 1s in current row.
     * 
     * As soon as we go left, we know this row has at least as many 1s as previous.
     * 
     * Track the row where we make the furthest left move.
     */
    public int rowWithMostOnes(BinaryMatrix binaryMatrix) {
        List<Integer> dims = binaryMatrix.dimensions();
        int rows = dims.get(0), cols = dims.get(1);

        int row = 0, col = cols - 1;
        int maxRow = -1;

        while (row < rows && col >= 0) {
            if (binaryMatrix.get(row, col) == 1) {
                maxRow = row; // update candidate
                col--; // try to go left for more 1s
            } else {
                row++; // try next row
            }
        }

        return maxRow;
    }
}