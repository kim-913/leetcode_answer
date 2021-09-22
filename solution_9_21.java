import java.util.ArrayList;
import java.util.List;

/**
 * solution_9_21
 */
public class solution_9_21 {

    public static void main(String[] args) {
        
    }

    // LeetCode 725
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] res = new ListNode[k];
        if(head == null) return res;
        ListNode cur = head;
        int size = 0;
        while(cur != null){
            size++;
            cur = cur.next;
        }
        /*
        ListNode temp = head;
        if(size <= k) {
            for(int i = 0; i < size; i++){
                if(temp == null) continue;
                ListNode next = temp.next;
                temp.next = null;
                res[i] = temp;
                temp = next;
            }
        } else {
            int num = size / k;
            int residual = size % k;
            int more = k / size;
            ListNode n = head;
            ListNode after = null;
            int j = 0;
            while(residual > 0){
                ListNode current = n;
                for(int i = 0; i < num; i++){
                    if(n != null) n = n.next;
                }
                ListNode prev = n;
                n = n.next;
                prev.next = null;
                res[j] = current;
                j++;
                residual--;
                k--;
            }
            while(k > 0){
                for(int i = 0; i < num; i++){
                    after = after.next;
                }
                ListNode next_after = after.next;
                after.next = null;
                res[j] = after;
                j++;
                k--;
            }
        }
        */
        int num = size / k;
        int residual = size % k;
            cur = head;
            for(int i = 0; i < k; i++){
                ListNode new_head = cur;
                for(int j = 0; j < num + (i < residual ? 1 : 0) - 1; j++){
                    if(cur != null) cur = cur.next;
                }
                if(cur != null) {
                    ListNode prev = cur;
                    cur = cur.next;
                    prev.next = null;
                }
                res[i] = new_head;
            }
        return res;
    }



    // LeetCode 931. Minimum Falling Path Sum
    public int minFallingPathSum(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int row = matrix.length;
        int col = matrix[0].length;
        // instead of creating a new double int array, we can just fix the value of the original matrix
        for(int i = row - 2; i >= 0; i--){
            for(int j = 0; j < col; j++){
               matrix[i][j] += Math.min(matrix[i + 1][j], Math.min(matrix[i + 1][Math.max(0, j - 1)], matrix[i + 1][Math.min(col - 1, j + 1)]));   
            }
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < col; i++){
            res = Math.min(res, matrix[0][i]);
        }
        return res;
    }

    // LC 120. Triangle
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int max_col = triangle.get(row - 1).size();
        int cur_col = max_col - 1;
        for(int i = row - 2; i >= 0; i--){
            for(int j = 0; j < cur_col && cur_col < max_col; j++){
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(Math.min(max_col - 1, j + 1))));
            }
            cur_col--;
        }
        return triangle.get(0).get(0);
    }

    // LC 46. Permutations
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        dfs(nums, res, new ArrayList<>(), 0);
        return res;
    }
    
    public void dfs(int[] nums, List<List<Integer>> res, List<Integer> cur, int index){
        if(cur.size() == nums.length) res.add(new ArrayList<>(cur));
        for(int i = 0; i < nums.length; i++){
            if(cur.contains(nums[i])) continue;
            cur.add(nums[i]);
            dfs(nums, res, cur, index + 1);
            cur.remove(cur.size() - 1);
        }
    }


    // LC 47. Permutations II
    boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        used = new boolean[nums.length];
        Arrays.sort(nums);
        if(nums == null || nums.length == 0) return res;
        dfs(nums, res, new ArrayList<>(), 0);
        return res;
    }
    
    public void dfs(int[] nums, List<List<Integer>> res, List<Integer> cur, int index){
        if(cur.size() == nums.length) res.add(new ArrayList<>(cur));
        for(int i = 0; i < nums.length; i++){
            if(used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) continue;
            used[i] = true;
            cur.add(nums[i]);
            dfs(nums, res, cur, index + 1);
            used[i] = false;
            cur.remove(cur.size() - 1);
        }
    }
}