import java.util.*;

public class solution_9_24 {
    
    public static void main(String[] args) {
        
    }

    class LRUCache {
        Node head = new Node();
        Node tail = new Node();
        Map<Integer, Node> node_map;
        int cahce_capacity;
        
        // firstly, to add and remove the node in constant time, we need to create a data strucuture that put the cur node in the front for us to get 
        // want to create a double linkedlist here because it has connection with both next node and prev node. Here, we want to set an extra field of key value wich correspond to the key value inside the map
        
        class Node {
            int key;
            int val;
            Node prev;
            Node next;
        }
        
        public LRUCache(int capacity) {
            this.cahce_capacity = capacity;
            node_map = new HashMap<>(capacity);
            head.next = tail;
            tail.prev = head;
        }
        
        // we want to create an add method that add the node to the front of the double linkedlist
        public void addNode(Node cur){
            Node head_next = head.next;
            cur.next = head_next;
            head_next.prev = cur;
            head.next = cur;
            cur.prev = head;
        }
        
        // also, we want to create a remove method that removes the current node from the double linkedlist
        public void removeNode(Node cur){
            Node cur_next = cur.next;
            Node cur_prev = cur.prev;
            cur_prev.next = cur_next;
            cur_next.prev = cur_prev;
        }
        
        // need to know that the get method need us to put the node in the front of the double linkedlist to be got in constant time
        public int get(int key) {
            // get the node from the nodemap
            int res = -1;
            Node cur = node_map.get(key);
            if(cur != null){
                res = cur.val;
                removeNode(cur);
                addNode(cur);
            }
            return res;
        }
        
        // put method 
        public void put(int key, int value) {
            // firstly, we have to check if nodemap already contains the node
            Node cur = node_map.get(key);
            // if exists, update and remove and add the node to the front of the double linkedlist
            if(cur != null){
                cur.val = value;
                removeNode(cur);
                addNode(cur);
            } else{
                // we have to check if the nodemap has already reached its capacity
                if(node_map.size() == cahce_capacity){
                    // remove the last node and add the current node
                    // tail is like a dummy node, remove the prev node of rail
                    node_map.remove(tail.prev.key);
                    removeNode(tail.prev);
                }
                // here, we've reached the condition that the nodemap is still capable of adding the current node
                // add the node to both double linkedlist and the node_map
                Node new_node = new Node();
                new_node.key = key;
                new_node.val = value;
                node_map.put(key, new_node);
                addNode(new_node);
            }
        }
    }
}
