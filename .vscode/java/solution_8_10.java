import java.util.*;
public class solution_8_10 {
    
    public static void main(String[] args) {
        
    }

    // LeetCode 56. Merge Intervals
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if(intervals.length <= 1) return intervals;
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
        
        // add first
        int[] cur = intervals[0];
        res.add(cur);
        for(int[] interval: intervals){
            int cur_start = cur[0];
            int cur_end = cur[1];
            int next_start = interval[0];
            int next_end = interval[1];
            if(next_start <= cur_end) {
                //merge
                cur[1] = Math.max(next_end, cur_end);
            } else {
                cur = interval;
                res.add(cur);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    // LeetCode 986. Interval List Intersections
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> res = new ArrayList<>();
        if(secondList == null || secondList.length == 0) return secondList;
        if(firstList == null || firstList.length == 0) return firstList;
        int i = 0, j = 0;
        while(i < firstList.length && j < secondList.length){
            int first_start = firstList[i][0];
            int second_start = secondList[j][0];
            int first_end = firstList[i][1];
            int second_end = secondList[j][1];
            int start = Math.max(first_start, second_start);
            int end = Math.min(first_end, second_end);
            if(start <= end) res.add(new int[]{start, end});
            if(firstList[i][1] < secondList[j][1]) i++;
            else j++;
        }
        return res.toArray(new int[res.size()][]);
    }

    // LeetCode 435. Non-overlapping Intervals
    public boolean isOverlapping(int[] i, int[] j) {
        return i[1] > j[0];
      }
      public int eraseOverlapIntervals(int[][] intervals) {
          if (intervals.length == 0) return 0;
          Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
          LinkedList<int[]> res = new LinkedList<>();
          res.add(intervals[0]);
          for (int i = 1; i < intervals.length; i++) {
              int[] cur = intervals[i];
              int[] last = res.getLast();
              // if overlaps
              if(last[1] > cur[0]){
                  last[1] = Math.min(last[1], cur[1]);
                  res.removeLast();
                  res.addLast(last);
              } else {
                  res.addLast(cur);
              }
          }
        return intervals.length - res.size();
      }
}
