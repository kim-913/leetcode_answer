package problems;
class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if(firstList == null && secondList == null) return firstList;
        if(firstList == null || secondList == null) return firstList == null ? firstList : secondList;
        List<int[]> res = new ArrayList<>();
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
}