package doordash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class EligibleOrderSequence {
    public List<Integer> solve(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < n; i++) {
            while (stack.peek() != -1 && nums[stack.peek()] > nums[i]) {
                right[stack.pop()] = i;
            }
            stack.push(i);
        }
        stack.clear();
        stack.push(-1);
        for (int i = n - 1; i >= 0; i--) {
            while (stack.peek() != -1 && nums[stack.peek()] > nums[i]) {
                left[stack.pop()] = i;
            }
            stack.push(i);
        }
        int[] indegree = new int[n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(i, new ArrayList<>());
            if (left[i] != -1) {
                indegree[left[i]]++;
                map.get(i).add(left[i]);
            }
            if (right[i] != -1) {
                indegree[right[i]]++;
                map.get(i).add(right[i]);
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> nums[a] - nums[b]);
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0)
                pq.offer(i);
        }
        List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            res.add(nums[cur]);
            for (int nei : map.getOrDefault(cur, new ArrayList<>())) {
                indegree[nei]--;
                if (indegree[nei] == 0)
                    pq.offer(nei);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] orders = { 3, 1, 5, 5, 2 };
        System.out.println(sol.solve(orders)); // Output: [3, 5, 4, 2, 1]
    }
}
