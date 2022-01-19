class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>(nestedList);
        int levelWeight = 0; // each time an element is encountered, add the value
        int overAllWeight = 0; // as depth increases, this variable will add the previous depth elements AGAIN
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger obj = queue.poll();
                if (obj.isInteger()) {
                    levelWeight += obj.getInteger(); // do not make it ZERO (0)
                } else {
                    queue.addAll(obj.getList());
                }
            }

            overAllWeight += levelWeight;
        }

        return overAllWeight;
    }
}