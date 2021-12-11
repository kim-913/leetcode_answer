class Solution {
    public boolean isBipartite(int[][] graph) {
        int nodes = graph.length;
        int[] colors = new int[nodes];
        // use 0 to denote nodes that aren't connected, 1 to denote red, 2 to denote
        // blue
        for (int i = 0; i < nodes; i++) {
            // haven't initiate the color of the node yet
            if (graph[i].length != 0 && colors[i] == 0) {
                // color the current node to red
                colors[i] = 1;
                // now, color its neighbors
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                while (!q.isEmpty()) {
                    int cur = q.poll();
                    for (int neighbor : graph[cur]) {
                        // determine if the neighbors are colored to the same, if not, color them to a
                        // different color
                        // first, determine if we've initiaite the current or not
                        if (colors[neighbor] == 0) {
                            colors[neighbor] = colors[cur] == 1 ? 2 : 1;
                            q.offer(neighbor);
                        } else if (colors[neighbor] == colors[cur])
                            return false;
                    }
                }
            }
        }
        return true;
    }
}