// LC 23. Merge k Sorted Lists
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode list : lists) {
            if (list != null)
                pq.offer(list);
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (!pq.isEmpty()) {
            cur.next = pq.poll();
            cur = cur.next;
            if (cur.next != null) {
                pq.add(cur.next);
            }
        }
        return dummy.next;
    }
}
// Divide and conqure
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        return mergeK(lists, 0, lists.length - 1);
    }

    private ListNode mergeK(ListNode[] lists, int start, int end) {
        if (start == end)
            return lists[start];
        if (end == start + 1) {
            return mergeTwo(lists[start], lists[end]);
        }
        int mid = start + (end - start) / 2;
        ListNode temp1 = mergeK(lists, start, mid);
        ListNode temp2 = mergeK(lists, mid + 1, end);
        return mergeTwo(temp1, temp2);
    }

    // merge 2 list
    private ListNode mergeTwo(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwo(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwo(l1, l2.next);
            return l2;
        }
    }
}