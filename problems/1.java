package problems;
import java.util.*;
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // solution 1: two for loops
        /*
        int[] res = new int[2];
        for(int i = 0; i < nums.length - 1; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
        */
        // solution 2: one loop and a binary search, but a map required becasue we need to sort the array while the index change
        /*
        Arrays.sort(nums);
        int[] res = new int[2];
        int left = 0, right = nums.length - 1;
        for(int i = 0; i < nums.length; i++){
            while(left <= right){
                int mid = left + (right - left) / 2;
                if(nums[i] + nums[mid] == target) {
                    res[0] = i;
                    res[1] = mid;
                }else if(nums[i] + nums[mid] < target) {
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
        }
        */
        // solution 3: hashmap one pass
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])) {
                res[0] = map.get(target - nums[i]);
                res[1] = i;
                continue;
            }
            map.put(nums[i], i);
        }
        return res;
    }
}