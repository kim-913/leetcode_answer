import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        int n = board.length;
        Set<Character>[] rows = new HashSet[9];
        Set<Character>[] cols = new HashSet[9];
        Set<Character>[] boxes = new HashSet[9];
        Set<Character>[] boxDiag1 = new HashSet[9];
        Set<Character>[] boxDiag2 = new HashSet[9];
        Set<Character> mainDiag1 = new HashSet<>();
        Set<Character> mainDiag2 = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
            boxDiag1[i] = new HashSet<>();
            boxDiag2[i] = new HashSet<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.')
                    continue;

                int boxIdx = (i / 3) * 3 + j / 3;
                int localRow = i % 3;
                int localCol = j % 3;

                if (!rows[i].add(c) || !cols[j].add(c) || !boxes[boxIdx].add(c)) {
                    return false;
                }

                // Main board diagonals
                if (i == j && !mainDiag1.add(c))
                    return false;
                if (i + j == 8 && !mainDiag2.add(c))
                    return false;

                // Box diagonals
                if (localRow == localCol && !boxDiag1[boxIdx].add(c))
                    return false;
                if (localRow + localCol == 2 && !boxDiag2[boxIdx].add(c))
                    return false;
            }
        }
        return true;
    }
}