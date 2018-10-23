/**
 * @author Alfred
 * Creates a double-ended queue data strutcure
 */
public class LinkedListDeque<T> {
    private class Node<T>{
        public Node prev;
        public T item;
        public Node next;

        /**
         * Constructs a node object.
         * @param p ** The previous node
         * @param i ** the item to be added
         * @param n ** the next node
         */
        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    /* The first item (if it exists), is at sentinel.next. */
    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        Node p = new Node(sentinel, item, sentinel.next);
        sentinel.next = p;
        p.next.prev = p;
        size += 1;
    }

    public void addLast(T item) {
        Node p = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = p;
        sentinel.prev = p;
        size += 1;
    }

    public boolean isEmpty() {
        if (sentinel.next.item == sentinel.item) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel.next;
        int index = size;
        while (index > 0) {
            System.out.println(p.item + " ");
            p = p.next;
            index -= 1;
        }
    }

    public T removeFirst() {
        if (sentinel.next == null && sentinel.prev == null) {
            return null;
        } else {
            Node p = sentinel.next;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return (T) p.item;
        }
    }

    public T removeLast() {
        if (sentinel.prev == null) {
            return null;
        } else {
            Node p = sentinel.prev;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            return (T) p.item;
        }
    }

    public T get(int index) {
        Node p = sentinel.next;
        while (index > 0 && p != null) {
            p = p.next;
            index -= 1;
        }
        return (T) p.item;
    }
}
