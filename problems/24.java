// solution1 - recursion
class Solution {
    public ListNode swapPairs(ListNode head) {
        // first find the next pair(up util the last)
        ListNode temp = swapPairs(head.next.next);
        ListNode newHead = head.next;
        newHead.next = head;
        head.next = temp;
        return newHead;
    }
}

// solution2 - iteration
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        while (prev.next != null && prev.next.next != null) {
            ListNode temp1 = prev.next;
            ListNode temp2 = prev.next.next;
            temp1.next = temp2.next;
            prev.next = temp2;
            prev.next.next = temp1;
            prev = prev.next.next;
        }
        return dummy.next;
    }
}