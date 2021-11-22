package problems;

import java.util.*;

class Solution {

    // DFS
    // boolean[] visited;
    // public boolean validPath(int n, int[][] edges, int start, int end) {
    //     Map<Integer, List<Integer>> buildGraph = new HashMap<>();
    //     visited = new boolean[n];
    //     for(int i = 0; i < n; i++){
    //         buildGraph.put(i, new ArrayList<>());
    //     }
    //     for(int[] edge: edges){
    //         buildGraph.get(edge[0]).add(edge[1]);
    //         buildGraph.get(edge[1]).add(edge[0]);
    //     }
    //     return dfs(buildGraph, start, end, start);
    // }
    
    // public boolean dfs(Map<Integer, List<Integer>> graph, int start, int end, int cur){
    //     if(cur == end) return true;
    //     visited[cur] = true;
    //     List<Integer> neighbors = graph.get(cur);
    //     for(int neighbor: neighbors){
    //         if(!visited[neighbor]) {
    //             return dfs(graph, start, end, neighbor);
    //         }
    //     }
    //     return false;
    // }

    // Union Find
    public int[] parent;
    public boolean validPath(int n, int[][] edges, int start, int end) {
        parent = new int[n];
        for(int i = 0; i < n; i++) parent[i] = i;
        for(int[] edge : edges) {
            int p1 = findParent(edge[0]);
            int p2 = findParent(edge[1]);
            if(p1 != p2) {
                parent[p1] = p2;
            }
        }
        return findParent(start) == findParent(end);
    }

    private int findParent(int child) {
    // if the parent of the current node is itself, means that it's already the root
    // otherwise, find the parent node of it's parent node
    // recursive: from down to up
        return parent[child] == child ? child : (parent[child] = findParent(parent[child]));
    }
}