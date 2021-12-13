class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++)
            jobs[i] = new int[] { startTime[i], endTime[i], profit[i] };
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0);
        for (int[] job : jobs) {
            // The floorEntry(K key) method is used to return a key-value mapping associated
            // with the greatest key less than or equal to the given key, or null if there
            // is no such key.
            int cur = dp.floorEntry(job[0]).getValue() + job[2];
            // System.out.println(dp.floorEntry(job[0]));
            if (cur > dp.lastEntry().getValue())
                dp.put(job[1], cur);
        }
        return dp.lastEntry().getValue();
    }
}