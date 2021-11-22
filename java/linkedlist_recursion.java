package java;

public class linkedlist_recursion {

    public static void main(String[] args) {

    }

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

    // LC 206. Reverse Linked List
    /*
    Input: head = [1,2,3,4,5]
    Output: [5,4,3,2,1]*/

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode temp = head.next;
        ListNode p = reverseList(head.next);
        temp.next = head;
        head.next = null;
        return p;
    }
    
    // LC 24. Swap Nodes in Pairs
    /*
     * Input: head = [1,2,3,4] Output: [2,1,4,3]
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        /*
         * ListNode first = head; ListNode second = head.next; first.next =
         * swapPairs(second.next); second.next = first; return second;
         */
        ListNode temp = swapPairs(head.next.next);
        ListNode newHead = head.next;
        newHead.next = head;
        head.next = temp;
        return newHead;
    }
}
