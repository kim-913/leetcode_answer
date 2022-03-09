// This is the excersise for amazon binary tree questions


// LC 863. All Nodes Distance K in Binary Tree
class Solution {
    // create a parent map
    Map<Integer, TreeNode> parentMap;
    List<Integer> res;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        parentMap = new HashMap<>();
        res = new ArrayList<>();
        createMap(root);
        dfs(target, null, k, 0);
        return res;
    }

    private void createMap(TreeNode root) {
        if (root == null)
            return;
        if (root.left != null) {
            parentMap.put(root.left.val, root);
            createMap(root.left);
        }
        if (root.right != null) {
            parentMap.put(root.right.val, root);
            createMap(root.right);
        }
    }

    private void dfs(TreeNode root, TreeNode from, int k, int level) {
        if (root == null)
            return;
        if (level == k) {
            res.add(root.val);
            return;
        }
        if (root.left != from) {
            dfs(root.left, root, k, level + 1);
        }
        if (root.right != from) {
            dfs(root.right, root, k, level + 1);
        }
        if (parentMap.get(root.val) != from) {
            dfs(parentMap.get(root.val), root, k, level + 1);
        }
    }
}


// LC 103. Binary Tree Zigzag Level Order Traversal
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // BFS
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                list.add(cur.val);
                if (cur.left != null)
                    q.add(cur.left);
                if (cur.right != null)
                    q.add(cur.right);
            }
            res.add(new ArrayList<>(list));
        }
        for (int i = 1; i < res.size(); i += 2) {
            Collections.reverse(res.get(i));
        }
        return res;
    }
}



// LC 1372. Longest ZigZag Path in a Binary Tree
// important thing about this question is that if it's not zig-zag, we have to clear the distance to 0
class Solution {
    int res = 0;

    public int longestZigZag(TreeNode root) {
        if (root == null)
            return 0;
        dfs(root.left, true, 0);
        dfs(root.right, false, 0);
        return res;
    }

    private void dfs(TreeNode root, boolean direction, int length) {
        res = Math.max(res, length);
        if (root == null)
            return;
        dfs(root.left, true, (!direction) ? length + 1 : 0);
        dfs(root.right, false, (direction) ? length + 1 : 0);
    }
}


// LC 1339. Maximum Product of Splitted Binary Tree
// need to notice here that we need to covert int to long
class Solution {
    List<Integer> res;

    public int maxProduct(TreeNode root) {
        res = new ArrayList<>();
        long ans = 0;
        long totalSum = dfs(root);
        for (long subTreeSum : res) {
            ans = Math.max(ans, subTreeSum * (totalSum - subTreeSum));
        }
        return (int) (ans % (Math.pow(10, 9) + 7));
    }

    // calculate the sum for one subtree
    private int dfs(TreeNode root) {
        if (root == null)
            return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        int total = root.val + left + right;
        res.add(total);
        return total;
    }
}


//
class Solution {
    List<Integer> res;

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        res = new ArrayList<>();
        res.add(root.val);
        leftBound(root.left);
        leaves(root.left);
        leaves(root.right);
        rightBound(root.right);
        return res;
    }

    private void leftBound(TreeNode root) {
        if (root == null)
            return;
        if (root.left != null) {
            res.add(root.val);
            leftBound(root.left);
        } else if (root.right != null) {
            res.add(root.val);
            leftBound(root.right);
        }
    }

    private void leaves(TreeNode root) {
        if (root == null)
            return;
        leaves(root.left);
        if (root.left == null && root.right == null)
            res.add(root.val);
        leaves(root.right);
    }

    private void rightBound(TreeNode root) {
        if (root == null)
            return;
        if (root.right != null) {
            rightBound(root.right);
            res.add(root.val);
        } else if (root.left != null) {
            rightBound(root.left);
            res.add(root.val);
        }
    }
}


// LC 297. Serialize and Deserialize Binary Tree
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    private String rserialize(TreeNode root, String res) {
        if (root == null) {
            res += "Null,";
        } else {
            res += String.valueOf(root.val) + ",";
            res = rserialize(root.left, res);
            res = rserialize(root.right, res);
        }
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        List<String> list = new LinkedList<String>(Arrays.asList(dataArray));
        return rdeserialize(list);
    }

    private TreeNode rdeserialize(List<String> list) {
        if (list.get(0).equals("Null")) {
            list.remove(0);
            return null;
        }
        TreeNode cur = new TreeNode(Integer.valueOf(list.get(0)));
        list.remove(0);
        cur.left = rdeserialize(list);
        cur.right = rdeserialize(list);
        return cur;
    }
}


// LC 236. Lowest Common Ancestor of a Binary Tree
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null)
            return null;
        if (left != null && right != null)
            return root;
        return left == null ? right : left;
    }
}

