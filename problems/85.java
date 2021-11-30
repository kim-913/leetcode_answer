package problems;

// DP solution
// built on top of 84
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0) return 0;
        // for each cell with value=1, we look upward (north), the number of continuous '1' is the height of cell
        int[] heights = new int[matrix[0].length];
        int maxArea = -1;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j]=='0'){
                    heights[j] = 0;
                } else {
                    heights[j] ++;
                }
            }            
            int area = largestRectangleArea(heights);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
    
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