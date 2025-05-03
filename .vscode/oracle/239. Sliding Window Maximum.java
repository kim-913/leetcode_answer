import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k == 0)
            return new int[0];
        if (k == 1)
            return nums;
        Deque<Integer> queue = new ArrayDeque<>();
        int[] res = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            while (!queue.isEmpty() && queue.peek() < i - k + 1) {
                queue.poll();
            }
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.offer(i);
            if (i >= k - 1)
                res[i - k + 1] = nums[queue.peek()];
        }

        return res;
    }
}

class SolutionNLogK {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < n; i++) {
            maxHeap.offer(new int[] { nums[i], i });
            if (i >= k - 1) {
                while (maxHeap.peek()[1] <= i - k) {
                    maxHeap.poll();
                }
                res[i - k + 1] = maxHeap.peek()[0];
            }
        }

        return res;
    }
}
