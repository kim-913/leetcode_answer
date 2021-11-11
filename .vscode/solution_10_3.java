import java.util.*;

/**
 * solution_10_3
 */
public class solution_10_3 {

    public static void main(String[] args) {
        comparatorValue(new int[] { 7, 5, 9 }, new int[] { 13, 1, 4 }, 3);

    }

    public static int comparatorValue(int[] a, int[] b, int d) {
        // sort a and b first
        // Arrays.sort(a);
        Arrays.sort(b);
        // [5, 7, 9]
        // [1, 4, 13]
        int res = a.length;
        int left = 0, right = b.length - 1;
        for (int i = 0; i < a.length; i++) {
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (Math.abs(b[mid] - a[i]) > d)
                    continue;
                if (Math.abs(b[left] - a[i]) < d || Math.abs(b[right] - a[i]) < d) {
                    res--;
                } else if (Math.abs(b[left] - a[i]) < Math.abs(b[right] - a[i])) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            left = 0;
            right = b.length - 1;
        }
        return res;
    }
}