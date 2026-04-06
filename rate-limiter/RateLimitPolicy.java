public class RateLimitPolicy {
    private final int maxRequests;
    private final long timeWindowMillis;

    public RateLimitPolicy(int maxRequests, long timeWindowMillis) {
        this.maxRequests = maxRequests;
        this.timeWindowMillis = timeWindowMillis;
    }

    public int getMaxRequests() { return maxRequests; }
    public long getTimeWindowMillis() { return timeWindowMillis; }
}