// 238. Product of Array Except Self
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            dp[i] *= right;
            right *= nums[i];
        }
        return dp;
    }
}



// LC 370. Range Addition
class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        for (int[] update : updates) {
            int value = update[2];
            int start = update[0];
            int end = update[1];
            res[start] += value;
            if (end < length - 1)
                res[end + 1] -= value;
        }

        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += res[i];
            res[i] = sum;
        }

        return res;
    }
}


// LC 523. Continuous Subarray Sum
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        // important
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum != 0)
                sum %= k;
            Integer prev = map.get(sum);
            if (prev != null) {
                if (i - prev > 1)
                    return true;
            } else
                map.put(sum, i);
        }
        return false;
    }
}



// LC 1248. Count Number of Nice Subarrays
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int cntEven = 0;
        int left = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0)
                cntEven++;
            while (i - left + 1 - cntEven > k) {
                res++;
                cntEven -= (nums[left++] % 2 == 0 ? 1 : 0);
            }
        }
        return res;
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



// 2021
class Solution {
    public int brightestPosition(int[][] lights) {
        // use treemap because the prefix sum need to be sorted
        TreeMap<Integer, Integer> line = new TreeMap<>();
        int bright = 0, max_bright = 0, res = 0;
        for (int[] l : lights) {
            line.put(l[0] - l[1], line.getOrDefault(l[0] - l[1], 0) + 1);
            line.put(l[0] + l[1] + 1, line.getOrDefault(l[0] + l[1] + 1, 0) - 1);
        }
        for (Integer light : line.keySet()) {
            bright += line.get(light);
            if (bright > max_bright) {
                max_bright = bright;
                res = light;
            }
        }
        return res;
    }
}