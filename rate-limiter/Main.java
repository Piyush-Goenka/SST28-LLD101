public class Main {
    public static void main(String[] args) throws InterruptedException {
        RateLimitPolicy policy = new RateLimitPolicy(3, 1000);
        
        RateLimiter rateLimiter = new RateLimiter(new FixedWindowStrategy());
        ExternalServiceCaller service = new ExternalServiceCaller(rateLimiter, policy);

        String tenant = "Tenant_A";

        System.out.println("--- Testing Fixed Window ---");
        service.executeTask(tenant, true); 
        service.executeTask(tenant, true); 
        service.executeTask(tenant, false); 
        service.executeTask(tenant, true); 
        service.executeTask(tenant, true); 

        Thread.sleep(1100);
        
        service.executeTask(tenant, true); 

        System.out.println("\n--- Switching to Sliding Window ---");
        
        rateLimiter.setStrategy(new SlidingWindowStrategy());
        
        service.executeTask(tenant, true); 
        service.executeTask(tenant, true); 
        service.executeTask(tenant, true); 
        service.executeTask(tenant, true); 
    }
}