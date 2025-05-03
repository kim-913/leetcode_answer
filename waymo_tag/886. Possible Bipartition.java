import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    // Overall time complexity: O(n + E), space complexity: O(n + E)
    public boolean possibleBipartition(int n, int[][] dislikes) {
        // visited, also can color
        int[] color = new int[n + 1];
        Map<Integer, List<Integer>> map = new HashMap<>();
        // constructing graph
        for (int i = 1; i <= n; i++)
            map.put(i, new ArrayList<>());
        // O(E), E is the pairs, or the edges between each nodes
        for (int[] dislike : dislikes) {
            map.get(dislike[0]).add(dislike[1]);
            map.get(dislike[1]).add(dislike[0]);
        }
        // traverse through each node with all edges, O(n)
        for (int i = 1; i <= n; i++) {
            if (color[i] == 0 && !dfs(i, map, color, 1))
                return false;
        }
        return true;
    }

    private boolean dfs(int cur, Map<Integer, List<Integer>> map, int[] color, int curColor) {
        color[cur] = curColor;
        for (int nei : map.get(cur)) {
            if (color[nei] == color[cur])
                return false;
            if (color[nei] == 0 && !dfs(nei, map, color, curColor * -1))
                return false;
        }
        return true;
    }
}