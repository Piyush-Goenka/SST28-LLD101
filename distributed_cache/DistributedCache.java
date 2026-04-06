package distributed_cache;

import java.util.List;

public class DistributedCache {
    private List<CacheNode> nodes;
    private DistributionStrategy distributionStrategy;
    private Database database;

    public DistributedCache(List<CacheNode> nodes, DistributionStrategy distributionStrategy, Database database) {
        this.nodes = nodes;
        this.distributionStrategy = distributionStrategy;
        this.database = database;
    }

    public String get(String key) {
        CacheNode targetNode = distributionStrategy.getNode(nodes, key);
        String value = targetNode.get(key);

        if (value != null) {
            System.out.println("Cache Hit in " + targetNode.getId() + " for key: " + key);
            return value;
        }

        System.out.println("Cache Miss in " + targetNode.getId() + " for key: " + key);
        value = database.read(key);

        if (value != null) {
            targetNode.put(key, value);
        }

        return value;
    }

    public void put(String key, String value) {
        database.write(key, value);
        CacheNode targetNode = distributionStrategy.getNode(nodes, key);
        targetNode.put(key, value);
        System.out.println("Stored key: " + key + " in " + targetNode.getId());
    }
}
