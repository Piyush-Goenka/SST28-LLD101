package distributed_cache;

import java.util.List;

public interface DistributionStrategy {
    CacheNode getNode(List<CacheNode> nodes, String key);
}
