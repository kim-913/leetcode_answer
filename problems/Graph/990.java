class Solution {
    private int[] parent;

    class UnionFind {
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int parentX = parent[x];
            int parentY = parent[y];
            parent[parentX] = parentY;
        }
    }

    public boolean equationsPossible(String[] equations) {
        UnionFind uf = new UnionFind(26);
        for (String equation : equations) {
            int char1 = equation.charAt(0) - 'a';
            int char2 = equation.charAt(3) - 'a';
            if (equation.charAt(1) == '=') {
                int parent1 = uf.find(char1);
                int parent2 = uf.find(char2);
                if (parent1 != parent2) {
                    uf.union(parent1, parent2);
                }
            }
        }
        for (String equation : equations) {
            int char1 = equation.charAt(0) - 'a';
            int char2 = equation.charAt(3) - 'a';
            if (equation.charAt(1) == '!') {
                int parent1 = uf.find(char1);
                int parent2 = uf.find(char2);
                if (parent1 == parent2)
                    return false;
            }
        }
        return true;
    }

    // lc 31. Next Permutation
    class Solution {
        public void nextPermutation(int[] nums) {
            // start from last but one, find a pair where prev element < latter
            int i = nums.length - 2;
            while (i >= 0 && nums[i] >= nums[i + 1]) {
                i--;
            }
            if (i >= 0) {
                int j = nums.length - 1;
                // 找到 后面比前面大的最小数字，然后进行swap
                // swap完之后将当前位置往后降序排列
                while (j >= 0 && nums[i] >= nums[j]) {
                    j--;
                }
                swap(nums, i, j);
            }
            reverse(nums, i + 1);
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        public void reverse(int[] nums, int start) {
            int left = start, right = nums.length - 1;
            while (left < right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
    }
}