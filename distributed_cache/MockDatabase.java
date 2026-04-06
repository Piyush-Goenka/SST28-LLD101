package distributed_cache;

import java.util.HashMap;
import java.util.Map;

public class MockDatabase implements Database {
    private Map<String, String> data = new HashMap<>();

    @Override
    public String read(String key) {
        System.out.println("Database Read: " + key);
        return data.get(key);
    }

    @Override
    public void write(String key, String value) {
        System.out.println("Database Write: " + key + " = " + value);
        data.put(key, value);
    }
}