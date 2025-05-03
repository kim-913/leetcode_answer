import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class SolutionBest {
    public int candy(int[] ratings) {
        int[] res = new int[ratings.length];
        Arrays.fill(res, 1);
        for (int i = 0; i < ratings.length - 1; i++) {
            if (ratings[i + 1] > ratings[i]) {
                res[i + 1] = res[i] + 1;
            }
        }
        for (int i = ratings.length - 1; i >= 1; i--) {
            if (ratings[i - 1] > ratings[i]) {
                res[i - 1] = Math.max(res[i - 1], res[i] + 1);
            }
        }
        int sum = 0;
        for (int num : res)
            sum += num;
        return sum;
    }
}

class Solution {
    public int candy(int[] ratings) {
        // 0:{1, 2}, 1: {0}, 2:{1}
        // 1:{2}, 2:{1,2}, 2:{1}
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < ratings.length; i++) {
            map.computeIfAbsent(ratings[i], k -> new ArrayList<>()).add(i);
        }
        PriorityQueue<Map.Entry<Integer, List<Integer>>> pq = new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry::getKey));
        pq.addAll(map.entrySet());

        int[] res = new int[ratings.length];
        Arrays.fill(res, 1);
        while (!pq.isEmpty()) {
            Map.Entry<Integer, List<Integer>> entry = pq.poll();
            for (int index : entry.getValue()) {
                if (index > 0 && ratings[index] > ratings[index - 1]) {
                    res[index] = Math.max(res[index], res[index - 1] + 1);
                }
                if (index < ratings.length - 1 && ratings[index] > ratings[index + 1]) {
                    res[index] = Math.max(res[index], res[index + 1] + 1);
                }
            }
        }
        int sum = 0;
        for (int c : res)
            sum += c;
        return sum;
    }
}