// LC 987 Vertical Order Traversal of a Binary Tree
class Solution {
    PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
        public int compare(int[] a, int[] b) {
            if (a[0] != b[0])
                return a[0] - b[0];
            else if (a[1] != b[1])
                return a[1] - b[1];
            return a[2] - b[2];
        }
    });

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        int[] curInfo = new int[] { 0, 0, root.val };
        pq.offer(curInfo);
        dfs(root, curInfo);
        while (!pq.isEmpty()) {
            int[] cur = pq.peek();
            List<Integer> temp = new ArrayList<>();
            while (!pq.isEmpty() && pq.peek()[0] == cur[0]) {
                temp.add(pq.poll()[2]);
            }
            res.add(new ArrayList<>(temp));
        }
        return res;
    }

    private void dfs(TreeNode root, int[] curInfo) {
        if (root.left != null) {
            int[] leftInfo = new int[] { curInfo[0] - 1, curInfo[1] + 1, root.left.val };
            pq.offer(leftInfo);
            dfs(root.left, leftInfo);
        }
        if (root.right != null) {
            int[] rightInfo = new int[] { curInfo[0] + 1, curInfo[1] + 1, root.right.val };
            pq.offer(rightInfo);
            dfs(root.right, rightInfo);
        }
    }
}


// LC 124. Binary Tree Maximum Path Sum
class Solution {
    int max_sum = Integer.MIN_VALUE;

    public int max_gain(TreeNode node) {
        if (node == null)
            return 0;
        int left_gain = Math.max(0, max_gain(node.left));
        int right_gain = Math.max(0, max_gain(node.right));
        int path_value = node.val + left_gain + right_gain;
        max_sum = Math.max(path_value, max_sum);
        return node.val + Math.max(left_gain, right_gain);
    }

    public int maxPathSum(TreeNode root) {
        max_gain(root);
        return max_sum;
    }
}


// LC 993. Cousins in Binary Tree
class Solution {
    int dx;
    int dy;
    TreeNode px;
    TreeNode py;

    public boolean isCousins(TreeNode root, int x, int y) {
        findDepth(root, null, x, y, 0);
        return px != py && dx == dy;
    }

    private void findDepth(TreeNode root, TreeNode parent, int x, int y, int depth) {
        if (root == null)
            return;
        if (root.val == x) {
            px = parent;
            dx = depth;
        }
        if (root.val == y) {
            py = parent;
            dy = depth;
        }
        findDepth(root.left, root, x, y, depth + 1);
        findDepth(root.right, root, x, y, depth + 1);
    }
}


// LC 114. Flatten Binary Tree to Linked List
class Solution {
    public void flatten(TreeNode root) {
        dfs(root);
    }

    private TreeNode dfs(TreeNode root) {
        if (root == null)
            return null;
        if (root.left == null && root.right == null)
            return root;
        TreeNode left = dfs(root.left);
        TreeNode right = dfs(root.right);
        if (left != null) {
            left.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        return right == null ? left : right;
    }
}


// LC 662. Maximum Width of Binary Tree
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(root);
        root.val = 1;
        int res = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            res = Math.max(res, q.peekLast().val - q.peekFirst().val + 1);
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (cur.left != null) {
                    cur.left.val = cur.val * 2;
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    cur.right.val = cur.val * 2 + 1;
                    q.offer(cur.right);
                }
            }
        }
        return res;
    }
}

// LC 543. Diameter of Binary Tree
class Solution {
    int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null)
            return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        res = Math.max(res, left + right);
        return Math.max(left, right) + 1;
    }
}


// LC 1740. Find Distance in a Binary Tree
class Solution {
    Map<Integer, TreeNode> map;
    TreeNode pNode;
    int res = 0;

    public int findDistance(TreeNode root, int p, int q) {
        map = new HashMap<>();
        construct(root);
        findP(root, p);
        dfs(pNode, null, q, 0);
        return res;
    }

    private void construct(TreeNode root) {
        if (root == null)
            return;
        if (root.left != null) {
            map.put(root.left.val, root);
            construct(root.left);
        }
        if (root.right != null) {
            map.put(root.right.val, root);
            construct(root.right);
        }
    }

    private void findP(TreeNode root, int p) {
        if (root == null)
            return;
        if (root.val == p) {
            pNode = root;
            return;
        }
        findP(root.left, p);
        findP(root.right, p);
    }

    private void dfs(TreeNode root, TreeNode parent, int q, int len) {
        if (root == null)
            return;
        if (root.val == q) {
            res = len;
            return;
        }
        if (root.left != parent) {
            dfs(root.left, root, q, len + 1);
        }
        if (root.right != parent) {
            dfs(root.right, root, q, len + 1);
        }
        if (map.get(root.val) != parent) {
            dfs(map.get(root.val), root, q, len + 1);
        }
    }
}


//LC  98. Validate Binary Search Tree
class Solution {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode root, long left, long right) {
        if (root == null)
            return true;
        if (root.val <= left || root.val >= right)
            return false;
        return dfs(root.left, left, root.val) && dfs(root.right, root.val, right);
    }
}


// LC 1367. Linked List in Binary Tree
class Solution {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null)
            return true;
        if (root == null)
            return false;
        if (head.val == root.val && dfs(head, root))
            return true;
        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean dfs(ListNode head, TreeNode root) {
        if (head == null)
            return true;
        if (root == null)
            return false;
        if (root.val == head.val) {
            return dfs(head.next, root.left) || dfs(head.next, root.right);
        }
        return false;
    }
}


