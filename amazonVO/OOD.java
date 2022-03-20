// LC 146 LRU Cache
class LRUCache {
    // both head and tail are dummy nodes
    Node head = new Node();
    Node tail = new Node();
    Map<Integer, Node> map;
    int cahce_capacity;

    class Node {
        int key;
        int val;
        Node prev;
        Node next;
    }

    public void addNode(Node cur) {
        // add to the front
        Node head_next = head.next;
        cur.next = head_next;
        head_next.prev = cur;
        head.next = cur;
        cur.prev = head;
    }

    public void removeNode(Node cur) {
        Node cur_next = cur.next;
        Node cur_prev = cur.prev;
        cur_prev.next = cur_next;
        cur_next.prev = cur_prev;
    }

    public LRUCache(int capacity) {
        this.cahce_capacity = capacity;
        map = new HashMap<>();
        // initialize head and tail
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        int res = -1;
        // get from map
        Node cur = map.get(key);
        if (cur != null) {
            res = cur.val;
            // runs in O(1), we have to move the node to the front
            removeNode(cur);
            addNode(cur);
        }
        return res;
    }

    public void put(int key, int value) {
        Node cur = map.get(key);
        if (cur != null) {
            // have to change the val of node cur
            // remove it and add to front
            cur.val = value;
            removeNode(cur);
            addNode(cur);
        } else {
            // we have to check if the nodemap has already reached its capacity
            if (map.size() == cahce_capacity) {
                // remove the last node and add the current node
                // tail is like a dummy node, remove the prev node of rail
                map.remove(tail.prev.key);
                removeNode(tail.prev);
            }

            Node new_Node = new Node();
            new_Node.key = key;
            new_Node.val = value;
            map.put(key, new_Node);
            addNode(new_Node);
        }
    }
}


// LC 588. Design In-Memory File System
public class FileSystem {
    private FileNode root;

    public FileSystem() {
        root = new FileNode("");
    }

    public List<String> ls(String path) {
        return findNode(path).getList();
    }

    public void mkdir(String path) {
        findNode(path);
    }

    public void addContentToFile(String filePath, String content) {
        findNode(filePath).addContent(content);
    }

    public String readContentFromFile(String filePath) {
        return findNode(filePath).getContent();
    }

    // -- private method section --//
    private FileNode findNode(String path) {
        String[] files = path.split("/");

        FileNode cur = root;
        for (String file : files) {
            if (file.length() == 0)
                continue;
            cur.children.putIfAbsent(file, new FileNode(file));
            System.out.println(cur.children);
            cur = cur.children.get(file);
            if (cur.isFile())
                break;
        }

        return cur;
    }

    // Private class
    private class FileNode {
        private TreeMap<String, FileNode> children;
        private StringBuilder file;
        private String name;

        public FileNode(String name) {
            children = new TreeMap<>();
            file = new StringBuilder();
            this.name = name;
        }

        public String getContent() {
            return file.toString();
        }

        public String getName() {
            return name;
        }

        public void addContent(String content) {
            file.append(content);
        }

        public boolean isFile() {
            return file.length() > 0;
        }

        public List<String> getList() {
            List<String> list = new ArrayList<>();
            if (isFile()) {
                list.add(getName());
            } else {
                list.addAll(children.keySet());
            }

            return list;
        }
    }
}


// LC 642. Design Search Autocomplete System
public class AutocompleteSystem {
    class TrieNode {
        Map<Character, TrieNode> children;
        Map<String, Integer> counts;
        boolean isWord;

        public TrieNode() {
            children = new HashMap<Character, TrieNode>();
            counts = new HashMap<String, Integer>();
            isWord = false;
        }
    }

    class Pair {
        String s;
        int c;

        public Pair(String s, int c) {
            this.s = s;
            this.c = c;
        }
    }

    TrieNode root;
    String prefix;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        prefix = "";

