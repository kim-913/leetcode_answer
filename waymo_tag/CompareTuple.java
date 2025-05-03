/*
 * You’re given two lists of intervals:

a = [(0,2), (1,4)] → interpreted as setting 1 for bits from 0 to 2 and 1 to 4

b = [(0,4), (1,1), (0,1)] → same logic

We can't expand the tuples into full lists explicitly.

 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompareTuple {
    public static int countMismatch(List<int[]> A, List<int[]> B) {
        // using linesweep
        // storeds, the position, delta, and type
        // define 0 for type A, and 1 for type B, +1 for A start, -1 for A end, same for
        // B
        List<int[]> events = new ArrayList<>();
        for (int[] interval : A) {
            events.add(new int[] { interval[0], 1, 0 });
            events.add(new int[] { interval[1], -1, 0 });
        }
        for (int[] interval : B) {
            events.add(new int[] { interval[0], 1, 1 });
            events.add(new int[] { interval[1], -1, 1 });
        }
        Collections.sort(events, (a, b) -> {
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        int prev = 0, curA = 0, curB = 0, mismatch = 0;
        for (int[] event : events) {
            int curPos = event[0], delta = event[1], type = event[2];
            if (curPos > curA && curA != curB) {
                mismatch += curPos - prev;
            }
            if (type == 0)
                curA += delta;
            else
                curB += delta;
            prev = curPos;
        }
        return mismatch;
    }
}
