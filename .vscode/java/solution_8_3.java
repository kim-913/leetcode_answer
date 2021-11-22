/**
 * solution_8_3
 */

public class solution_8_3 {

    public static void main(String[] args) {
        
    }

    // LeetCode 309. Best Time to Buy and Sell Stock with Cooldown
    public static int maxProfit(int[] prices) {
        // record the maximum profit at ith day
        int[][] dp = new int[prices.length][2];
        /*dp[i][1] represents have stock: 
        1. bought today, meaning cool down yesterday
            dp[i-2][0] - prices[i]
        2.bought before, carry forward
            dp[i-1][1]
        dp[i][0] represents have no stock: 
        1. sold today
            prices[i] + dp[i-1][1] 
        2. carry forward, do nothing
            dp[i-1][0]
        */
        if(prices.length <= 1) return 0;
        if(prices.length == 2) {
            if(prices[0] < prices[1]) return prices[1] - prices[0];
            else return 0;
        }
        // day 0, have no stock
        dp[0][0] = 0;
        // day 0, have stock
        dp[0][1] = -prices[0];
        // day 1, have no stock, either don't by anything, or sell today
        dp[1][0] = Math.max(dp[0][0], prices[1] - prices[0]);
        // day 1, have a stock, either bought it on first day, or bought it today
        dp[1][1] = Math.max(dp[0][1], dp[0][0] - prices[1]);
        
        for(int i = 2; i < prices.length; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] - prices[i]);
        }
        return dp[prices.length-1][0];
    }

    // LeetCode 2. Add Two Numbers
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = 0;
        ListNode dummy = new ListNode(-1);
        ListNode res = dummy;
        // notice here for sum != 0, result in the case that we have to create a new node for the left number of sum
        while(l1 != null || l2 != null || sum != 0){
            if(l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            dummy.next = new ListNode(sum % 10);
            sum /= 10;
            dummy = dummy.next;
        }
        return res.next;
    }


    // 142. Linked List Cycle II
    public static ListNode detectCycle(ListNode head) {
        if(head == null) return null;
        if(head != null && head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && slow != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) {
                //need clarification here
                //return slow.next;
                //for(ListNode p = head; p != slow; p = p.next, slow = slow.next) {}
                //return slow;
                break;
            }
        }
        while (slow != null && slow.next != null) {
            if(head == slow) return head;
            slow = slow.next;
            head = head.next;
        }
        return null;
    }
}