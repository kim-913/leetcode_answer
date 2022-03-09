// LC 909. Snakes and Ladders
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n * n];
        int index = 0;
        for (int i = n - 1; i >= 0; i -= 2) {
            for (int j = 0; j < n; j++) {
                arr[index] = board[i][j];
                index++;
            }
            index += n;
        }
        index = n;
        for (int i = n - 2; i >= 0; i -= 2) {
            for (int j = n - 1; j >= 0; j--) {
                arr[index] = board[i][j];
                index++;
            }
            index += n;
        }

        boolean[] visited = new boolean[n * n];
        Queue<Integer> q = new LinkedList<>();
        int start = arr[0] > -1 ? arr[0] - 1 : 0;
        q.offer(start);
        visited[start] = true;
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                if (cur == n * n - 1) {
                    return step;
                }
                for (int next = cur + 1; next <= Math.min(cur + 6, n * n - 1); next++) {
                    int dest = arr[next] > -1 ? arr[next] - 1 : next;
                    if (!visited[dest]) {
                        visited[dest] = true;
                        q.offer(dest);
                    }
                }
            }
            step++;
        }
        return -1;
    }
}


// LC 815. Bus Routes
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        int n = routes.length;
        Map<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j : routes[i]) {
                map.putIfAbsent(j, new HashSet<>());
                map.get(j).add(i);
            }
        }
        Queue<int[]> q = new LinkedList<>();
        // current stop, how many bus taken
        q.offer(new int[] { source, 0 });
        Set<Integer> visited = new HashSet<>();
        visited.add(source);
        boolean[] seen_routes = new boolean[n];
        while (!q.isEmpty()) {
            int curStop = q.peek()[0];
            int busCnt = q.peek()[1];
            q.poll();
            if (curStop == target)
                return busCnt;
            for (int curRoute : map.get(curStop)) {
                if (seen_routes[curRoute])
                    continue;
                for (int next : routes[curRoute]) {
                    if (!visited.contains(next)) {
                        visited.add(next);
                        q.offer(new int[] { next, busCnt + 1 });
                    }
                }
                seen_routes[curRoute] = true;
            }
        }
        return -1;
    }
}