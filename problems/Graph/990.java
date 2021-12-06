class Solution {
    private int[] parent;

    class UnionFind {
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int parentX = parent[x];
            int parentY = parent[y];
            parent[parentX] = parentY;
        }
    }

    public boolean equationsPossible(String[] equations) {
        UnionFind uf = new UnionFind(26);
        for (String equation : equations) {
            int char1 = equation.charAt(0) - 'a';
            int char2 = equation.charAt(3) - 'a';
            if (equation.charAt(1) == '=') {
                int parent1 = uf.find(char1);
                int parent2 = uf.find(char2);
                if (parent1 != parent2) {
                    uf.union(parent1, parent2);
                }
            }
        }
        for (String equation : equations) {
            int char1 = equation.charAt(0) - 'a';
            int char2 = equation.charAt(3) - 'a';
            if (equation.charAt(1) == '!') {
                int parent1 = uf.find(char1);
                int parent2 = uf.find(char2);
                if (parent1 == parent2)
                    return false;
            }
        }
        return true;
    }
}