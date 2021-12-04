class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> s = new HashSet<>();
        // start from room 0
        s.add(0);
        q.offer(0);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                for (int other : rooms.get(cur)) {
                    if (!s.contains(other)) {
                        s.add(other);
                        q.offer(other);
                    }
                    if (s.size() == rooms.size())
                        return true;
                }
            }
        }
        return false;
    }
}