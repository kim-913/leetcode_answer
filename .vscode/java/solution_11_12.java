import java.util.*;

public class solution_11_12 {

    public static void main(String[] args) {

    }

    // LC 857. Minimum Cost to Hire K Workers
    public double mincostToHireWorkers(int[] q, int[] w, int K) {
        double[][] workers = new double[q.length][2];
        for (int i = 0; i < q.length; ++i)
            workers[i] = new double[] { (double) (w[i]) / q[i], (double) q[i] };
        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));
        double res = Double.MAX_VALUE, qsum = 0;
        PriorityQueue<Double> pq = new PriorityQueue<>();
        for (double[] worker : workers) {
            qsum += worker[1];
            pq.add(-worker[1]);
            if (pq.size() > K)
                qsum += pq.poll();
            if (pq.size() == K)
                res = Math.min(res, qsum * worker[0]);
        }
        return res;
    }

    // LC 27. Remove Element
    public int removeElement(int[] nums, int val) {
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[left] = nums[i];
                left++;
            }
        }
        return left;
    }

    // LC 2067. Number of Equal Count Substrings
    public int equalCountSubstrings(String s, int count) {
        if (s == null || s.length() == 0 || count < 1) {
            return 0;
        }

        int result = 0;
        int n = s.length();

        Set<Character> maxUnique = new HashSet<>();
        for (char c : s.toCharArray()) {
            maxUnique.add(c);
        }

        for (int unique = 1; unique <= maxUnique.size(); ++unique) {
            int[] cnt = new int[26];
            int len = count * unique;
            int hasCount = 0;
            for (int i = 0; i < n; ++i) {
                if (++cnt[s.charAt(i) - 'a'] == count) {
                    ++hasCount;
                }

                if (i >= len && --cnt[s.charAt(i - len) - 'a'] == count - 1) {
                    --hasCount;
                }

                if (hasCount == unique) {
                    result++;
                }

            }
        }
        return result;
    }

    // LC 443. String Compression
    public int compress(char[] chars) {
        int res = 0, curIndex = 0;
        while (curIndex < chars.length) {
            char cur = chars[curIndex];
            int count = 0;
            while (curIndex < chars.length && chars[curIndex] == cur) {
                curIndex++;
                count++;
            }
            chars[res++] = cur;
            if (count != 1) {
                for (char c : Integer.toString(count).toCharArray()) {
                    chars[res++] = c;
                }
            }
        }
        return res;
    }

}
