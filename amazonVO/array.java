// LC 1010. Pairs of Songs With Total Durations Divisible by 60
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int array[] = new int[60];
        int retVal = 0;
        for (int i : time) {
            int n = i % 60;
            retVal += array[n == 0 ? 0 : 60 - n];
            array[n]++;
        }
        return retVal;
    }
}


// LC 2104. Sum of Subarray Ranges
class Solution {
    public long subArrayRanges(int[] nums) {
        long res = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int max = nums[i], min = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                max = Math.max(max, nums[j]);
                min = Math.min(min, nums[j]);
                res += max - min;
            }
        }
        return res;
    }
}


// LC 135. Candy
public class Solution {
    public int candy(int[] ratings) {
        int candies[] = new int[ratings.length];
        Arrays.fill(candies, 1);

        for (int i = 1; i < candies.length; i++) {
            if (ratings[i] > ratings[i - 1])
                candies[i] = (candies[i - 1] + 1);
        }

        for (int i = candies.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1])
                candies[i] = Math.max(candies[i], (candies[i + 1] + 1));
        }

        int sum = 0;
        for (int candy : candies)
            sum += candy;
        return sum;
    }
}


// LC 1710 Maximum Units on a Truck
class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int res = 0;
        for (int[] box : boxTypes) {
            int count = Math.min(truckSize, box[0]);
            res += count * box[1];
            truckSize -= count;
            if (truckSize == 0)
                break;
        }
        return res;
    }
}


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


// LC 2078. Two Furthest Houses With Different Colors
class Solution {
    public int maxDistance(int[] colors) {
        int n = colors.length;
        int cur = 0;
        int res = 0;
        int left = 0, right = n - 1;
        while (left < right) {
            if (colors[left] != colors[right]) {
                res = Math.max(res, right - left);
            }
            right--;
        }
        left = 0;
        right = n - 1;
        while (left < right) {
            if (colors[left] != colors[right]) {
                res = Math.max(res, Math.abs(left - right));
            }
            left++;
        }
        return res;
    }
}


// LC 80. Remove Duplicates from Sorted Array II
class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int count = 1;
        int j = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }

            if (count <= 2)
                nums[j++] = nums[i];
        }
        return j;
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


// LC 1167. Minimum Cost to Connect Sticks
class Solution {
    public int connectSticks(int[] sticks) {
        int totalCost = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int stick : sticks) {
            pq.add(stick);
        }
        while (pq.size() > 1) {
            int stick1 = pq.remove();
            int stick2 = pq.remove();

            int cost = stick1 + stick2;
            totalCost += cost;

            pq.add(stick1 + stick2);
        }

        return totalCost;
    }
}


// 1268. Search Suggestions System
class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        // O(nlogn) -quick sort
        Arrays.sort(products);

        // O(mn)
        // O(n)
        List<List<String>> res = new ArrayList<>();
        for (int i = 1; i <= searchWord.length(); i++) {
            List<String> list = new ArrayList<>();
            String prefix = searchWord.substring(0, i);
            // int count = 0;
            for (String product : products) {
                if (product.indexOf(prefix) == 0) {
                    if (list.size() < 3)
                        list.add(product);
                    else
                        break;
                }
            }
            res.add(list);
            list = new ArrayList<>();
        }
        return res;
    }
}


// LC 1710. Maximum Units on a Truck
class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int res = 0;
        for (int[] box : boxTypes) {
            int count = Math.min(truckSize, box[0]);
            res += count * box[1];
            truckSize -= count;
            if (truckSize == 0)
                break;
        }
        return res;
    }
}