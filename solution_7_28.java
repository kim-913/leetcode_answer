import java.util.*;
public class solution_7_28 {
    
    public static void main(String[] args) {
        
    }

    // LeetCode 139. Word Break
    public boolean wordBreak(String s, List<String> wordDict) {
        // this is because the substring method exclude the current index
        boolean[] dp = new boolean[s.length()+1];
        // we can always find empty string inside the wordDict
        dp[0] = true;
        
        // follow up, we can check the maxLength of the string inside the worDict and if the difference between i and j is already greater than or equal to the maxLength, we can simply continue.
        int max = 0;
        for(String str: wordDict) max = Math.max(str.length(), max);
        for(int i = 0; i <= s.length(); i++){
            for(int j = 0; j < i; j++){
            //for(int j = i-1; j >= 0; j--){
                if(i-j > max) continue;
                if(dp[j] && wordDict.contains(s.substring(j,i))) dp[i] = true;
            }
        }
        return dp[s.length()];
    }



    // LeetCode 91. Decode Ways
    public static int numDecodings(String s) {
        //         Map<Integer,Integer> map = new HashMap<>();
        //     }
            
        //     public int dfs(String s, Map<Integer,Integer> map, int cur){
        //         // still, map keeps track of if we had dealed with the subproblem before, avoid doing same work
        //         if(map.containsKey(index)) return map.get(index);
        //         // base case
        //         if(index == s.length()) return 1;
        //         if(s.charAt(cur) == '0') return 0;
        //         if()
                
        //     }
        int[] dp = new int[s.length()+1];// put +1 here becuase we want to account for the string with 0 length.
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1; // if the first index is 0 or not
        for(int i = 2; i <= s.length(); i++){
            // only have to get two situations, whether the current situation is one digit or two
            int one = Integer.valueOf(s.substring(i-1,i));
            int two = Integer.valueOf(s.substring(i-2,i));
            if(one >= 1) dp[i] += dp[i-1];
            if(two >= 10 && two <= 26) dp[i] += dp[i-2];
        }
        return dp[s.length()];        
    }


    //LeetCode 377. Combination Sum IV
    public static int combinationSum4(int[] nums, int target) {
        // Use memorization map to get if we've dealed with the current situation before or not
        Map<Integer, Integer> map = new HashMap<>();
        return dfs(nums, target, map);
    }
    
    public static int dfs(int[] nums, int remain, Map<Integer, Integer> map){
        // if we've done this situation
        if(map.containsKey(remain)) return map.get(remain);
        //base case, if we find the target
        if(remain == 0) return 1;
        int res = 0;
        for(int num: nums){
            // if we can use current num
            if(remain - num >= 0) res += dfs(nums, remain-num, map);
        }
        // !!!!! don't forget to put it into the map
        map.put(remain, res);
        
        return res;
    }


    // LeetCode 139. Word Break

}
