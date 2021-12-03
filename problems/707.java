public class ListNode {
    int val;
    ListNode next;
    ListNode prev;

    ListNode(int x) {
        val = x;
    }
}

class MyLinkedList {
    private int size;
    private ListNode head;
    private ListNode tail;

    public MyLinkedList() {
        size = 0;
        // head and tail are dummy nodes
        head = new ListNode(-1);
        tail = new ListNode(-1);
        head.next = tail;
        tail.prev = head;
    }

    // get the val of the node if index is valid
    public int get(int index) {
        if (index < 0 || index >= size)
            return -1;
        // we can either go from head or tail, depending on which one is faster
        // means closer to head
        ListNode cur = head;
        if (index + 1 < size - index) {
            for (int i = 0; i < index + 1; i++) {
                cur = cur.next;
            }
        } else {
            cur = tail;
            for (int i = 0; i < size - index; i++) {
                cur = cur.prev;
            }
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        size++;
        ListNode prev = head;
        ListNode next = head.next;
        ListNode cur = new ListNode(val);
        cur.prev = prev;
        cur.next = next;
        prev.next = cur;
        next.prev = cur;
    }

    public void addAtTail(int val) {
        size++;
        ListNode prev = tail.prev;
        ListNode next = tail;
        ListNode cur = new ListNode(val);
        cur.prev = prev;
        cur.next = next;
        prev.next = cur;
        next.prev = cur;
    }

    public void addAtIndex(int index, int val) {
        // check if index is valid
        if (index > size)
            return;
        if (index < 0)
            index = 0;
        ListNode prev = new ListNode(-1);
        ListNode next = new ListNode(-1);
        // iterate from head
        if (index < size - index) {
            prev = head;
            for (int i = 0; i < index; ++i) {
                prev = prev.next;
            }
            next = prev.next;
            // from tail
        } else {
            next = tail;
            for (int i = 0; i < size - index; i++) {
                next = next.prev;
            }
            prev = next.prev;
        }
        size++;
        ListNode cur = new ListNode(val);
        cur.prev = prev;
        cur.next = next;
        prev.next = cur;
        next.prev = cur;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size)
            return;
        ListNode prev = new ListNode(-1);
        ListNode next = new ListNode(-1);
        // iterate from head
        if (index < size - index) {
            prev = head;
            for (int i = 0; i < index; ++i) {
                prev = prev.next;
            }
            next = prev.next.next;
            // from tail
        } else {
            next = tail;
            for (int i = 0; i < size - index - 1; i++) {
                next = next.prev;
            }
            prev = next.prev.prev;
        }
        size--;
        prev.next = next;
        next.prev = prev;
    }
}