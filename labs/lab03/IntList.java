/**
 * @author Alfred
 * A data structure to represent a Linked List of Integers.
 * Each IntList represents one node in the overall Linked List.
 */
public class IntList {
    /**The first element of the linked list.*/
    public int first;
    /**The second element of the linked list.*/
    public IntList rest;

    /**
     * A Linked list data structure.
     * @param f ** the first element.
     * @param r ** a referene type pointing to the next
     * element of the list.
     */
    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /** Returns an IntList consisting of the given values.
     * @param values ** an int variable.
     * */
    public static IntList of(int... values) {
        if (values.length == 0) {
            return null;
        }
        IntList p = new IntList(values[0], null);
        IntList front = p;
        for (int i = 1; i < values.length; i++) {
            p.rest = new IntList(values[i], null);
            p = p.rest;
        }
        return front;
    }

    /**@return ** returns the size of the list. */
    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + rest.size();
    }

    /** @return [position]th value in this list.
     * @param position ** the position of the item
     * */
    public int get(int position) {
        IntList p = this;
        if (position < 0) {
            System.out.println("List index must be a positive number ");
            return 0;
        }
        while (position > 0) {
            if (p.rest != null) {
                p = p.rest;
            } else {
                System.out.println("List index out of range!");
                return 0;
            }
            position -= 1;
        }
        return p.first;
    }

    /**
     * @return the string representation of the list. */
    public String toString() {
        IntList p = this;
        while (p != null) {
            System.out.print(p.first + " ");
            p = p.rest;
        }
        return null;
    }

    /**Return a bool value indicating this and the given list is equal
     * or not.
     * @param o ** an object
     * @return whether this and the given list or object are equal. */
    public boolean equals(Object o) {
        IntList other = (IntList) o;
        IntList p = this;
        while (p != null && other != null) {
            if (p.first != other.first) {
                return false;
            } else {
                p = p.rest;
                other = other.rest;
            }
        }
        return true;
    }

    /** this part is for testing purposes only
    public static void main(String[] args){
        IntList A = new IntList(10, null);
        A.rest = new IntList(2, null);
        A.rest.rest = new IntList(3, null);
        System.out.println( A.get(5));

        IntList B = new IntList(10, null);
        B.rest = new IntList(2, null);
        B.rest.rest = new IntList(3, null);
        B.rest.first = 8;
        System.out.println(B.rest.first + " " + A.rest.first);
        System.out.println( A.equals(B));
    }
    */
}
