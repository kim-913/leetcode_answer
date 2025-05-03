import java.util.Arrays;

class Solution {
    public long minTime(int[] skill, int[] mana) {
        long[] endTime = new long[skill.length];
        Arrays.fill(endTime, 0);
        for (int i = 0; i < mana.length; ++i) {
            long t = 0, maxDiff = 0;
            for (int j = 0; j < skill.length; ++j) {
                maxDiff = Math.max(maxDiff, endTime[j] - t);
                t += (long) skill[j] * (long) mana[i];
                endTime[j] = t;
            }
            for (int j = 0; j < skill.length; ++j)
                endTime[j] += maxDiff;
        }
        return endTime[endTime.length - 1];
    }
}
