package problems;
import java.util.*;
// DP
class Solution1 {
    public static int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int[] left = new int[height.length]; // idx of the first bar the left that is lower than current
        int[] right = new int[height.length]; // idx of the first bar the right that is lower than current
        right[height.length - 1] = height.length;
        left[0] = -1;
        
        // iterate from left to right
        for(int i = 1; i < height.length; i++){
            int cur = i - 1;
            while(cur >= 0 && height[cur] >= height[i]){
                cur = left[cur];
            }
            left[i] = cur;
        }
        
        // iterate from right to left
        for(int i = height.length - 2; i >= 0; i--){
            int cur = i + 1;
            while(cur < height.length && height[cur] >= height[i]){
                cur = right[cur];
            }
            right[i] = cur;
        }
        
        int res = 0;
        for(int i = 0; i < height.length; i++){
            res= Math.max(res, height[i] * (right[i] - left[i] - 1));
        }
        return res;
    }
}

// Stack
class Solution2 {
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Deque<Integer> stack = new LinkedList<>();
        int p = 0;
        while (p < heights.length) {
            if (stack.isEmpty()) {
                stack.push(p);
                p++;
            } else {
                int top = stack.peek();
                // if current height greater than the max one in stack, push
                if (heights[p] >= heights[top]) {
                    stack.push(p);
                    p++;
                } else {
                    // store the top element of stack
                    int height = heights[stack.pop()];
                    // first left one's index that is less than the current height
                    int leftLessMin = stack.isEmpty() ? -1 : stack.peek();
                    // first right one's index that is less than the current column
                    int RightLessMin = p;
                    int area = (RightLessMin - leftLessMin - 1) * height;
                    maxArea = Math.max(area, maxArea);
                }
            }
        }
        while (!stack.isEmpty()) {
            // store current top elemnt of stack
            int height = heights[stack.pop()];
            // first left one's index that is less than the current height
            int leftLessMin = stack.isEmpty() ? -1 : stack.peek();
            // right doens't have the elment which is less than the current height, so store the most right one's index
            int RightLessMin = heights.length;
            int area = (RightLessMin - leftLessMin - 1) * height;
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }
}