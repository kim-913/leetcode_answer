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
    // Note that to avoid index out of bound, have to set i > cur
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs1(nums, 0, new ArrayList<Integer>(), res);
        return res;
    }
    
    public static void dfs1(int[] nums, int cur, List<Integer> list, List<List<Integer>> res){
        res.add(new ArrayList<>(list));
        for(int i = cur; i < nums.length; i++){
            if(i > cur && nums[i] == nums[i-1]) continue;
            list.add(nums[i]);
            dfs1(nums, i+1, list, res);
            list.remove(list.size()-1);
        }
    }

    // LeetCode 46 Permutations
    // Difference between permutations and subset is that the index alawys 
    // start from 0 in the for loop
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs2(nums, new ArrayList<Integer>(), res);
        return res;
    }
    
    public static void dfs2(int[] nums, List<Integer> list, List<List<Integer>> res){
        if(list.size() == nums.length) res.add(new ArrayList<>(list));
        for(int i = 0; i < nums.length; i++){
            if(!list.contains(nums[i])){
                list.add(nums[i]);
                dfs2(nums, list, res);
                list.remove(list.size()-1);
            }
        }
    }

}
