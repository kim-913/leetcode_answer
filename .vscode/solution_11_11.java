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

}
