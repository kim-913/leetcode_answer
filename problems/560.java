class Solution {
    public int subarraySum(int[] nums, int k) {
        int sum = 0, res = 0;
        Map<Integer, Integer> map = new HashMap();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // if difference of sum between ith and jth index is equal to k, then sum from
            // ith to jth index is k
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put((sum), map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}