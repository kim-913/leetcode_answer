import java.util.ArrayList;
import java.util.List;

/**
 * 7.19
 */
public class solution {

    public static void main(String[] args) {
        int[] test = { 1, 3, 3, 4, 6, 6, 5, 8 };
        List<Integer> result = findDuplicates(test);
        System.out.print(result);

    }

    // LeetCode 442
    public static List<Integer> findDuplicates(int[] nums) {
        // mark seen element in the array
        int[] mark = new int[nums.length];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (mark[nums[i] - 1] != -1)
                mark[nums[i] - 1] = -1;
            else
                res.add(nums[i]);
        }
        return res;
    }
}