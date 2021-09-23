public class solution_9_23 {
    
    public static void main(String[] args) {
        
    }


    // LC 430. Flatten a Multilevel Doubly Linked List
    public Node flatten(Node head) {
        if(head == null) return head;
        Node cur = head; 
        // find the first child
        while(cur != null) {
            if(cur.child == null) {
                cur = cur.next;
                continue;
            }
            // then use something to declare the child
            Node temp = cur.child;
            // get the last node
            while(temp.next != null) 
                temp = temp.next;
            // Connect lastnode with the previous layer
            temp.next = cur.next;  
            if(cur.next != null)  cur.next.prev = temp;
            // Connect cur with child of cur
            cur.next = cur.child; 
            cur.child.prev = cur;
            cur.child = null;
        }
        return head;
    }
}
