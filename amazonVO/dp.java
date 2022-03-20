// LC 123. Best Time to Buy and Sell Stock III
class Solution {
    public int maxProfit(int[] prices) {
        int sell1 = 0, sell2 = 0, buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
            System.out.println(buy1 + "buy1 " + sell1 + "sell1 " + buy2 + "buy2 " + sell2 + "sell2");
        }
        return sell2;
    }
}

// LC 828. Count Unique Characters of All Substrings of a Given String
/*
 * Let's think about how a character can be found as a unique character.
 * 
 * Think about string "XAXAXXAX" and focus on making the second "A" a unique
 * character.
 * We can take "XA(XAXX)AX" and between "()" is our substring.
 * We can see here, to make the second "A" counted as a uniq character, we need
 * to:
 * 
 * insert "(" somewhere between the first and second A
 * insert ")" somewhere between the second and third A
 * For step 1 we have "A(XA" and "AX(A", 2 possibility.
 * For step 2 we have "A)XXA", "AX)XA" and "AXX)A", 3 possibilities.
 
 * index[26][2] record last two occurrence index for every upper characters.
 * Initialise all values in index to -1.
 * Loop on string S, for every character c, update its last two occurrence index
 * to index[c].
 * Count when loop. For example, if "A" appears twice at index 3, 6, 9
 * seperately, we need to count:
 * For the first "A": (6-3) * (3-(-1))"
 * For the second "A": (9-6) * (6-3)"
 * For the third "A": (N-9) * (9-6)"
 */
class Solution {
    public int uniqueLetterString(String s) {
        int n = s.length();
        // record the last two accurence of a char
        int[][] index = new int[26][2];
        for (int i = 0; i < 26; i++)
            Arrays.fill(index[i], -1);
        int res = 0;
        int mod = (int) Math.pow(10, 9) + 7;
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'A';
            res = (res + (i - index[c][1]) * (index[c][1] - index[c][0]) % mod) % mod;
            index[c] = new int[] { index[c][1], i };
        }
        for (int c = 0; c < 26; c++) {
            res = (res + (n - index[c][1]) * (index[c][1] - index[c][0]) % mod) % mod;
        }
        return res;
    }
}

// Robort and box
class Solution {
    private static int robotPower(int[] box, int power) {
        int[] dp = new int[power + 1];
        Arrays.fill(dp, power + 1);
        dp[0] = 0;
        for (int i = 1; i <= power; i++) {
            for (int j = 0; j < box.length; j++) {
                if (box[j] <= i) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - box[j]]);
                }
            }
        }
        return dp[power] > power ? -1 : dp[power];
    }
}


// Jump Game 2
class Solution {
    public int jump(int[] nums) {
        int jumps = 0, currentJumpEnd = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // we continuously find the how far we can reach in the current jump
            farthest = Math.max(farthest, i + nums[i]);
            // if we have come to the end of the current jump,
            // we need to make another jump
            if (i == currentJumpEnd) {
                jumps++;
                currentJumpEnd = farthest;
            }
        }
        return jumps;
    }
}



// LC 42. Trapping Rain Water
// BF
class Solution {
    public int trap(int[] height) {
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            int leftMax = 0, rightMax = 0;
            for (int k = i; k >= 0; k--) {
                leftMax = Math.max(leftMax, height[k]);
            }
            for (int j = i; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            res += Math.min(leftMax, rightMax) - height[i];
        }
        return res;
    }
}
class Solution {
    public int trap(int[] height) {
        int l = 0, r = height.length - 1;
        int maxL = height[l];
        int maxR = height[r];
        int res = 0;
        while (l < r) {
            if (maxL < maxR) {
                res += maxL - height[l];
                maxL = Math.max(maxL, height[++l]);
            } else {
                res += maxR - height[r];
                maxR = Math.max(maxR, height[--r]);
            }
        }
        return res;
    }
}


// LC 63. Unique Paths II
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1)
            return 0;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] == 1 && obstacleGrid[i][0] == 0 ? 1 : 0;
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] == 1 && obstacleGrid[0][i] == 0 ? 1 : 0;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = obstacleGrid[i][j] == 0 ? dp[i - 1][j] + dp[i][j - 1] : 0;
            }
        }
        return dp[m - 1][n - 1];
    }
}



// LC 279. Perfect Squares
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 1);
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            if ((int) Math.sqrt(i) * (int) Math.sqrt(i) == i) {
                dp[i] = 1;
                continue;
            }
            for (int j = 1; j < i; j++) {
                dp[i] = Math.min(dp[i], Math.min(dp[i - 1] + 1, dp[i - j] + dp[j]));
            }
        }
        return dp[n];
    }
}


// LC 300. Longest Increasing Subsequence
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int c : dp) {
            res = Math.max(res, c);
        }
        return res;
    }
}


// LC 6. Zigzag Conversion
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++)
            list.add(new StringBuilder());
        boolean goingDown = false;
        int curRow = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            list.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1)
                goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder sb : list) {
            res.append(sb);
        }
        return res.toString();
    }
}



// LC 926. Flip String to Monotone Increasing
class Solution {
    public int minFlipsMonoIncr(String S) {
        int n = S.length();
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + (S.charAt(i - 1) - '0' == 1 ? 1 : 0);
        }
        // then, the total number of flip for each position is the total number of 1
        // ones before i that need to be flipped to zero plus the number after this i
        // that need to be flipped to 1
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            res = Math.min(res, prefix[i] + (n - i - (prefix[n] - prefix[i])));
        }
        return res;
    }
}



//
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet(wordDict);
        int maxLen = 0;
        for (String word : wordDict)
            maxLen = Math.max(maxLen, word.length());
        boolean[] dp = new boolean[s.length() + 1]; // dp[i]: the word end with s[i - 1] can be divided or not
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0 && j >= i - maxLen; j--) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}


// LC 1567. Maximum Length of Subarray With Positive Product
class Solution {
    public int getMaxLen(int[] nums) {
        int res = 0;
        int pos = 0;
        int neg = 0;

        for (int num : nums) {
            if (num == 0) {
                pos = 0;
                neg = 0;
            } else if (num > 0) {
                pos++;
                neg = neg == 0 ? 0 : neg + 1;
            } else {
                int temp = pos;
                pos = neg == 0 ? 0 : neg + 1;
                neg = temp + 1;
            }
            res = Math.max(res, pos);
        }
        return res;
    }
}