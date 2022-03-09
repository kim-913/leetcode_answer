// LC 1838. Frequency of the Most Frequent Element
class Solution {
    public int maxFrequency(int[] A, int k) {
        int res = 1, i = 0, j;
        long sum = 0;
        Arrays.sort(A);
        for (j = 0; j < A.length; ++j) {
            sum += A[j];
            while (sum + k < (long) A[j] * (j - i + 1)) {
                sum -= A[i];
                i += 1;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}


// LC 424. Longest Repeating Character Replacement
class Solution {
    public int characterReplacement(String input, int k) {
        int[] cnt = new int[26];
        int start = 0;
        int res = 1;
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            count = Math.max(count, ++cnt[c - 'A']);
            while (i - start + 1 - count > k) {
                cnt[input.charAt(start++) - 'A']--;
            }
            res = Math.max(res, i - start + 1);
        }
        return res;
    }
}