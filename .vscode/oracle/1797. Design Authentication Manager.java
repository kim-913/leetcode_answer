import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class AuthenticationManager {
    int timeToLive;

    class Pair {
        String tokenId;
        int expireTime;

        public Pair(String tokenId, int expireTime) {
            this.tokenId = tokenId;
            this.expireTime = expireTime;

        }
    }

    Queue<Pair> q = new PriorityQueue<>((Pair p1, Pair p2) -> p1.expireTime - p2.expireTime);

    Map<String, Integer> map = new HashMap<>();

    public AuthenticationManager(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public void generate(String tokenId, int currentTime) {
        if (!map.containsKey(tokenId)) {
            q.offer(new Pair(tokenId, currentTime + timeToLive));
        }
        map.put(tokenId, currentTime + timeToLive);
    }

    public void renew(String tokenId, int currentTime) {
        if (map.containsKey(tokenId) && map.get(tokenId) > currentTime) {
            map.put(tokenId, currentTime + timeToLive);
        }

    }

    public int countUnexpiredTokens(int currentTime) {
        while (!q.isEmpty() && q.peek().expireTime <= currentTime) {
            Pair p = q.poll();
            if (map.get(p.tokenId) > p.expireTime) {
                q.offer(new Pair(p.tokenId, map.get(p.tokenId)));
            } else {
                map.remove(p.tokenId);
            }
        }
        return q.size();
    }
}

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * AuthenticationManager obj = new AuthenticationManager(timeToLive);
 * obj.generate(tokenId,currentTime);
 * obj.renew(tokenId,currentTime);
 * int param_3 = obj.countUnexpiredTokens(currentTime);
 */