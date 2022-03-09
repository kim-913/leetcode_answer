// LC 277. Find the Celebrity
public class Solution extends Relation {
    public int findCelebrity(int n) {
        int celebrity = 0;
        for (int i = 1; i < n; i++) {
            if (knows(celebrity, i))
                celebrity = i;
        }
        for (int i = 0; i < n; i++) {
            if (i != celebrity && knows(celebrity, i))
                return -1;
            if (i != celebrity && !knows(i, celebrity))
                return -1;
        }
        return celebrity;
    }
}


// LC 323. Number of Connected Components in an Undirected Graph

// DFS
class Solution {
    boolean[] visited;

    public int countComponents(int n, int[][] edges) {
        visited = new boolean[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                res++;
                dfs(i, edges);
            }
        }
        return res;
    }

    private void dfs(int i, int[][] edges) {
        if (visited[i])
            return;
        visited[i] = true;
        for (int[] edge : edges) {
            if (edge[0] == i || edge[1] == i) {
                if (edge[0] == i)
                    dfs(edge[1], edges);
                else
                    dfs(edge[0], edges);
            }
        }
    }
}
// Union Find
class Solution {
    private int[] parents;

    public int countComponents(int n, int[][] edges) {
        parents = new int[n];
        // initialize parents
        for (int i = 0; i < n; i++)
            parents[i] = i;
        Set<Integer> set = new HashSet();
        // union first
        for (int[] edge : edges)
            union(edge[0], edge[1]);
        // set only takes the parents(unique connected components)
        for (int i = 0; i < n; i++)
            set.add(find(i));
        return set.size();
    }

    // find the parent of each node and set one's parent to another
    private void union(int edge1, int edge2) {
        int parent1 = find(edge1);
        int parent2 = find(edge2);
        parents[parent1] = parent2;
    }

    private int find(int edge) {
        // do a recursive path compression first to reduce run time
        if (parents[edge] != edge)
            parents[edge] = find(parents[edge]);
        return parents[edge];
    }
}



// LC 743. Network Delay Time
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] time : times) {
            map.putIfAbsent(time[0], new HashMap<>());
            map.get(time[0]).put(time[1], time[2]);
        }
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int res = 0;
        pq.add(new int[] { 0, k });
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (visited[cur[1]])
                continue;
            visited[cur[1]] = true;
            res = cur[0];
            n--;
            if (map.containsKey(cur[1])) {
                for (int nei : map.get(cur[1]).keySet()) {
                    pq.add(new int[] { cur[0] + map.get(cur[1]).get(nei), nei });
                }
            }
        }
        return n == 0 ? res : -1;
    }
}

// LC 785. Is Graph Bipartite
class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].length != 0 && colors[i] == 0) {
                colors[i] = 1;
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                while (!q.isEmpty()) {
                    int cur = q.poll();
                    for (int nei : graph[cur]) {
                        if (colors[nei] == 0) {
                            colors[nei] = colors[cur] == 1 ? 2 : 1;
                            q.offer(nei);
                        } else if (colors[nei] == colors[cur])
                            return false;
                    }
                }
            }
        }
        return true;
    }
}