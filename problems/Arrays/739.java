class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Deque<Integer> stack = new LinkedList<>();

        for (int cur = 0; cur < n; cur++) {
            int temp = temperatures[cur];
            while (!stack.isEmpty() && temperatures[stack.peek()] < temp) {
                int prev = stack.pop();
                res[prev] = cur - prev;
            }
            stack.push(cur);
        }
        return res;
    }
}