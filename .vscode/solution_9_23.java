import java.util.*;
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

    // LC 1041
    // need to do simulation for this problem because we need not only to record the count for left and right, but also need
    // to record the current location.
    public boolean isRobotBounded(String instructions) {
        if(instructions == null || instructions.length() <= 1) return false;
        // directions has to be in a circle
        int[][] directions = {{0,1}, {-1,0}, {0, -1}, {1, 0}};
        // we want to determine if we've changed directions during the steps
        int x = 0, y = 0;
        int direction = 0;
        for(char c: instructions.toCharArray()){
            if(c == 'L') direction = (direction + 1) % 4;
            else if(c == 'R') direction = (direction + 3) % 4;
            else{
                x += directions[direction][0];
                y += directions[direction][1];
            }
        }
        return (direction != 0) || (x == 0 && y == 0);
    }



    // LC 
    class MinStack {
        private Stack<Integer> s1;
        private Stack<Integer> min;
        public MinStack() {
            s1 = new Stack<>();
            min = new Stack<>();
        }
        
        public void push(int val) {
            s1.push(val);
            if(min.isEmpty() || val <= min.peek()) min.push(val);
        }
        
        public void pop() {
            if(s1.peek().equals(min.peek())) min.pop();
            s1.pop();
        }
        
        public int top() {
            return s1.peek();
        }
        
        public int getMin() {
            return min.peek();
        }
    }

    // LC 19. Remove Nth Node From End of List
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        int size = 0;
        ListNode cur = head;
        while(cur != null){
            size++;
            cur = cur.next;
        }
        cur = dummy;
        int num = size - n;
        while(cur != null && num > 0){
            cur = cur.next;
            num--;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }


    // LC 323. Number of Connected Components in an Undirected Graph
    boolean[] used;
    public int countComponents(int n, int[][] edges) {
        if(edges == null) return 0;
        used = new boolean[n];
        int res = 0;
        // we can't just use the property of graph: connected graph: V - E  <= 1 to be the condition since there may exist cycle or the degree of vertice can be > 1.
        for(int i = 0; i < n; i++){
            if(!used[i]) {
                res++;
                dfs(edges, i);
            }
        }
        return res;
    }
    
    public void dfs(int[][] edges, int cur){
        // if we've already visited this node(according to the neighbor of the previous nodes, return)
        if(used[cur]) return;
        used[cur] = true;
        for(int[] edge: edges){
            int v = edge[0];
            int e = edge[1];
            if(v == cur || e == cur){
                if(v == cur) dfs(edges, e);
                else dfs(edges, v);
            }
        }
    }
}
