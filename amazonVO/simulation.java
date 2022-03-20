// LC 1275. Find Winner on a Tic Tac Toe Game
class Solution {
    public String tictactoe(int[][] moves) {
        int[] aRow = new int[3], aCol = new int[3], bRow = new int[3], bCol = new int[3];
        int aD1 = 0, aD2 = 0, bD1 = 0, bD2 = 0;
        for (int i = 0; i < moves.length; ++i) {
            int r = moves[i][0], c = moves[i][1];
            if (i % 2 == 1) {
                if (++bRow[r] == 3 || ++bCol[c] == 3 || r == c && ++bD1 == 3 || r + c == 2 && ++bD2 == 3)
                    return "B";

            } else {
                if (++aRow[r] == 3 || ++aCol[c] == 3 || r == c && ++aD1 == 3 || r + c == 2 && ++aD2 == 3)
                    return "A";
            }
        }
        return moves.length == 9 ? "Draw" : "Pending";
    }
}


// LC 48. Rotate Image
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }
}


// LC 273. Integer to English Words
class Solution {
    String[] bigUnits = { "", " Thousand", " Million", " Billion" };
    String[] digits = { "", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine" };
    String[] tens = { "", "", " Twenty", " Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty", " Ninety" };
    String[] tenToTwenty = { " Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen",
            " Seventeen", " Eighteen", " Nineteen" };

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuilder result = new StringBuilder();
        int bigUnitIndex = 0;
        while (num != 0) {
            if (num % 1000 != 0) {
                result.insert(0, parseThreeDigits(num % 1000) + bigUnits[bigUnitIndex] + "");
            }
            num /= 1000;
            bigUnitIndex++;
        }

        return result.toString().trim();
    }

    private String parseThreeDigits(int num) {
        StringBuilder result = new StringBuilder();
        if (num > 99) {
            result.append(digits[num / 100] + " Hundred");
            num = num % 100;
        }
        if (num > 19) {
            result.append(tens[num / 10]);
            num = num % 10;
        }
        if (num > 9) {
            result.append(tenToTwenty[num % 10]);
            return result.toString();
        }

        return result.append(digits[num]).toString();
    }
}