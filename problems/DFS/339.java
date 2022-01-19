class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    private int dfs(List<NestedInteger> nestedList, int depth) {
        int res = 0;
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger())
                res += depth * ni.getInteger();
            else
                res += dfs(ni.getList(), depth + 1);
        }
        return res;
    }
}