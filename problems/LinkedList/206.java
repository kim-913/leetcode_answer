// solution1 - recursion
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}

// solution2 - iteration
class Solution {

    public ListNode reverseList(ListNode head) {
        ListNode current = head;
        ListNode tem = null;
        while (current != null) {
            ListNode temp = current.next;
            current.next = tem;
            tem = current;
            current = temp;
        }
        return tem;
    }
}