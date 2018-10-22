 /**
  * @author Alfred
  * A DLList is a list of integers. Like SLList, it also hides the terrible
  * truth of the nakedness within, but with a few additional optimizations.
  */
public class DLList<BleepBlorp> {
    private class Node {
        public Node prev;
        public BleepBlorp item;
        public Node next;

        public Node(BleepBlorp i, Node p, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private Node sentinel;
    private int size;

    /** Creates an empty DLList. */
    public DLList() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

     /**
      * @param values ** values to put in the list
      * @param <BleepBlorp> ** a data type
      * @return ** a list consisting of the values
      */
    public static <BleepBlorp> DLList of(BleepBlorp... values) {
        DLList<BleepBlorp> list = new DLList<>();
        for (BleepBlorp value : values) {
            list.addLast(value);
        }
        return list;
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    /** Adds item to the front of the list. */
    public void addFirst(BleepBlorp item) {
        Node n = new Node(item, sentinel, sentinel.next);
        n.next.prev = n;
        n.prev.next = n;
        size += 1;
    }

    /** Adds item to the back of the list. */
    public void addLast(BleepBlorp item) {
        Node n = new Node(item, sentinel.prev, sentinel);
        n.next.prev = n;
        n.prev.next = n;
        size += 1;
    }

    /** Adds item to the list at the specified index. */
    public void add(int index, BleepBlorp item) {
        /** Node n stands for the node which you will add to its next */
        Node n = sentinel;
        while (index > 0 && n.next != sentinel) {
            n = n.next;
            index -= 1;
        }
        Node temp = new Node(item, n, n.next);
        temp.next.prev = temp;
        temp.prev.next = temp;
        size += 1;
    }

    /** Remove the first instance of item from this list. */
    public void remove(BleepBlorp item) {
        Node n = sentinel;
        while (n.item != item) {
            n = n.next;
        }
        n.prev.next = n.next;
        n.next.prev = n.prev;
        size -= 1;
    }

    @Override
    public String toString() {
        String result = "";
        Node p = sentinel.next;
        boolean first = true;
        while (p != sentinel) {
            if (first) {
                result += p.item.toString();
                first = false;
            } else {
                result += " " + p.item.toString();
            }
            p = p.next;
        }
        return result;
    }

    /** Returns whether this and the given list or object are equal. */
    public boolean equals(Object o) {
        DLList other = (DLList) o;
        if (size() != other.size()) {
            return false;
        }
        Node op = other.sentinel.next;
        for (Node p = sentinel.next; p != sentinel; p = p.next) {
            if (!(p.item.equals(op.item))) {
                return false;
            }
            op = op.next;
        }
        return true;
    }
}
