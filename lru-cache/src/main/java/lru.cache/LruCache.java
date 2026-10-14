package lru.cache;

import java.util.HashMap;
import java.util.Map;

public class LruCache<K,V> {
    private final int capacity;
    private int size;
    private final Map<K, Node<K,V>> cacheMap;
    private final DoublyLinkedList<K, V> doublyLinkedList;

    public LruCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("size needs to be greater than 0");
        }
        this.capacity = capacity;
        cacheMap = new HashMap<>();
        doublyLinkedList = new DoublyLinkedList<>();
    }

    public V get(K key) {
        if(!cacheMap.containsKey(key)){
            return null;
        }
        Node<K,V> node = cacheMap.get(key);
        doublyLinkedList.removeNode(node);
        doublyLinkedList.addNodeToFront(node);
        return node.getValue();
    }

    public K put(K key, V value) {
        if(cacheMap.containsKey(key)){
            Node<K,V> node = cacheMap.get(key);
            node.setValue(value);
            doublyLinkedList.removeNode(node);
            doublyLinkedList.addNodeToFront(node);
            return node.getKey();
        }
        if(size == capacity){
            Node<K,V> lastNode = doublyLinkedList.removeLastNode();
            cacheMap.remove(lastNode.getKey());
            Node<K,V> node = new Node<>(key,value);
            doublyLinkedList.addNodeToFront(node);
            cacheMap.put(key,node);
            return node.getKey();
        }
        Node<K,V> node = new Node<>(key,value);
        doublyLinkedList.addNodeToFront(node);
        cacheMap.put(key,node);
        size++;
        return node.getKey();
    }

}
