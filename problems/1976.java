class Solution {
    public int countPaths(int n, int[][] roads) {
        int mod = (int) (Math.pow(10, 9) + 7);
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        Map<Integer, List<Integer>> cost = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adjList.putIfAbsent(i, new ArrayList<>());
            cost.putIfAbsent(i, new ArrayList<>());
        }
        for (int[] road : roads) {
            adjList.get(road[0]).add(road[1]);
            adjList.get(road[1]).add(road[0]);
            cost.get(road[0]).add(road[2]);
            cost.get(road[1]).add(road[2]);
        }
        long[] distance = new long[n];
        int[] paths = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;
        paths[0] = 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return (int) (distance[a] - distance[b]);
        });
        pq.add(0);
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            int size = adjList.get(cur).size();
            for (int i = 0; i < size; i++) {
                int v = adjList.get(cur).get(i);
                int c = cost.get(cur).get(i);
                if (distance[cur] + c < distance[v]) {
                    // edit distance
                    distance[v] = distance[cur] + c;
                    // add to the path
                    paths[v] = paths[cur];
                    pq.remove(v);
                    pq.add(v);
                } else if (distance[cur] + c == distance[v]) {
                    paths[v] = (paths[v] + paths[cur]) % mod;
                }
            }
        }
        return paths[n - 1];
    }
}