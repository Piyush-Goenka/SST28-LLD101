package distributed_cache;

public interface EvictionPolicy<K> {
    void keyAccessed(K key);
    K evictKey();
}
