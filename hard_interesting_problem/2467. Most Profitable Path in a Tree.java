import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    private int res = Integer.MIN_VALUE;

    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = amount.length;
        for (int i = 0; i < n; i++)
            map.put(i, new ArrayList<>());
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        Map<Integer, Integer> bobPath = new HashMap<>();
        boolean[] visited = new boolean[n];
        findBob(bob, 0, bobPath, visited, map);
        // update visited
        Arrays.fill(visited, false);
        findAlice(0, 0, bobPath, visited, map, amount, 0);
        return res;
    }

    private boolean findBob(int cur, int time, Map<Integer, Integer> bobPath, boolean[] visited,
            Map<Integer, List<Integer>> map) {
        bobPath.put(cur, time);
        visited[cur] = true;
        if (cur == 0)
            return true;
        for (int next : map.get(cur)) {
            if (!visited[next] && findBob(next, time + 1, bobPath, visited, map)) {
                return true;
            }
        }
        // not reaching 0, remove from path
        bobPath.remove(cur);
        return false;
    }

    private void findAlice(int cur, int time, Map<Integer, Integer> bobPath, boolean[] visited,
            Map<Integer, List<Integer>> map, int[] amount, int income) {
        visited[cur] = true;
        // alice reach first
        if (!bobPath.containsKey(cur) || time < bobPath.get(cur)) {
            income += amount[cur];
        } else if (time == bobPath.get(cur)) {
            income += amount[cur] / 2;
        }
        // reach leaf, neighbor size = 1 and source!= 0
        if (map.get(cur).size() == 1 && cur != 0) {
            res = Math.max(res, income);
        }
        for (int next : map.get(cur)) {
            if (!visited[next]) {
                findAlice(next, time + 1, bobPath, visited, map, amount, income);
            }
        }
    }
}