class Solution {
    public int slidingPuzzle(int[][] board) {
        String target = "123450";
        String start = "";
        int res = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                start += board[i][j];
            }
        }
        // directions records the position 0 can go in all 6 positions
        int[][] directions = new int[][] { { 1, 3 }, { 0, 2, 4 }, { 1, 5 }, { 0, 4 }, { 1, 3, 5 }, { 2, 4 } };
        Set<String> visited = new HashSet();
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        visited.add(start);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                if (cur.equals(target))
                    return res;
                int zero = cur.indexOf("0");
                for (int d : directions[zero]) {
                    String process = swap(cur, zero, d);
                    if (visited.contains(process))
                        continue;
                    visited.add(process);
                    q.offer(process);
                }
            }
            res++;
        }
        return -1;
    }

    private String swap(String s, int i, int j) {
        StringBuilder sb = new StringBuilder(s);
        char a = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, a);
        return sb.toString();
    }
}