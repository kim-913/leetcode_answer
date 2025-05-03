import java.util.PriorityQueue;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>((a, b) -> a.val - b.val);
        ListNode ans = new ListNode(0);
        ListNode tail = ans;
        for (ListNode list : lists) {
            if (list != null)
                pq.offer(list);
        }
        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            tail.next = cur;
            if (cur.next != null)
                pq.offer(cur.next);
            tail = tail.next;
        }
        return ans.next;
    }
}