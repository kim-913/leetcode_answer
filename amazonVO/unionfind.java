// 399. Evaluate Division
class Solution {
    // use union find for this problem because they are disjoint sets
    // first, create union find data structure
    private class UnionFind {
        private int[] parent;
        private double[] weight;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.weight = new double[n];
            // first, initialize the parent to be itself
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        public void union(int x, int y, double value) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX == parentY)
                return;
            parent[parentX] = parentY;
            weight[parentX] = weight[y] * value / weight[x];
        }

        public int find(int x) {
            // path compression, recursively find parent, which is the parent's value equals
            // to the vertice's value
            if (x != parent[x]) {
                int origin = parent[x];
                parent[x] = find(parent[x]);
                weight[x] *= weight[origin];
            }
            return parent[x];
        }

        public double isConnected(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX == parentY)
                return weight[x] / weight[y];
            else
                return -1.0d;
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int size = equations.size();
        UnionFind uf = new UnionFind(2 * size);
        // first step, map the value of the variable to id so that the bottom layer of
        // the nuion if implemented using an array
        // . which is convenient for coding
        Map<String, Integer> map = new HashMap<>(2 * size);
        int id = 0;
        for (int i = 0; i < size; i++) {
            List<String> equation = equations.get(i);
            String s1 = equation.get(0);
            String s2 = equation.get(1);
            if (!map.containsKey(s1)) {
                map.put(s1, id);
                id++;
            }
            if (!map.containsKey(s2)) {
                map.put(s2, id);
                id++;
            }
            uf.union(map.get(s1), map.get(s2), values[i]);
        }

        // second step, make a query
        int querySize = queries.size();
        double[] res = new double[querySize];
        for (int i = 0; i < querySize; i++) {
            List<String> query = queries.get(i);
            String s1 = query.get(0);
            String s2 = query.get(1);

            Integer id1 = map.get(s1);
            Integer id2 = map.get(s2);

            if (id1 == null || id2 == null)
                res[i] = -1.0d;
            else
                res[i] = uf.isConnected(id1, id2);
        }
        return res;
    }
}



// LC 990. Satisfiability of Equality Equations
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