class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode res = new ListNode(-1);
        ListNode cur = head;

        while (cur != null) {
            ListNode pre = res;
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
            }
            ListNode next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = next;
        }
        return res.next;
    }
}