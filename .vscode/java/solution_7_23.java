import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class solution_7_23 {

    public static void main(String[] args) {
        
    }

    // LeetCode 40: Combination Sum II
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[candidates.length];
        dfs(candidates, target, new ArrayList<>(), res, 0, used);
        return res;
    }
    
    public static void dfs(int[] candidates, int target, List<Integer> list, List<List<Integer>> res, int cur, boolean[] used){
        if(target < 0) return;
        if(target == 0) res.add(new ArrayList<>(list));
        for(int i = cur; i < candidates.length; i++){
            if((i > 0 && candidates[i] == candidates[i-1] && !used[i-1]) || used[i]) continue;
            used[i] = true;
            list.add(candidates[i]);
            dfs(candidates, target - candidates[i], list, res, i+1, used);
            used[i] = false;
            list.remove(list.size()-1);
        }
    }

    //LeetCode 216. Combination Sum III
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(k,n,res,new ArrayList<>(),0);
        return res;
    }
    
    public static void dfs(int k, int n, List<List<Integer>> res, List<Integer> list, int cur){
        if(list.size() == k && n == 0) res.add(new ArrayList<>(list));
        for(int i = 1; i <= 9; i++){
           if(n - i < 0 || list.contains(i)) break;
            list.add(i);
            dfs(k, n-i, res, list, i+1);
            list.remove(list.size()-1);
        }
    }

    //LeetCode 22: Generate Parentheses
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(n, new StringBuilder(), res, 0, 0);
        return res;
    }

    // StringBuilder, do backtracking two times
    public static void dfs(int n, StringBuilder str, List<String> res, int front, int back){
        if(str.length() == 2*n) res.add(str.toString());
        if(front < n){
            str.append("(");
            dfs(n, str, res, front+1, back);
            str.deleteCharAt(str.length()-1);
        }
        if(back < front){
            str.append(")");
            dfs(n, str, res, front, back+1);
            str.deleteCharAt(str.length()-1);
        }
    }

    
}
