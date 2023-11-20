import org.w3c.dom.Node;

public class ArrayDeque<T> {

    private T[] nodes;

    private int size, capacity;

    private int nextFirst, nextLast;

    private static int RFACTOR = 2;

    private static int START = 0;

    public ArrayDeque() {

        nodes = (T[]) new Object[8];
        size = 0;
        capacity = 8;
        nextLast = START;
        nextFirst = safeMinus(nextLast, 1, capacity);

    }

    private int safePlus(int i, int n, int cap) {

        return (i + n) % cap;

    }

    private int safeMinus(int i, int n, int cap) {

        if (i < n)
            return cap - (n - i);

        return i - n;
    }

    private void resize(int cap) {

        if (isEmpty()) {
            return;
        }

        T[] newNodes = (T[]) new Object[cap];

        int index = safePlus(nextFirst, 1, this.capacity);
        int newIndex = START;
        int n = size;
        while (n > 0) {
            newNodes[newIndex] = nodes[index];
            index = safePlus(index, 1, this.capacity);
            newIndex = safePlus(newIndex, 1, cap);
            n--;
        }

        nextLast = size;
        this.capacity = cap;
        nextFirst = this.capacity - 1;
        nodes = newNodes;
    }

    public void addFirst(T item) {

        if (size == capacity) {
            resize(capacity * RFACTOR);
        }
        nodes[nextFirst] = item;
        nextFirst = safeMinus(nextFirst, 1, capacity);
        size++;
    }

    public void addLast(T item) {
        if (size == capacity) {
            resize(capacity * RFACTOR);
        }
        nodes[nextLast] = item;
        nextLast = safePlus(nextLast, 1, capacity);
        size++;
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

        int index = safePlus(nextFirst, 1, capacity);
        int n = size;
        while (n > 0) {
            System.out.print(nodes[index] + " ");
            index = safePlus(index, 1, capacity);
            n--;
        }

    }

    public T removeFirst() {

        if (isEmpty()) {
            return null;
        }

        int firstIndex = safePlus(nextFirst, 1, capacity);
        T first = nodes[firstIndex];
        nodes[firstIndex] = null;
        nextFirst = firstIndex;
        size--;
        if (capacity > 16 && capacity / size >= 4) {
            resize(capacity / RFACTOR);
        }

        return first;
    }

    public T removeLast() {

        if (isEmpty()) {
            return null;
        }

        int lastIndex = safeMinus(nextLast, 1, capacity);
        T last = nodes[lastIndex];
        nodes[lastIndex] = null;
        nextLast = lastIndex;
        size--;

        if (capacity > 16 && capacity / size >= 4) {
            resize(capacity / RFACTOR);
        }

        return last;
    }

    public T get(int i) {

        if (i >= size) {
            return null;
        }
        int index = safePlus(nextFirst, i + 1, capacity);

        return nodes[index];
    }
}
