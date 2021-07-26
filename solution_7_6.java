import java.util.*;
public class solution_7_6 {
    
    public static void main(String[] args) {
        
    }

    // LeetCode 141. Linked List Cycle
    public boolean hasCycle(ListNode head) {
        if(head==null) return false;
        Set<ListNode> s = new HashSet<>();
        while(head != null){
            if(s.contains(head)) return true;
            s.add(head);
            head=head.next;
        }
        return false;
        /*
        //fast and slow pointer
        ListNode fast = head.next;
        ListNode slow = head;
        while(fast != null && slow != null && fast.next != null){
            if(fast == slow) return true;
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
        */
    }

    // LeetCode 234. Palindrome Linked List
    public boolean isPalindrome(ListNode head) {
        if(head==null) return true;
        /*
        List<Integer> res = new ArrayList<>();
        while(head!=null){
            res.add(head.val);
            head=head.next;
        }
        int left = 0, right = res.size()-1;
        while(left <= right){
            if(!res.get(left).equals(res.get(right))) return false;
            left++;
            right--;
        }
        return true;
        */
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        if(fast != null){
            slow = slow.next;
        }
        slow = reverse(slow);
        
        while(slow != null){
            if(head.val != slow.val){
                return false;
            }
            head = head.next;
            slow = slow.next;
        }
        return true;
    }
    public ListNode reverse(ListNode temp){
        if(temp == null) return temp;
        ListNode prev = temp;
        ListNode curr = null;
        while(prev != null){
            ListNode current = prev.next;
            prev.next=curr;
            curr = prev;
            prev=current;
            
        }
        return curr;
    }

    // LeetCode 203. Remove Linked List Elements
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return head;
        /*
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode prev = dummy, cur = head;
        while(cur!=null){
            if(cur.val == val) prev.next = cur.next;
            else prev = cur;
            cur = cur.next;
        }
        return dummy.next;
        */
        head.next = removeElements(head.next,val);
        return (head.val==val) ? head.next:head;
    }
}
