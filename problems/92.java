class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode cur = head;
        while (left > 1) {
            prev = cur;
            cur = cur.next;
            left--;
            right--;
        }
        ListNode temp = prev;
        ListNode tail = cur;
        while (right > 0) {
            ListNode next_Node = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next_Node;
            right--;
        }
        if (temp != null) {
            temp.next = prev;
        } else {
            head = prev;
        }
        tail.next = cur;
        return head;
    }
}