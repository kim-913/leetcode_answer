package problems;
class Solution {
    public int minSwaps(int[] data) {
        int ones = 0;
        for(int num: data){
            if(num == 1) ones++;
        }
        if(ones <= 2) return 0;
        // use sliding window to solve, want to count the current ones in the subarray of length of the ones
        // inside the array, and subtract total numbers of ones and the maximum subarray with most ones 
        // to get the result
        int maxOnesInWindow = 0;
        int left = 0, right = 0;
        int currentOnes = 0;
        while(right < data.length){
            currentOnes += data[right++];
            if(right - left > ones) currentOnes -= data[left++];
            maxOnesInWindow = Math.max(currentOnes, maxOnesInWindow);
        }
        return ones - maxOnesInWindow;
    }
}