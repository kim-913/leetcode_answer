import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    // time complexity: (nlogn)
    public boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0)
            return false;
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);
        for (int num : nums) {
            if (!map.containsKey(num) || map.get(num) == 0)
                continue;
            int freq = map.get(num);
            for (int i = 0; i < k; i++) {
                int consecutiveKey = num + i;
                if (map.getOrDefault(consecutiveKey, 0) < freq)
                    return false;
                else
                    map.put(consecutiveKey, map.get(consecutiveKey) - 1);
            }
        }
        return true;
    }
}