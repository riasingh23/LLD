package lru.cache;

public class DoublyLinkedList<K,V> {
    private Node<K,V> head;
    private Node<K,V> tail;

    public DoublyLinkedList() {
        this.head = new Node<>(null,null);
        this.tail = new Node<>(null,null);
        head.next = tail;
        tail.prev = head;
    }

    public Node<K,V> addNodeToFront(Node<K,V> node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
        return node;
    }

    public Node<K,V> removeNode(Node<K,V> node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        node.next = null;
        node.prev = null;
        return node;
    }

    public Node<K,V> removeLastNode() {
        Node<K,V> node = tail.prev;
        removeNode(node);
        return node;
    }



}
