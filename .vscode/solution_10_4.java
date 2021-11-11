/**
 * solution_10_4
 */
public class solution_10_4 {

    public static void main(String[] args) {

    }

    // LC 853 Car Fleet
    public int carFleet(int target, int[] position, int[] speed) {
        /*
         * // two ways of implementation // 1. new car class class Car { int index;
         * double arrivalTime; public Car(int i, double time){ this.index = i;
         * this.arrivalTime = time; } } // larger postion to smaller PriorityQueue<Car>
         * pq = new PriorityQueue<>((a, b) -> (position[b.index] - position[a.index]));
         * int n = position.length; if(n <= 0) return 0; for(int i = 0; i < n; i++){ //
         * calculate the cur time double time = (double) (target - position[i]) /
         * speed[i]; pq.offer(new Car(i, time)); } // if the prev position's time is
         * smaller than current position's time, meaning that the two cars will meet
         * each other. int res = 1; while(!pq.isEmpty()){ Car cur = pq.poll();
         * while(!pq.isEmpty() && pq.peek().arrivalTime <= cur.arrivalTime) pq.poll();
         * if(!pq.isEmpty()) res++; } return res;
         */

        // 2. we create an array that takes the position as index and the arrival time
        // as value
        int n = position.length;
        int[] speeds = new int[target];
        if (n <= 0)
            return 0;
        // assume each car is a fleet
        int res = n;
        // first, we need to initialize the speeds array
        for (int i = 0; i < n; i++) {
            speeds[position[i]] = speed[i];
        }
        // now, we need to check from the largest position that if the latter position
        // have arrival time smaller than it, the result need to decrease by 1.
        // create an offset value
        double slow = -1;
        for (int i = target - 1; i >= 0; i--) {
            // if there is no car in the current position
            if (speeds[i] == 0)
                continue;
            double time = (double) (target - i) / speeds[i];
            // meaning that we don't need to change the result, the current car is a fleet,
            // record its arrival time
            if (time > slow)
                slow = time;
            else
                res--;
        }
        return res;
    }
}