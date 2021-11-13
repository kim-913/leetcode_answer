import java.util.*;

public class solution_11_11 {
    public static void main(String[] args) {

    }

    int diff = Integer.MAX_VALUE;
    String res = "";

    public static String nextClosestTime(String time) {
        Set<Integer> set = new HashSet<>();
        for (char c : time.toCharArray()) {
            if (Character.isDigit(c))
                set.add(c - '0');
        }
        // if only one digit
        if (set.size() == 1)
            return time;

        List<Integer> list = new ArrayList<>(set);
        int minute = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
        dfs(0, "", list, minute);
        return res;
    }

    /*
     * HH:MM first H <= 2 if first H is 0 or 1, second H can be any values between 0
     * to 9 if first H is 2, second <= 3
     * 
     * first M <= 5 second M can be anyvalues
     */
    private static void dfs(int position, String cur, List<Integer> digits, int target) {
        if (position == 4) {
            int m = Integer.parseInt(cur.substring(0, 2)) * 60 + Integer.parseInt(cur.substring(2, 4));
            if (m == target)
                return;
            int d = m - target > 0 ? m - target : 1440 + m - target;
            if (d < diff) {
                diff = d;
                res = cur.substring(0, 2) + ":" + cur.substring(2, 4);
            }
            return;
        }

        for (int i = 0; i < digits.size(); i++) {
            if (position == 0 && digits.get(i) > 2)
                continue;
            if (position == 1 && Integer.parseInt(cur) * 10 + digits.get(i) > 23)
                continue;
            if (position == 2 && digits.get(i) > 5)
                continue;
            if (position == 3 && Integer.parseInt(cur.substring(2)) * 10 + digits.get(i) > 59)
                continue;
            dfs(position + 1, cur + digits.get(i), digits, target);
        }
    }


    //

    public int maxDistToClosest(int[] seats) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < seats.length; i++){
            if(seats[i] == 1) list.add(i);
        }
        int res = Math.max(list.get(0) - 0, 1);
        int curDist = 1;
        if(list.size() == 1) return Math.max((list.get(0) - 0), (seats.length - 1 - list.get(0)));
        int prev = list.get(0);
        for(int i = 1; i < list.size(); i++){
            curDist = ((list.get(i) + prev) / 2) - prev;
            if(curDist > res) res = curDist;
            prev = list.get(i);
        }
        if(seats[seats.length - 1] == 0) res = Math.max((seats.length - 1 - list.get(list.size() - 1)), res);
        return res;
    }
    */
    public int maxDistToClosest(int[] seats) {
        int len = seats.length;
        int prev = -1, future = 0;
        int ans = 0;
        
        for(int i = 0; i < len; i++) {
            if(seats[i] == 1) {
                prev = i;
            } else {
                while(future < len && seats[future] == 0 || future < i) {
                    future++;
                }
                
                int left = prev == -1 ? len : i - prev;
                int right = future == len ? len : future - i;
                ans = Math.max(ans, Math.min(left, right));
            }
        }
        return ans;
    }
}
