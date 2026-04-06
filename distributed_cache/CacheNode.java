package distributed_cache;

import java.util.HashMap;
import java.util.Map;

public class CacheNode {
    private String id;
    private int capacity;
    private Map<String, String> storage;
    private EvictionPolicy<String> evictionPolicy;

    public CacheNode(String id, int capacity, EvictionPolicy<String> evictionPolicy) {
        this.id = id;
        this.capacity = capacity;
        this.storage = new HashMap<>();
        this.evictionPolicy = evictionPolicy;
    }

    public String getId() {
        return id;
    }

    public String get(String key) {
        if (storage.containsKey(key)) {
            evictionPolicy.keyAccessed(key);
            return storage.get(key);
        }
        return null;
    }

    public void put(String key, String value) {
        if (storage.containsKey(key)) {
            storage.put(key, value);
            evictionPolicy.keyAccessed(key);
            return;
        }

        if (storage.size() == capacity) {
            String evictedKey = evictionPolicy.evictKey();
            if (evictedKey != null) {
                storage.remove(evictedKey);
                System.out.println("Node " + id + " evicted key: " + evictedKey);
            }
        }

        storage.put(key, value);
        evictionPolicy.keyAccessed(key);
    }
}
