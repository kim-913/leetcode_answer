import java.util.*;
public class solution_8_11 {
    
    public static void main(String[] args) {
        
    }

    // LeetCode 621. Task Scheduler
    public int leastInterval(char[] tasks, int n) {
        int res = 0;
        if(tasks.length <= 1) return tasks.length;
        //sort the list according to frequency of each character
        int[] frequencies = new int[26];
        for(char c: tasks){
            frequencies[c - 'A']++;
        }
        Arrays.sort(frequencies);
        // get the char with the max frequency
        int f_max = frequencies[25];
        // greedy
        int idle = (f_max - 1) * n;
        // add to idle slot
        for(int i = frequencies.length-2; i >= 0 && idle > 0; i--){
            idle -= Math.min(f_max - 1, frequencies[i]);
        }
        idle = Math.max(0, idle);
        return idle + tasks.length;
    }

    // LeetCode 452. Minimum Number of Arrows to Burst Balloons
    public int findMinArrowShots(int[][] points) {
        if(points.length == 0) return 0;
        // sort by end point
        Arrays.sort(points, (a,b) -> {
                   if (a[1] - b[1] == 0) return 0;
                   if(a[1] < b[1]) return -1;
                   return 1;
            });
        int res = 1;
        int start, end, firstEnd = points[0][1];
        for(int[] point: points){
            start = point[0];
            end = point[1];
            if(firstEnd < start) {
                res++;
                firstEnd = end;
            }
        }
        return res;
    }

    // LeetCode 57. Insert Interval
    public int[][] insert(int[][] intervals, int[] newInterval) {
        //
        int i = 0;
        List<int[]> res = new LinkedList<>();
        while(i < intervals.length && intervals[i][1] < newInterval[0]){
            res.add(intervals[i++]);
        }
        while(i < intervals.length && intervals[i][0] <= newInterval[1]){
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res.add(newInterval);
        while (i < intervals.length) res.add(intervals[i++]); 
        int[][] result = new int[res.size()][2];
        int j = 0;
        for(int[] list: res) {
            result[j] = list;
            j++;
        }
        return result;
    }
}