        for (int i = 0; i < sentences.length; i++) {
            add(sentences[i], times[i]);
        }
    }

    private void add(String s, int count) {
        TrieNode curr = root;
        for (char c : s.toCharArray()) {
            TrieNode next = curr.children.get(c);
            if (next == null) {
                next = new TrieNode();
                curr.children.put(c, next);
            }
            curr = next;
            curr.counts.put(s, curr.counts.getOrDefault(s, 0) + count);
        }
        curr.isWord = true;
    }

    public List<String> input(char c) {
        if (c == '#') {
            add(prefix, 1);
            prefix = "";
            return new ArrayList<String>();
        }

        prefix = prefix + c;
        TrieNode curr = root;
        for (char cc : prefix.toCharArray()) {
            TrieNode next = curr.children.get(cc);
            if (next == null) {
                return new ArrayList<String>();
            }
            curr = next;
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> (a.c == b.c ? a.s.compareTo(b.s) : b.c - a.c));
        for (String s : curr.counts.keySet()) {
            pq.add(new Pair(s, curr.counts.get(s)));
        }

        List<String> res = new ArrayList<String>();
        for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
            res.add(pq.poll().s);
        }
        return res;
    }
}


// Lc 208. Implement Trie (Prefix Tree)
class Trie {
    class TrieNode {
        boolean isEnd;
        TrieNode[] children;

        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode p = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (p.children[index] == null)
                p.children[index] = new TrieNode();
            p = p.children[index];
        }
        p.isEnd = true;
    }

    public TrieNode find(String word) {
        TrieNode p = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (p.children[index] == null)
                return null;
            p = p.children[index];
        }
        return p;
    }

    public boolean search(String word) {
        TrieNode p = find(word);
        return p != null && p.isEnd;
    }

    public boolean startsWith(String prefix) {
        return find(prefix) != null;
    }
}



// LC 1628. Design an Expression Tree With Evaluate Function
abstract class Node {
    public abstract int evaluate();
    // define your fields here
};

class TreeNode extends Node {
    // Constructor
    String val;
    TreeNode left;
    TreeNode right;

    TreeNode(String val) {
        this.val = val;
    }

    // Abstract method cannot have body; so fill in body here.
    public int evaluate() {
        return dfs(this); // use this keyword for current TreeNode object.
    }

    private int dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            return Integer.valueOf(root.val);
        }
        String operator = root.val;
        int left = dfs(root.left);
        int right = dfs(root.right);
        int res = 0;
        if (operator.equals("+")) {
            res = left + right;
        } else if (operator.equals("-")) {
            res = left - right;
        } else if (operator.equals("*")) {
            res = left * right;
        } else {
            res = left / right;
        }
        return res;
    }

};


class TreeBuilder {
    String operator = "+-*/";
    Stack<TreeNode> stack = new Stack<>();

