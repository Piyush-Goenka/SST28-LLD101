public interface RateLimitingStrategy {
    boolean isAllowed(String key, RateLimitPolicy policy);
}