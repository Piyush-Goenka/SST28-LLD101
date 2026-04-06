public class ExternalServiceCaller {
    private final RateLimiter rateLimiter;
    private final RateLimitPolicy policy;

    public ExternalServiceCaller(RateLimiter rateLimiter, RateLimitPolicy policy) {
        this.rateLimiter = rateLimiter;
        this.policy = policy;
    }

    public void executeTask(String tenantId, boolean requiresExternalCall) {
        System.out.println("\nProcessing request for " + tenantId);
        
        System.out.println("Running internal business logic...");

        if (!requiresExternalCall) {
            System.out.println("No external call required. Done.");
            return;
        }

        if (rateLimiter.allowRequest(tenantId, policy)) {
            System.out.println("✅ Rate limit check PASSED. Calling expensive external API...");
        } else {
            System.out.println("❌ Rate limit check FAILED. 429 Too Many Requests.");
        }
    }
}