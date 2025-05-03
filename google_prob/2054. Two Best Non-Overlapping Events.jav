class Solution {
    public int maxTwoEvents(int[][] events) {
        // at each pos, what's the max profit
        Arrays.sort(events, (a, b) -> (a[0] - b[0]));
        // store endtime and value
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> (a.key - b.key));
        int maxVal = 0, res = 0;
        for (int[] event: events) {
            while (!pq.isEmpty() && pq.peek().key < event[0]) {
                maxVal = Math.max(maxVal, pq.peek().val);
                pq.poll();
            }
            res = Math.max(res, maxVal + event[2]);
            pq.add(new Pair(event[1], event[2]));
        }
        return res;
    }

    class Pair {
        int key;
        int val;
        public Pair(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}