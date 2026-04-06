public class RateLimiter {
    private RateLimitingStrategy strategy;

    public RateLimiter(RateLimitingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(RateLimitingStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean allowRequest(String key, RateLimitPolicy policy) {
        return strategy.isAllowed(key, policy);
    }
}