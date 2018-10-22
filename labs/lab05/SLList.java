import java.lang.reflect.Array;

/**
  * An SLList is a list of integers, which hides the terrible truth of the
  * nakedness within.
  */
public class SLList {
    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }

        @Override
        public String toString() {
            return "IntNode{" +
                    "item=" + item +
                    ", next=" + next +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            IntNode intNode = (IntNode) o;

            if (item != intNode.item) return false;
            return next != null ? next.equals(intNode.next) : intNode.next == null;
        }

    }

    /* The first item (if it exists) is at sentinel.next. */
    private IntNode sentinel;
    private int size;

    /** Creates an empty SLList. */
    public SLList() {
        sentinel = new IntNode(63, null);
        size = 0;
    }

    public SLList(int x) {
        sentinel = new IntNode(63, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    /** Returns an SLList consisting of the given values. */
    public static SLList of(int... values) {
        SLList list = new SLList();
        for (int i = values.length - 1; i >= 0; i -= 1) {
            list.addFirst(values[i]);
        }
        return list;
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    /** Adds x to the front of the list. */
    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

     @Override
     public String toString() {
         return "SLList{" +
                 "sentinel=" + sentinel +
                 ", size=" + size +
                 '}';
     }

     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;

         SLList slList = (SLList) o;

         if (size != slList.size) return false;
         return sentinel != null ? sentinel.equals(slList.sentinel) : slList.sentinel == null;
     }


     /** Returns the first item in the list. */
    public int getFirst() {
        return sentinel.next.item;
    }

    /** Return the value at the given index. */
    public int get(int index) {
        IntNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    /** Adds x to the end of the list. */
    public void addLast(int x) {
        size += 1;
        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    /** Adds x to the list at the specified index. */
    public void add(int index, int x) {
        IntNode p = sentinel;
        while (index > 0 && p.next != null) {
            p = p.next;
            index -= 1;
        }
        IntNode res = p.next;
        p.next = new IntNode(x, null);
        p = p.next;
        p.next = res;
        size += 1;
    }

    /** Returns the reverse of this list. This method is destructive. */
    public void reverse() {
        if (size <= 1) {
            return ;
        } else {
            IntNode p = sentinel.next;
            int[] a = new int[size];
            int i = 0;
            while (p != null) {
                a[i] = p.item;
                p = p.next;
                i += 1;
            }
            p = sentinel;
            for (int j = a.length - 1; j >= 0; j--) {
                p.next = new IntNode(a[j], null);
                p = p.next;
            }
        }
    }
}
