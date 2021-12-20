class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int num : nums)
            s.add(num);
        int res = 0;
        for (int num : s) {
            if (!s.contains(num - 1)) {
                int cur = num;
                int length = 1;
                while (s.contains(cur + 1)) {
                    cur++;
                    length++;
                }
                res = Math.max(res, length);
            }
        }
        return res;
    }
}