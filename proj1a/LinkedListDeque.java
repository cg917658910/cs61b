public class LinkedListDeque<T> {

    private int size;

    private Node sentinel;

    private class Node {

        public T item;
        public Node prev;
        public Node next;

        public Node(T i, Node p, Node n) {
            item = i;
            prev = n;
            next = n;
        }

    }

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        size++;
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
    }

    public void addLast(T item) {
        size++;
        Node newNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
    }

    public boolean isEmpty() {

        return size == 0;
    }

    public int size() {

        return size;
    }

    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        Node node = sentinel.next;
        while (node != sentinel) {
            System.out.print(node.item + " ");
            node = node.next;
        }

    }

    public T removeFirst() {

        if (isEmpty()) {
            return null;
        }
        size--;
        Node first = sentinel.next;
        sentinel.next = first.next;
        sentinel.next.prev = first.prev;

        return first.item;
    }

    public T removeLast() {

        if (isEmpty()) {
            return null;
        }
        size--;
        Node last = sentinel.prev;
        sentinel.prev = last.prev;
        sentinel.prev.next = sentinel;

        return last.item;
    }

    public T get(int index) {

        if (index > size - 1) {
            return null;
        }
        Node node = sentinel.next;
        while (index > 0) {
            node = node.next;
            index--;
        }

        return node.item;
    }

    private T getRecursive(Node node, int index) {

        if (index == 0) {
            return node.item;
        }

        return getRecursive(node.next, index - 1);
    }

    public T getRecursive(int index) {

        if (index > size - 1) {
            return null;
        }

        return getRecursive(sentinel.next, index);
    }
}
