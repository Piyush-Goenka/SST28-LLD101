package distributed_cache;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {

    private class Node {
        K key;
        Node prev;
        Node next;

        Node(K key) {
            this.key = key;
        }
    }

    private Map<K, Node> map;
    private Node head;
    private Node tail;

    public LRUEvictionPolicy() {
        this.map = new HashMap<>();
        this.head = new Node(null);
        this.tail = new Node(null);
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public void keyAccessed(K key) {
        if (map.containsKey(key)) {
            removeNode(map.get(key));
        }
        Node newNode = new Node(key);
        addLast(newNode);
        map.put(key, newNode);
    }

    @Override
    public K evictKey() {
        if (head.next == tail) {
            return null;
        }
        Node lruNode = head.next;
        removeNode(lruNode);
        map.remove(lruNode.key);
        return lruNode.key;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addLast(Node node) {
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }
}
