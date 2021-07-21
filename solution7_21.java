import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class solution7_21 {
    public static void main(String[] args) {
        
    }

    // LeetCode: 78 Subsets
    // Typical dfs, not in the for loop i starts from cur_index
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, new ArrayList<Integer>(), res);
        return res;
    }
    
    public static void dfs(int[] nums, int cur, List<Integer> list, List<List<Integer>> res){
        res.add(new ArrayList<>(list));
        for(int i = cur; i < nums.length; i++){
            list.add(nums[i]);
            dfs(nums, i+1, list, res);
            list.remove(list.size()-1);
        }
    }

    // LeetCode 90 Subsets2
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<Integer>(), res);
        return res;
    }
    
    public static void dfs(int[] nums, int cur, List<Integer> list, List<List<Integer>> res){
        res.add(new ArrayList<>(list));
        for(int i = cur; i < nums.length; i++){
            if(i > cur && nums[i] == nums[i-1]) continue;
            list.add(nums[i]);
            dfs(nums, i+1, list, res);
            list.remove(list.size()-1);
        }
    }
}
