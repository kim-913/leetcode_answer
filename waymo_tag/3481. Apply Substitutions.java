import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution {
    public String applySubstitutions(List<List<String>> replacements, String text) {
        // Build the mapping and graph
        Map<String, String> valueMap = new HashMap<>();
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();

        for (List<String> replacement : replacements) {
            String key = replacement.get(0);
            String value = replacement.get(1);
            valueMap.put(key, value);
            graph.putIfAbsent(key, new HashSet<>());
            indegree.putIfAbsent(key, 0);
        }

        // Build dependency graph based on placeholders in values
        for (Map.Entry<String, String> entry : valueMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            for (String otherKey : valueMap.keySet()) {
                if (!key.equals(otherKey)) {
                    String placeholder = "%" + otherKey + "%";
                    if (value.contains(placeholder)) {
                        graph.get(key).add(otherKey);
                        indegree.put(otherKey, indegree.getOrDefault(otherKey, 0) + 1);
                    }
                }
            }
        }

        // Topological sort
        Queue<String> queue = new LinkedList<>();
        for (String key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                queue.offer(key);
            }
        }

        List<String> topoOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            String current = queue.poll();
            topoOrder.add(current);
            for (String neighbor : graph.getOrDefault(current, new HashSet<>())) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Resolve all values
        for (String key : topoOrder) {
            String val = valueMap.get(key);
            for (String otherKey : topoOrder) {
                String placeholder = "%" + otherKey + "%";
                if (val.contains(placeholder)) {
                    val = val.replace(placeholder, valueMap.get(otherKey));
                }
            }
            valueMap.put(key, val);
        }
        String result = text;
        for (String key : topoOrder) {
            String placeholder = "%" + key + "%";
            result = result.replace(placeholder, valueMap.get(key));
        }

        return result;
    }
}