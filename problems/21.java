// solution1 - recursion
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}

// solution2 - iteration
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null)
            return null;
        if (list1 == null || list2 == null)
            return list1 == null ? list2 : list1;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> (a.val - b.val));
        pq.offer(list1);
        pq.offer(list2);
        ListNode dummy = new ListNode(-1);
        ListNode res = dummy;
        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            dummy.next = cur;
            dummy = dummy.next;
            if (cur.next != null)
                pq.offer(cur.next);
        }
        return res.next;
    }
}

// solution3 - bf - easy to understand
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        ListNode tail = ans;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        if (l1 != null)
            tail.next = l1;
        if (l2 != null)
            tail.next = l2;
        return ans.next;
    }
}