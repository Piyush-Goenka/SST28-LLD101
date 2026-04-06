package distributed_cache;

public interface Database {
    String read(String key);
    void write(String key, String value);
}