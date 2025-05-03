import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class LCAinGraph {
    public int findLCA(int n, int[][] edges, int u, int v) {
        Map<Integer, List<Integer>> parentMap = new HashMap<>();
        for (int i = 1; i <= n; i++)
            parentMap.put(i, new ArrayList<>());
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1];
            parentMap.get(to).add(from);
        }
        Map<Integer, Integer> distU = bfs(parentMap, u);
        Map<Integer, Integer> distV = bfs(parentMap, v);

        Set<Integer> commonAcestor = distU.keySet();
        commonAcestor.retainAll(distV.keySet());
        int minLevel = Integer.MAX_VALUE;
        int res = -1;
        for (int node : commonAcestor) {
            int level = Math.max(distU.get(node), distV.get(node));
            if (level < minLevel) {
                minLevel = level;
                res = node;
            }
        }
        return res;
    }

    private Map<Integer, Integer> bfs(Map<Integer, List<Integer>> parentMap, int start) {
        Map<Integer, Integer> levelMap = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        levelMap.put(start, 0);
        while (!q.isEmpty()) {
            int cur = q.poll();
            int curLevel = levelMap.get(cur);
            for (int parent : parentMap.get(cur)) {
                if (!levelMap.containsKey(parent)) {
                    q.offer(parent);
                    levelMap.put(parent, curLevel + 1);
                }
            }
        }
        return levelMap;
    }
}
