import java.util.*;
public class solution_7_26 {
    

    public static void main(String[] args) {
        
    }

    // LeetCode: 494. Target Sum
    private static int res = 0;
    public static int findTargetSumWays(int[] nums, int target) {
        dfs(nums, target, 0, 0);
        return res;
    }
    
    public static void dfs(int[] nums, int target, int cur, int sum){
        if(cur == nums.length) {
            if(sum == target) res++;
        } else {
            dfs(nums, target, cur+1, sum + nums[cur]);
            dfs(nums, target, cur+1, sum - nums[cur]);
        }
    }

    // LeetCode 131. Palindrome Partitioning
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(s,res, new ArrayList<>(), 0);
        return res;
    }
    
    public void dfs(String s, List<List<String>> res, List<String> list, int cur){
        if(cur >= s.length()) res.add(new ArrayList<>(list));
        for(int i = cur; i < s.length(); i++){
            if(isPalindrome(s, cur, i)){
                list.add(s.substring(cur, i+1));
                dfs(s,res,list,i+1);
                list.remove(list.size()-1);
            }
        }
    }
    
    public boolean isPalindrome(String s, int front, int back){
        while(front < back){
            if(s.charAt(front) != s.charAt(back)) return false;
            front++;
            back--;
        }
        return true;
    }

    // LeetCode 17. Letter Combinations of a Phone Number
    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits.length() == 0) return res;
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        dfs(digits, res, "", 0, map);
        return res;
    }
    
    public static void dfs(String digits, List<String> res, String s, int cur, Map<Character, String> map){
        if(cur == digits.length()) {
            res.add(s);
            return;
        }
        String current = map.get(digits.charAt(cur));
        for(int i = 0; i < current.length(); i++){
            String sub = current.substring(i,i+1);
            dfs(digits, res, s.concat(sub), cur+1, map);
        }
    }
}
