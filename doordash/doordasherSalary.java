package doordash;

// Question: https://www.hack2hire.com/by-company/content/67831497d0faf7ec64cc3ad5?company=DOORDASH&type=ALGORITHM
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int calculateSalary(List<List<String>> records, List<List<Integer>> doublePayPeriods, int salaryRate) {
        // TODO: Implement calculateSalary logic

        Map<String, Order> map = new HashMap<>();
        int res = 0;
        for (List<String> record : records) {
            String orderKey = record.get(0);
            int timeStamp = Integer.parseInt(record.get(1));
            String status = record.get(2);
            if (!map.containsKey(orderKey)) {
                map.put(orderKey, new Order(timeStamp, status));
            } else {
                Order startOrder = map.get(orderKey);
                int startTime = startOrder.timeStamp;
                if (status.equals("CANCELED"))
                    res += 5;
                else if (status.equals("DELIVERED")) {
                    int endTime = timeStamp;
                    int totalTime = endTime - startTime;
                    int doubleTime = 0;
                    for (List<Integer> period : doublePayPeriods) {
                        int overlapStart = Math.max(startTime, period.get(0));
                        int overlapEnd = Math.min(endTime, period.get(1));
                        doubleTime += Math.max(0, overlapEnd - overlapStart);
                    }
                    int normalTime = totalTime - doubleTime;
                    res += (normalTime * salaryRate) + (doubleTime * salaryRate * 2);
                }
            }
        }
        return res;
    }
}

class Order {
    int timeStamp;
    String status;

    public Order(int timeStamp, String status) {
        this.timeStamp = timeStamp;
        this.status = status;
    }
}
