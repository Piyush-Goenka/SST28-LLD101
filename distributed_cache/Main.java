package distributed_cache;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Database db = new MockDatabase();
        
        CacheNode node1 = new CacheNode("Node_A", 2, new LRUEvictionPolicy<>());
        CacheNode node2 = new CacheNode("Node_B", 2, new LRUEvictionPolicy<>());
        CacheNode node3 = new CacheNode("Node_C", 2, new LRUEvictionPolicy<>());

        List<CacheNode> nodes = Arrays.asList(node1, node2, node3);
        
        DistributionStrategy distribution = new ModuloDistributionStrategy();
        DistributedCache cache = new DistributedCache(nodes, distribution, db);

        cache.put("user_1", "Alice");
        cache.put("user_2", "Bob");
        cache.put("user_3", "Charlie");

        cache.get("user_1"); 
        cache.get("user_99"); 

        cache.put("user_4", "David");
        cache.put("user_5", "Eve");
        cache.put("user_6", "Frank"); 
        
        cache.get("user_1");
    }
}
