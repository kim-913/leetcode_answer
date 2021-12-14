/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int celebrity = 0;
        for (int i = 1; i < n; i++) {
            if (knows(celebrity, i))
                celebrity = i;
        }
        for (int i = 0; i < n; i++) {
            if (i != celebrity && knows(celebrity, i))
                return -1;
            if (i != celebrity && !knows(i, celebrity))
                return -1;
        }
        return celebrity;
    }
}