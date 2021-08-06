/**
 * solution_8_4
 */
public class solution_8_4 {

    public static void main(String[] args) {
        
    }

    // LeetCode 19. Remove Nth Node From End of List
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // two passes
        ListNode temp = head;
        int count = 0;
        while(temp != null){
            count++;
            temp = temp.next;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        int num = count - n;
        while(num > 0){
            num--;
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
        

        // LeetCode 148. Sort List
        public ListNode sortList(ListNode head) {
            // merge sort, cut in half
            // choose the left middle node
            
            // base case
            if(head == null || head.next == null) return head;
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
        
        public ListNode midNode(ListNode head){
            ListNode slow = head;
            ListNode fast = head.next;
            while(fast != null && fast.next != null) {
                // by the time fast gets null, slow reaches mid
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
        
        public ListNode merge(ListNode left, ListNode right){
            ListNode dummy = new ListNode(-1);
            ListNode tail = dummy;
            while(left != null && right != null){
                if(left.val < right.val) {
                    tail.next = left;
                    left = left.next;
                } else {
                    tail.next = right;
                    right = right.next;
                }
                tail = tail.next;
            }
            if(left != null) tail.next = left;
            if(right != null) tail.next = right;
            return dummy.next;
        }


        // LeetCode 143. Reorder List
        public void reorderList(ListNode head) {
            // get to the middle of the list
            // seperate into two
            if(head == null || head.next == null) return;
            ListNode left = head;
            ListNode slow = head;
            ListNode fast = head;
            ListNode prev = null;
            while(fast != null && fast.next != null){
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            prev.next = null;
            ListNode right = reverse(slow);
            merge1(left, right);
        }
        
        public ListNode reverse(ListNode head){
            ListNode prev = null;
            ListNode cur = head;
            while(cur != null){
                ListNode next_node = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next_node;
            }
            return prev;
        }
        
        public void merge1(ListNode left, ListNode right){
            while(left != null){
                ListNode l1 = left.next;
                ListNode l2 = right.next;
                left.next = right;
                if(l1 == null) break;
                right.next = l1;
                left = l1;
                right = l2;
            }
        }
}