    Node buildTree(String[] postfix) {
        for (String str : postfix) {
            if (operator.contains(str)) {
                TreeNode root = new TreeNode(str);
                root.right = stack.pop();
                root.left = stack.pop();
                stack.push(root);
            } else {
                stack.push(new TreeNode(str));
            }
        }
        return stack.pop();
    }
};


 // LC 362. Design Hit Counter
 class HitCounter {
     private Queue<Integer> hits;

     public HitCounter() {
         this.hits = new LinkedList<Integer>();
     }

     public void hit(int timestamp) {
         hits.add(timestamp);
     }

     public int getHits(int timestamp) {
         while (!hits.isEmpty()) {
             int diff = timestamp - hits.peek();
             if (diff >= 300)
                 hits.poll();
             else
                 break;
         }
         return hits.size();
     }
 }




 // LC 716. Max Stack
 class MaxStack {
     Deque<Integer> stack;
     PriorityQueue<Integer> pq;

     /** initialize your data structure here. */
     public MaxStack() {
         stack = new ArrayDeque<>();
         pq = new PriorityQueue<>((a, b) -> (b - a));
     }

     public void push(int x) {
         stack.addLast(x);
         pq.offer(x);
     }

     public int pop() {
         int delete = stack.removeLast();
         pq.remove(delete);
         return delete;
     }

     public int top() {
         return stack.getLast();
     }

     public int peekMax() {
         return pq.peek();
     }

     public int popMax() {
         int delete = pq.poll();
         stack.removeLastOccurrence(delete);
         return delete;
     }
 }


 // LC 1472. Design Browser History
 class BrowserHistory {
     Deque<String> stack;
     Deque<String> mem;

     public BrowserHistory(String homepage) {
         stack = new LinkedList<>();
         mem = new LinkedList<>();
         stack.add(homepage);
     }

     public void visit(String url) {
         stack.push(url);
         mem = new LinkedList<>();
     }

     public String back(int steps) {
         while (steps-- > 0 && stack.size() > 1) {
             mem.push(stack.pop());
         }
         return stack.peek();
     }

     public String forward(int steps) {
         while (steps-- > 0 && mem.size() > 0) {
             stack.push(mem.pop());
         }
         return stack.peek();
     }
 }


 // LC Find median
 class MedianFinder {
     PriorityQueue<Integer> s;
     PriorityQueue<Integer> l;
     boolean isEven;

     public MedianFinder() {
         s = new PriorityQueue<>((a, b) -> b - a);
         l = new PriorityQueue<>();
         isEven = true;
     }

     public void addNum(int num) {
         if (isEven) {
             l.offer(num);
             s.offer(l.poll());
         } else {
             s.offer(num);
             l.offer(s.poll());
         }
         isEven = !isEven;
     }

     public double findMedian() {
         if (isEven) {
             return (s.peek() + l.peek()) / 2.0;
         } else {
             return s.peek();
         }
     }
 }


 // LC 348. Design Tic-Tac-Toe
 class TicTacToe {
     int[][] matrix;
     int n;

     public TicTacToe(int n) {
         matrix = new int[n][n];
         this.n = n;
     }

     public int move(int row, int col, int player) {
         matrix[row][col] = player;
         if (checkRow(row, player) || checkCol(col, player))
             return player;
         if (row == col) {
             if (checkDiag(player))
                 return player;
         }
         if (row + col == n - 1) {
             if (checkDiag2(player))
                 return player;
         }
         return 0;
     }

     public boolean checkRow(int row, int player) {
         for (int i = 0; i < n; i++) {
             if (matrix[row][i] != player)
                 return false;
         }
         return true;
     }

     public boolean checkCol(int col, int player) {
         for (int i = 0; i < n; i++) {
             if (matrix[i][col] != player)
                 return false;
         }
         return true;
     }

     public boolean checkDiag(int player) {
         for (int i = 0; i < n; i++) {
             if (matrix[i][i] != player)
                 return false;
         }
         return true;
     }

     public boolean checkDiag2(int player) {
         for (int i = 0; i < n; i++) {
             if (matrix[i][n - 1 - i] != player)
                 return false;
         }
         return true;
     }
 }




 // Linux find file > 5mb
 class File {
     String name;
     int size;
     int type;
     boolean isDirectory;
     File[] children;
 }

 abstract class Filter {
     abstract boolean apply(File file);
 }

 class MinSizeFilter extends Filter {

     int minSize;

     public MinSizeFilter(int minSize) {
         this.minSize = minSize;
     }

     @Override
     boolean apply(File file) {
         return file.size > minSize;
     }
 }

 class TypeFilter extends Filter {

     int type;

     public TypeFilter(int type) {
         this.type = type;
     }

     @Override
     boolean apply(File file) {
         return file.type == type;
     }
 }

 class FindCommand {

     public List<File> findWithFilters(File directory, List<Filter> filters) {
         if (!directory.isDirectory) {
             return new NotADirectoryException();
         }
         List<File> output = new ArrayList<>();
         findWithFilters(directory, filters, output);
         return output;
     }

     private void findWithFilters(File directory, List<Filter> filters, List<File> output) {
         if (directory.children == null) {
             return;
         }
         for (File file : directory.children) {
             if (file.isDirectory) {
                 findWithFilters(file, filters, output);
             } else {
                 boolean selectFile = true;
                 for (Filter filter : filters) {
                     if (!filter.apply(file)) {
                         selectFile = false;
                     }
                 }
                 if (selectFile) {
                     output.add(file);
                 }
             }
         }
     }
 }



 // Student registration class system
 class Registration {
        class Course {
            List<Student> curStudent;
            int capacity;
            String course;
            public Course(){
                
            }
            public boolean canAdd(){
                return s.curStudent.size() < capacity;
            }
        }
        
        class Student {
            List<Course> courses;
            public Student(Integer id){
                
            }
        }
        Set<Student> studentList;
        public Registration(Set<Student> courses){
            this.studentList = studentList;
        }
        
        public void addStudent(Student s){
            studentList.add(s);
        }
        
        public void delete(Course course, Student s){
            if(!studentList.contains(s)) return;
            studentList.get(s).courses.remove();
        }
        
        public void update(Course course, Student s){
            
        }
        
        public void add(Course course, Student s){
            if(course.canAdd()) s.courses.add(course);
        }
    }
}