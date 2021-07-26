import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class solution7_22 {

    public static void main(String[] args) {
        

    }


    //LeetCode 77: Combination
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(1, n,k,res, new ArrayList<>());
        return res;
        
    }
    
    //make sure when do we want to add the current num into the list
    public static void dfs(int cur, int n, int k, List<List<Integer>> res, List<Integer> list){
        if(list.size() == k) res.add(new ArrayList<>(list));
        for(int i = cur; i <= n; i++){
            if(list.size() <= k) {
                list.add(i);
                dfs(i+1, n, k, res, list);
                list.remove(list.size()-1);
            }
        }
    }

    // LeetCode 47 Permutations II
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, new ArrayList<>(), res, new boolean[nums.length]);
        return res;
    }
    
    public static void dfs(int[] nums, List<Integer> list, List<List<Integer>> res, boolean[] used){
        if(list.size() == nums.length) res.add(new ArrayList<>(list));
        for(int i = 0 ; i < nums.length; i++){
            if((i > 0 && nums[i] == nums[i-1] && !used[i-1]) || used[i]) continue;
            used[i] = true;
            list.add(nums[i]);
            dfs(nums, list, res, used);
            used[i] = false;
            list.remove(list.size()-1);
        }
    }

    // LeetCode 39: Combination Sum
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }
    
    public void dfs(int[] candidates, int target, int cur, List<Integer> list, List<List<Integer>> res){
        if(target < 0) return;
        if(target == 0) res.add(new ArrayList<>(list));
        for(int i = cur; i < candidates.length; i++){
            list.add(candidates[i]);
            dfs(candidates, target - candidates[i], i, list, res);
            list.remove(list.size()-1);
        }
    }
}
