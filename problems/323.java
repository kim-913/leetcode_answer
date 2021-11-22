package problems;
class Solution {
    boolean[] used;
    public int countComponents(int n, int[][] edges) {
        if(edges == null) return 0;
        used = new boolean[n];
        int res = 0;
        for(int i = 0; i < n; i++){
            if(!used[i]) {
                res++;
                dfs(edges, i);
            }
        }
        return res;
    }
    
    public void dfs(int[][] edges, int cur){
        // if we've already visited this node(according to the neighbor of the previous nodes, return)
        if(used[cur]) return;
        used[cur] = true;
        for(int[] edge: edges){
            int v = edge[0];
            int e = edge[1];
            if(v == cur || e == cur){
                if(v == cur) dfs(edges, e);
                else dfs(edges, v);
            }
        }
    }
}