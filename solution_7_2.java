import java.util.*;

public class solution_7_2 {
    public static void main(String[] args) {
        
    }
    // LeetCode: 136. Single Number
    public int singleNumber(int[] nums) {
        /*
        Map<Integer,Integer> map = new HashMap<>();
        int res = Integer.MAX_VALUE;
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        for(int num: map.keySet()){
            if(map.get(num) == 1) return num;
        }
        return 0;
        */
        int sumTwice = 0, sumAll = 0;
        Set<Integer> s = new HashSet<>();
        for(int num: nums){
            if(!s.contains(num)){
                s.add(num);
                sumTwice+=num;
            }
            sumAll+=num;
        }
        return 2*sumTwice-sumAll;
    }

    // LeetCode 70. Climbing Stairs
    public int climbStairs(int n) {
        /*
        if(n==1){
            return 1;
        }else if(n==2){
            return 2;
        } else {
            return (climbStairs(n-=1) + climbStairs(n-=2));
        }
        */
        int dp[] = new int[n+1];
        if(n<=0) return 0;
        if(n==1) return 1;
        dp[0]=0;
        dp[1]=1;
        dp[2]=2;
        for(int i = 3; i <= n; i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    // LeetCode 121. Best Time to Buy and Sell Stock
    public int maxProfit(int[] prices) {
        /*
        if(prices.length == 1) return 0;
        int diff = 0;
        for(int i = 0; i< prices.length; i++){
            for(int j = i; j < prices.length; j++){
                diff = Math.max(prices[j]-prices[i], diff);
            }
        }
        return diff;
        */
        int min=prices[0];
        int profit = 0;
        for(int i = 1;i < prices.length; i++){
            min = Math.min(min, prices[i]);
            profit = Math.max(profit,prices[i]-min);
        }
        return profit;
    }
}
