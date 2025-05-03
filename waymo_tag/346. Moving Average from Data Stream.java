import java.util.LinkedList;
import java.util.Queue;

/*
 * 
 * follow up: If too large?
 
 If k is small, I’ll store the last k values in a circular buffer (O(k) memory).
If k is large or memory is tight, I’ll switch to Exponential Moving Average to avoid storing history (O(1) memory, but approximate).
For backend services, I can persist values to disk (RocksDB) with TTL or rolling key window.
If streaming data in production scale, I’ll push this to Flink or Kafka Streams to handle stateful aggregation across machines.
 */
class MovingAverage {
    private Queue<Integer> q;
    private int size;
    private double sum;

    public MovingAverage(int size) {
        this.q = new LinkedList<>();
        this.size = size;
        this.sum = 0;
    }

    public double next(int val) {
        q.offer(val);
        sum += val;
        if (q.size() > size) {
            sum -= q.poll();
        }
        return sum / q.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */