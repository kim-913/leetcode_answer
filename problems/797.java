package problems;
import java.util.*;
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        backTrack(res, graph, new ArrayList<>(Arrays.asList(0)), 0);
        return res;
    }
    
    private void backTrack(List<List<Integer>> res, int[][] graph, List<Integer> list, int cur){
        if(cur == graph.length - 1) res.add(new ArrayList<>(list));
        for(int neighbor: graph[cur]){
            list.add(neighbor);
            backTrack(res, graph, list, neighbor);
            list.remove(list.size() - 1);
        }
    }
}