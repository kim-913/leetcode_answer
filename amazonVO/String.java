// LC 696. Count Binary Substrings
class Solution {
    public int countBinarySubstrings(String s) {
        int prev = 0, cur = 1, res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1))
                cur++;
            else {
                prev = cur;
                cur = 1;
            }
            if (prev >= cur)
                res++;
        }
        return res;
    }
}

// LC 43. Multiply Strings
class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] ansArr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ansArr[i + j + 1] += x * y;
            }
        }
        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }
        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuffer ans = new StringBuffer();
        while (index < m + n) {
            ans.append(ansArr[index++]);
        }
        return ans.toString();
    }
}



// Reverse String, maintain space and 标点符号
class Solution {
    public static String reverseString(String s) {
        StringBuilder sb = new StringBuilder(s);
        reverse(sb, 0, s.length() - 1);
        reverseEachWord(sb);
        return sb.toString();
    }

    public static void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    private static void reverseEachWord(StringBuilder sb) {
        int left = 0, right = 0;
        int n = sb.length();
        while (left < n) {
            while (right < n && Character.isAlphabetic(sb.charAt(right)))
                right++;
            reverse(sb, left, right - 1);
            left = right + 1;
            right++;
        }
    }
}


// LC 38. Count and Say
public class Solution {
    public String countAndSay(int n) {
        if (n <= 0)
            return "-1";
        String result = "1";

        for (int i = 1; i < n; i++) {
            result = build(result);
        }
        return result;
    }

    private String build(String result) {
        StringBuilder builder = new StringBuilder();
        int p = 0;
        while (p < result.length()) {
            char val = result.charAt(p);
            int count = 0;

            while (p < result.length() &&
                    result.charAt(p) == val) {
                p++;
                count++;
            }
            builder.append(String.valueOf(count));
            builder.append(val);
        }
        return builder.toString();
    }
}


// LC 926. Flip String to Monotone Increasing
class Solution {
    public int minFlipsMonoIncr(String S) {
        if (S == null || S.length() <= 1)
            return 0;
        int n = S.length();
        int cntEndWithOne = 0, cntEndWithZero = 0;

        for (int i = 0; i < n; i++) {
            char c = S.charAt(i);
            cntEndWithOne = Math.min(cntEndWithZero, cntEndWithOne) + (c == '1' ? 0 : 1);
            cntEndWithZero += (c == '0' ? 0 : 1);
        }
        return Math.min(cntEndWithOne, cntEndWithZero);
    }
}


// LC 438. Find All Anagrams in a String
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int n = s.length(), m = p.length();
        if (n < m)
            return res;
        int[] sCnt = new int[26];
        int[] pCnt = new int[26];
        for (int i = 0; i < Math.min(n, m); i++)
            sCnt[s.charAt(i) - 'a']++;
        for (int i = 0; i < Math.min(n, m); i++)
            pCnt[p.charAt(i) - 'a']++;
        if (Arrays.equals(sCnt, pCnt))
            res.add(0);
        for (int i = m; i < n; i++) {
            sCnt[s.charAt(i - m) - 'a']--;
            sCnt[s.charAt(i) - 'a']++;
            if (Arrays.equals(sCnt, pCnt))
                res.add(i - m + 1);
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
        boolean goingDown = false;
        // append a string builder to each row
        for (int i = 0; i < numRows; i++)
            list.add(new StringBuilder());
        int curRow = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            list.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1)
                goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : list)
            res.append(row);
        return res.toString();
    }
}


// LC 394. Decode String
class Solution {
    public String decodeString(String s) {
        Stack<Integer> count = new Stack<>();
        Stack<StringBuilder> build_String = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                k = k * 10 + s.charAt(i) - '0';
            } else if (s.charAt(i) == '[') {
                count.push(k);
                build_String.push(cur);
                cur = new StringBuilder();
                k = 0;
            } else if (s.charAt(i) == ']') {
                int countK = count.pop();
                StringBuilder temp = build_String.pop();
                while (countK-- > 0) {
                    temp.append(cur);
                }
                cur = temp;
            } else {
                cur.append(s.charAt(i));
            }
        }
        return cur.toString();
    }
}


// LC 316. Remove Duplicate Letters
class Solution {
    public String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        // map records the char and the last index occured for the char
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> seen = new HashSet<>();
        for (int i = 0; i < s.length(); i++)
            map.put(s.charAt(i), i);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!seen.contains(c)) {
                while (!stack.isEmpty() && c < stack.peek() && map.get(stack.peek()) > i) {
                    seen.remove(stack.pop());
                }
                seen.add(c);
                stack.add(c);
            }
        }
        StringBuilder sb = new StringBuilder(stack.size());
        for (Character c : stack)
            sb.append(c.charValue());
        return sb.toString();
    }
}


// LC 49. Group Anagrams
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}