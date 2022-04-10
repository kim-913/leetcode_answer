class Solution {
    public int minProductSum(int[] nums1, int[] nums2) {
        Arrays.sort(nums2);
        Arrays.sort(nums1);
        int res = 0;
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            res += nums1[i] * nums2[n - i - 1];
        }
        return res;
    }
}