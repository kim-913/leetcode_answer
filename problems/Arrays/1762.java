class Solution {
    public int[] findBuildings(int[] heights) {
        int len = heights.length;
        Deque<Integer> s = new LinkedList<>();
        for (int i = len - 1; i >= 0; i--) {
            if (i == len - 1 || heights[i] > heights[s.peek()]) {
                s.push(i);
            }
        }
        int n = s.size();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = s.pop();
        }
        return res;
    }
}