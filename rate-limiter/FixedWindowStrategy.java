import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowStrategy implements RateLimitingStrategy {
    
    private static class WindowState {
        long windowStartTime;
        AtomicInteger counter;

        WindowState(long windowStartTime) {
            this.windowStartTime = windowStartTime;
            this.counter = new AtomicInteger(0);
        }
    }

    private final ConcurrentHashMap<String, WindowState> store = new ConcurrentHashMap<>();

    @Override
    public boolean isAllowed(String key, RateLimitPolicy policy) {
        long currentTime = System.currentTimeMillis();
        long currentWindowStart = (currentTime / policy.getTimeWindowMillis()) * policy.getTimeWindowMillis();

        WindowState state = store.compute(key, (k, existingState) -> {
            if (existingState == null || existingState.windowStartTime != currentWindowStart) {
                return new WindowState(currentWindowStart);
            }
            return existingState;
        });

        if (state.counter.incrementAndGet() <= policy.getMaxRequests()) {
            return true;
        }
        return false;
    }
}