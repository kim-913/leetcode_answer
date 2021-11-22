import java.util.*;
public class solution_8_12 {
    
    public static void main(String[] args) {
        
    }

    // LeetCode 153. Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(mid > 0 && nums[mid] < nums[mid-1]) return nums[mid];
            else if(nums[left] <= nums[mid] && nums[mid] > nums[right]) left = mid + 1;
            else right = mid - 1;
        }
        return nums[left];
    }

    // LeetCode 162. Find Peak Element
    public int findPeakElement(int[] nums) {
        int start = 0, end = nums.length - 1;
        if(nums.length == 2) return nums[0] > nums[1] ? 0: 1;
        while(start < end){
            int mid = start + (end - start) / 2;
            if(mid > 0 && nums[mid] > nums[mid-1] && nums[mid] > nums[mid + 1]) return mid;
            // move start
            if(mid > 0 && nums[mid] > nums[mid-1] && nums[mid] < nums[mid + 1]) start = mid + 1;
            else end = mid;
        }
        return start;
    }

    // LeetCode 33. Search in Rotated Sorted Array
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) return mid;
            else if(nums[left] <= nums[mid]){
                if(target >= nums[left] && target < nums[mid]) {
                    right = mid-1;
                } else {
                    left = mid+1;
                }
            } else {
                if(target <= nums[right] && target > nums[mid]){
                    left = mid+1;
                } else{
                    right = mid-1;
                }
            }
        }
        return -1;
    }
}
