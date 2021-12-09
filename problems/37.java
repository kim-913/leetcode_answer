class Solution {
    public void solveSudoku(char[][] board) {
        /*
         * use backtrack to solve this problem, the key point is that we have to check
         * the current cell, check if we place number from 1-9, is it valid? if it is,
         * we can start to do recursion from here. otherwise, we have to backtrack and
         * change the state for the cell
         */
        if (board == null || board.length == 0)
            return;
        solve(board);
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            // if there are same character in the same row
            if (board[i][col] != '.' && board[i][col] == c)
                return false;
            // if there are same character in the same col
            if (board[row][i] != '.' && board[row][i] == c)
                return false;
            // check the 3 * 3 smaller grid
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.'
                    && board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c)
                return false;
        }
        return true;
    }

    private boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solve(board)) {
                                return true;
                            } else {
                                // backtrack to previous state
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}