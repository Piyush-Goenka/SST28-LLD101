package distributed_cache;

import java.util.List;

public class ModuloDistributionStrategy implements DistributionStrategy {
    @Override
    public CacheNode getNode(List<CacheNode> nodes, String key) {
        if (nodes == null || nodes.isEmpty()) {
            throw new IllegalArgumentException("Nodes list cannot be empty");
        }
        int hash = Math.abs(key.hashCode());
        int index = hash % nodes.size();
        return nodes.get(index);
    }
}
