import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class solution_8_16 {
    
    public static void main(String[] args) {
        
    }

    // LeetCode 658. Find K Closest Elements
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        if(arr.length == 0) return res;
        // sorted, expand from x
        int left = 0, right = arr.length - 1;
        int mid = 0;
        while(left < right){
            mid = left + (right - left) / 2;
            if(Math.abs(arr[left] - x) > Math.abs(arr[right] - x)){
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        /*
        int front = mid - 1, back = mid + 1;
        res.add(arr[mid]);
        k -= 1;
        while(back < arr.length || front >= 0){
            if(k <= 0) break;
            if(front >= 0 && k > 0) {
                if(back == arr.length || Math.abs(arr[front] - x) <= Math.abs(arr[back] - x)) {
                    res.add(arr[front]);
                    k--;
                    front--;
                }
            }
            if(back < arr.length && k > 0) {
                res.add(arr[back]);
                k--;
                back++;
            }
                
        }
        Collections.sort(res);
        return res;
        */
        left -= 1;
        right = left + 1;
        
        // While the window size is less than k
        while (right - left - 1 < k) {
            // Be careful to not go out of bounds
            if (left == -1) {
                right += 1;
                continue;
            }
            
            // Expand the window towards the side with the closer number
            // Be careful to not go out of bounds with the pointers
            if (right == arr.length || Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                left -= 1;
            } else {
                right += 1;
            }
        } 

        // Build and return the window
        for (int i = left + 1; i < right; i++) {
            res.add(arr[i]);
        }
        
        return res;
    }


    // LeetCode 209. Minimum Size Subarray Sum
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, curSum = 0;
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            curSum += nums[i];
            while(curSum >= target){
                // record current result, current subarrays' length
                res = Math.min(res, i + 1 - left);
                curSum -= nums[left++];
            }
        }
        // return if res has been modified or not
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    // LeetCode 904. Fruit Into Basket
    public int totalFruit(int[] arr) {
        int res = 0;
        if(arr.length <= 2) return arr.length;
        int slidePos = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            while (map.size() > 2) {
                map.put(arr[slidePos], map.get(arr[slidePos]) - 1);
                if (map.get(arr[slidePos]) == 0) {
                    map.remove(arr[slidePos]);
                }
                slidePos++;
            }

            res = Math.max(res, i - slidePos + 1);
        }

        return res;
    }
}