// LC 105 Construct Binary Tree from Preorder and Inorder Traversal
class Solution {
    int n;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        n = preorder.length;
        return dfs(preorder, inorder, 0, 0, n - 1);
    }

    private TreeNode dfs(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd) {
        if (preStart >= n || inStart > inEnd)
            return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val)
                index = i;
        }
        root.left = dfs(preorder, inorder, preStart + 1, inStart, index - 1);
        root.right = dfs(preorder, inorder, preStart + index - inStart + 1, index + 1, inEnd);
        return root;
    }
}

// LC 889. Construct Binary Tree from Preorder and Postorder Traversal
class Solution {
    int preIndex = 0, posIndex = 0;

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        TreeNode root = new TreeNode(pre[preIndex++]);
        if (root.val != post[posIndex])
            root.left = constructFromPrePost(pre, post);
        if (root.val != post[posIndex])
            root.right = constructFromPrePost(pre, post);
        posIndex++;
        return root;
    }
}


// LC 1110. Delete Nodes And Return Forest
class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null)
            return res;
        Set<Integer> s = new HashSet<>();
        for (int num : to_delete) {
            s.add(num);
        }
        dfs(root, s, true, res);
        return res;
    }

    private TreeNode dfs(TreeNode root, Set<Integer> s, boolean isRoot, List<TreeNode> res) {
        if (root == null)
            return null;
        // record if we want to delete the current node
        boolean isDelete = s.contains(root.val);
        // default isRoot is true and if we don't want to delete the root node, add it
        // inside res
        if (!isDelete && isRoot)
            res.add(root);
        // note that we have to set root.left and root.right because that once it
        // records whether its deleted or not
        root.left = dfs(root.left, s, isDelete, res);
        root.right = dfs(root.right, s, isDelete, res);
        return isDelete ? null : root;
    }
}


// LC 663. Equal Tree Partition
class Solution {
    boolean res = false;

    public boolean checkEqualTree(TreeNode root) {
        int sum = calculate(root);
        if (sum % 2 != 0)
            return false;
        dfs(root.left, sum / 2);
        dfs(root.right, sum / 2);
        return res;
    }

    private int calculate(TreeNode root) {
        if (root == null)
            return 0;
        int left = calculate(root.left);
        int right = calculate(root.right);
        root.val = root.val + left + right;
        return root.val;
    }

    private void dfs(TreeNode root, int target) {
        if (root == null)
            return;
        if (root.val == target) {
            res = true;
            return;
        }
        dfs(root.left, target);
        dfs(root.right, target);
    }
}


// LC 1120. Maximum Average Subtree
class Solution {
    double res = 0;

    public double maximumAverageSubtree(TreeNode root) {
        dfs(root);
        return res;
    }

    private int[] dfs(TreeNode root) {
        if (root == null)
            return new int[] { 0, 0 };
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int sum = root.val + left[0] + right[0];
        int count = 1 + left[1] + right[1];
        res = Math.max(res, (double) sum / (double) count);
        return new int[] { sum, count };
    }
}


// LC 669. Trim a Binary Search Tree
class Solution {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        return dfs(root, low, high);
    }

    private TreeNode dfs(TreeNode root, int low, int high) {
        if (root == null)
            return root;
        if (root.val < low)
            return dfs(root.right, low, high);
        if (root.val > high)
            return dfs(root.left, low, high);
        root.left = dfs(root.left, low, high);
        root.right = dfs(root.right, low, high);
        return root;
    }
}


// LC 663. Equal Tree Partition
class Solution {
    boolean res = false;

    public boolean checkEqualTree(TreeNode root) {
        int sum = calculate(root);
        if (sum % 2 != 0)
            return false;
        dfs(root.left, sum / 2);
        dfs(root.right, sum / 2);
        return res;
    }

    private int calculate(TreeNode root) {
        if (root == null)
            return 0;
        int left = calculate(root.left);
        int right = calculate(root.right);
        root.val = root.val + left + right;
        return root.val;
    }

    private void dfs(TreeNode root, int target) {
        if (root == null)
            return;
        if (root.val == target) {
            res = true;
            return;
        }
        dfs(root.left, target);
        dfs(root.right, target);
    }
}


// LC 99. Recover Binary Search Tree
class Solution {
    List<TreeNode> list;

    public void recoverTree(TreeNode root) {
        list = new LinkedList<>();
        inOrder(root);
        TreeNode t1 = null;
        TreeNode t2 = null;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).val > list.get(i + 1).val) {
                t2 = list.get(i + 1);
                if (t1 == null) {
                    t1 = list.get(i);
                }
            }
        }
        int temp = t1.val;
        t1.val = t2.val;
        t2.val = temp;

    }

    private void inOrder(TreeNode root) {
        if (root == null)
            return;
        inOrder(root.left);
        list.add(root);
        inOrder(root.right);
    }
}