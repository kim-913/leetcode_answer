class Solution {
    private int dfs(Map<Integer, List<Integer>> graph, List<Boolean> apples, int node, int prev) {
        int result = 0;
        for (int next : graph.get(node)) {
            if (next != prev) {
                result += dfs(graph, apples, next, node);
            }
        }
        if (node > 0 && (result > 0 || apples.get(node))) {
            result += 2;
        }
        return result;
    }

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++)
            graph.put(i, new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return dfs(graph, hasApple, 0, -1);
    }
}