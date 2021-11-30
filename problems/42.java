package problems;
import java.util.*;
// Two Pointers
class Solution1 {
    public int trap(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int maxL = height[l];
        int maxR = height[r];
        int ans = 0;
        while(l < r){
            // make sure the reason why we are comparing the global left max and right max is because that the trapped water is determined by these two variables instead of the current left and right.
            if(maxL < maxR) {
                ans += maxL - height[l];
                maxL = Math.max(maxL, height[++l]);
            } else {
                ans += maxR - height[r];
                maxR = Math.max(maxR, height[--r]);
            }
        }
        return ans;
    }

    // DP
    class Solution2 {
        // scan both from left to right and right to left to find the local maximum
        public int trap(int[] height) {
            int res = 0;
            int[] left = new int[height.length];
            int[] right = new int[height.length];
            for(int i = 0; i < height.length; i++){
                left[i] = i == 0 ? height[i] : Math.max(height[i], left[i - 1]);
            }
            for(int i = height.length - 1; i >= 0; i--){
                right[i] = i == height.length - 1 ? height[i] : Math.max(height[i], right[i + 1]);
            }
            
            // current trapped water amount = min(right, left) - h[i]
            for(int i = 0; i < height.length; i++){
                res += Math.min(right[i], left[i]) - height[i];
            }
            return res;
        }
    }

    // find by each line(height)
    class Solution3 {        
        public int trap(int[] height) {
            int sum = 0;
            int max = getMax(height);//find the global maximum height 
            for (int i = 1; i <= max; i++) {
                boolean isStart = false; // records whether we've started modifiying
                int temp_sum = 0;
                for (int j = 0; j < height.length; j++) {
                    if (isStart && height[j] < i) {
                        temp_sum++;
                    }
                    if (height[j] >= i) {
                        sum = sum + temp_sum;
                        temp_sum = 0;
                        isStart = true;
                    }
                }
            }
            return sum;
        }
        private int getMax(int[] height) {
            int max = 0;
            for (int i = 0; i < height.length; i++) {
                if (height[i] > max) {
                    max = height[i];
                }
            }
            return max;
        }
    }

    // find by each column
    class Solution4 {
        public int trap(int[] height) {
            int sum = 0;
            // don't have to consider the first column because there won't be trapped
            // thus, we iterate from index to length - 2
            for (int i = 1; i < height.length - 1; i++) {
                int max_left = 0;
                // find the highest for the left
                for (int j = i - 1; j >= 0; j--) {
                    if (height[j] > max_left) {
                        max_left = height[j];
                    }
                }
                int max_right = 0;
                // find the highest for the right
                for (int j = i + 1; j < height.length; j++) {
                    if (height[j] > max_right) {
                        max_right = height[j];
                    }
                }
                // compare and find the minimum between left_max and right_max
                int min = Math.min(max_left, max_right);
                // there will be trapped water only if the smaller max is greater than current height
                if (min > height[i]) {
                    sum = sum + (min - height[i]);
                }
            }
            return sum;
        }
    }

    // Stack
    class Solution5 {
        public int trap(int[] height) {
            int sum = 0;
            Deque<Integer> stack = new LinkedList<Integer>();
            int current = 0;
            while(current < height.length) {
                while(!stack.isEmpty() && height[current] > height[stack.peek()]) {
                    int h = height[stack.pop()];
                    if(stack.isEmpty()) break;
                    int distance = current - stack.peek() - 1;
                    int min = Math.min(height[stack.peek()], height[current]);
                    sum += distance * (min - h);
                }
                stack.push(current);
                current++;
            }
            return sum;
        }
    }
}