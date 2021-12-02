class Solution {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode res = dummy;
        if (l1 == null && l2 == null)
            return null;
        if (l1 == null || l2 == null)
            return l1 == null ? l2 : l1;
        // difference between this problem and Q2 is the order
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        Stack<Integer> s3 = new Stack<>();
        while (l1 != null) {
            s1.add(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.add(l2.val);
            l2 = l2.next;
        }
        int curSum = 0;
        while (!s1.isEmpty() || !s2.isEmpty() || curSum != 0) {
            if (!s1.isEmpty()) {
                curSum += s1.pop();
            }
            if (!s2.isEmpty()) {
                curSum += s2.pop();
            }
            s3.add(curSum % 10);
            curSum /= 10;
        }
        while (!s3.isEmpty()) {
            dummy.next = new ListNode(s3.pop());
            dummy = dummy.next;
        }
        return res.next;
    }
}