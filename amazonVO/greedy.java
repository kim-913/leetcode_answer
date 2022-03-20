// LC 300. Longest Increasing Subsequence
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int i = 0, j = size;
            while (i < j) {
                int m = (i + j) / 2;
                if (tails[m] < x)
                    i = m + 1;
                else
                    j = m;
            }
            tails[i] = x;
            if (i == size)
                ++size;
        }
        return size;
    }
}

// LC 134. Gas Station
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;

        int total_tank = 0;
        int curr_tank = 0;
        int starting_station = 0;
        for (int i = 0; i < n; ++i) {
            total_tank += gas[i] - cost[i];
            curr_tank += gas[i] - cost[i];
            // If one couldn't get here,
            if (curr_tank < 0) {
                // Pick up the next station as the starting one.
                starting_station = i + 1;
                // Start with an empty tank.
                curr_tank = 0;
            }
        }
        return total_tank >= 0 ? starting_station : -1;
    }
}




// LC 1648. Sell Diminishing-Valued Colored Balls
class Solution {
    public int maxProfit(int[] inventory, int orders) {
        Arrays.sort(inventory);
        long ans = 0;
        int n = inventory.length - 1;
        long count = 1;
        while (orders > 0) {
            if (n > 0 && inventory[n] - inventory[n - 1] > 0 && orders >= count * (inventory[n] - inventory[n - 1])) {
                ans += count * sumFromNtoX(inventory[n], inventory[n - 1]);
                orders -= count * (inventory[n] - inventory[n - 1]);
            } else if (n == 0 || inventory[n] - inventory[n - 1] > 0) {
                long a = orders / count;
                ans += count * sumFromNtoX(inventory[n], inventory[n] - a);
                long b = orders % count;
                ans += b * (inventory[n] - a);
                orders = 0;
            }
            ans %= 1000000007;
            n--;
            count++;
        }
        return (int) ans;
    }

    private long sumFromNtoX(long n, long x) {
        return (n * (n + 1)) / 2 - (x * (x + 1)) / 2;
    }
}


// LC 1963. Minimum Number of Swaps to Make the String Balanced
class Solution {
    public int minSwaps(String s) {
        int cnt = 0, res = 0;
        for (char c : s.toCharArray()) {
            if (c == '[')
                cnt++;
            else if (cnt > 0)
                cnt--;
            else {
                res++;
                cnt++;
            }
        }
        return res;
    }
}


// LC 11. Container With Most Water
class Solution {
    public int maxArea(int[] height) {
        int n = height.length;
        int l = 0;
        int r = n - 1;
        int res = 0;
        while (l < r) {
            int min = Math.min(height[l], height[r]);
            res = Math.max(res, min * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return res;
    }
}