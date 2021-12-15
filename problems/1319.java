class Solution {
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1)
            return -1; // To connect all nodes need at least n-1 edges
        int[] parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
        int components = n;
        for (int[] c : connections) {
            int p1 = findParent(parent, c[0]);
            int p2 = findParent(parent, c[1]);
            if (p1 != p2) {
                parent[p1] = p2; // Union 2 component
                components--;
            }
        }
        return components - 1; // Need (components-1) cables to connect components together
    }

    private int findParent(int[] parent, int i) {
        if (i == parent[i])
            return i;
        return parent[i] = findParent(parent, parent[i]); // Path compression
    }
}