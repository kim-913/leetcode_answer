class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // use priorityQueue
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> (a.val - b.val));
        for (ListNode node : lists) {
            if (node != null)
                pq.add(node);
        }
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