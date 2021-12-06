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