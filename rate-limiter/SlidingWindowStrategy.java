import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowStrategy implements RateLimitingStrategy {

    private final ConcurrentHashMap<String, Queue<Long>> store = new ConcurrentHashMap<>();

    @Override
    public boolean isAllowed(String key, RateLimitPolicy policy) {
        long currentTime = System.currentTimeMillis();
        long windowStartTime = currentTime - policy.getTimeWindowMillis();

        Queue<Long> timestamps = store.computeIfAbsent(key, k -> new ConcurrentLinkedQueue<>());

        while (!timestamps.isEmpty() && timestamps.peek() <= windowStartTime) {
            timestamps.poll();
        }

        if (timestamps.size() < policy.getMaxRequests()) {
            timestamps.offer(currentTime);
            return true;
        }

        return false;
    }
}