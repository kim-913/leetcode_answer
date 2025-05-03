package doordash;

import java.util.Arrays;

/*
n dashers and m restaurants, each restaurants has different number of orders, 
one dasher can only serve on resturants, what's the minimum order each dashser has to take?

Ex: 
orders = [10, 5, 8]  // 3 restaurants
n = 4                // 4 dashers
return 8
 */
public class dasherLoadBalancer {

    public int calcMinOrder(int[] restaurants, int dasherCnt) {
        int m = restaurants.length;
        if (dasherCnt < m)
            return -1;
        Arrays.sort(restaurants);
        int left = 1, right = restaurants[m - 1];
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isValid(restaurants, dasherCnt, mid))
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    private boolean isValid(int[] orders, int dasher, int load) {
        int dasherNeed = 0;
        for (int order : orders) {
            dasherNeed += (order + load - 1) / load;
            if (dasherNeed > dasher)
                return false;
        }
        return dasherNeed <= dasher;
    }
}
