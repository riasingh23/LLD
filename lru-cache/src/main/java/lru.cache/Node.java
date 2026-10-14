package lru.cache;

public class Node<K, V> {
    private K key;
    private V value;
    public Node<K, V> next;
    public Node<K, V> prev;
    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public K getKey() {
        return key;
    }
    public V getValue() {
        return value;
    }
    public Node<K, V> getNext() {
        return next;
    }
    public Node<K, V> getPrev() {
        return prev;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
