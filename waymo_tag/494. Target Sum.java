import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<String, Integer> map = new HashMap<>();

    public int findTargetSumWays(int[] nums, int target) {
        return dfs(0, 0, target, nums);
    }

    private int dfs(int index, int totalSum, int target, int[] nums) {
        String key = index + "" + totalSum;
        if (map.containsKey(key))
            return map.get(key);
        if (index == nums.length)
            return totalSum == target ? 1 : 0;
        int addition = dfs(index + 1, totalSum + nums[index], target, nums);
        int subtraction = dfs(index + 1, totalSum - nums[index], target, nums);
        map.put(key, addition + subtraction);
        return map.get(key);
    }
}
