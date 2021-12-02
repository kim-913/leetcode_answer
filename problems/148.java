class Solution {
    public ListNode sortList(ListNode head) {
        // merge sort, cut in half
        // choose the left middle node

        // base case
        if (head == null || head.next == null)
            return head;
        // split list into two halves
        ListNode left = head;
        ListNode right = midNode(head);
        ListNode temp = right.next;
        right.next = null;
        right = temp;
        left = sortList(left);
        right = sortList(right);
        return merge(left, right);
    }

    public ListNode midNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            // by the time fast gets null, slow reaches mid
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }
        if (left != null)
            tail.next = left;
        if (right != null)
            tail.next = right;
        return dummy.next;
    }
}