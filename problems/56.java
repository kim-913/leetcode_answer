class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[] cur = intervals[0];
        res.add(cur);
        for (int i = 0; i < intervals.length; i++) {
            int curStart = cur[0];
            int curEnd = cur[1];
            int nextStart = intervals[i][0];
            int nextEnd = intervals[i][1];
            if (nextStart <= curEnd) {
                cur[1] = Math.max(curEnd, nextEnd);
            } else {
                cur = intervals[i];
                res.add(cur);
            }
        }
        return res.toArray(new int[res.size()][2]);
    }
}