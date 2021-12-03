class Solution {
    private boolean[] visited;

    public int findCircleNum(int[][] isConnected) {
        visited = new boolean[isConnected.length];
        int res = 0;
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                // make sure that here is i!!!!
                dfs(isConnected, i);
                res++;
            }
        }
        return res;
    }

    private void dfs(int[][] isConnected, int cur) {
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i] && isConnected[i][cur] == 1) {
                visited[i] = true;
                dfs(isConnected, i);
            }
        }
    }
}