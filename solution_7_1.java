import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class solution_7_1 {
    public static void main(String[] args) {
        
    }

    // LeetCode 217. Contains Duplicate
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for(int num: nums){
            s.add(num);
        }
        return s.size() < nums.length;
    }

    // LeetCode 268: Missing Number
    public static int missingNumber(int[] nums) {
        // Map, Set, Binary search
        Arrays.sort(nums);
        int left = 0, right = nums.length;
        while(left < right){
            int mid = left + (right-left)/2;
            if(nums[mid] > mid) right = mid;
            else left = mid+1;
        }
        return left;
    }

    // LeetCode 448: Find All Numbers Disappeared in an Array
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        int size = nums.length;
        List<Integer> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            set.add(nums[i]);
        }
        for(int i = 1; i <= size; i++){
            if(!set.contains(i)) res.add(i);
        }
        return res;
    }
}
