// solution 1 -> bubble sort, swap the least quiet to the front, and swap n times
class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int[] result = new int[quiet.length];
        for (int i = 0; i < quiet.length; i++) {
            result[i] = i;
        }
        for (int k = 0; k < quiet.length; k++) {
            boolean changed = false;
            for (int[] r : richer) {
                if (quiet[result[r[0]]] < quiet[result[r[1]]]) {
                    result[r[1]] = result[r[0]];
                    changed = true;
                }
            }
            if (!changed)
                break;
        }
        return result;
    }
}

// solution 2 -> topological sort
class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        //
        int n = quiet.length;
        int[] res = new int[n];
        int[] indegree = new int[n];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] rich : richer) {
            map.putIfAbsent(rich[0], new HashSet<>());
            map.get(rich[0]).add(rich[1]);
            indegree[rich[1]]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            res[i] = i;
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                if (map.containsKey(cur)) {
                    for (int next : map.get(cur)) {
                        if (quiet[res[next]] > quiet[res[cur]])
                            res[next] = res[cur];
                        if (--indegree[next] == 0)
                            q.offer(next);
                    }
                }
            }
        }
        return res;
    }
}

// solution 3 -> dfs
class solution {
    HashMap<Integer, List<Integer>> richer2 = new HashMap<>();
    int res[];

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        for (int i = 0; i < n; ++i)
            richer2.put(i, new ArrayList<Integer>());
        for (int[] v : richer)
            richer2.get(v[1]).add(v[0]);
        res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++)
            dfs(i, quiet);
        return res;
    }

    int dfs(int i, int[] quiet) {
        if (res[i] >= 0)
            return res[i];
        res[i] = i;
        for (int j : richer2.get(i))
            if (quiet[res[i]] > quiet[dfs(j, quiet)])
                res[i] = res[j];
        return res[i];
    }
}