package problems;

class Solution {
    // Definition for singly-linked list.
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = 0;
        ListNode dummy = new ListNode(-1);
        ListNode res = dummy;
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
}