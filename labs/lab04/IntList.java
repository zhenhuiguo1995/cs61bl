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

    /**
     * Append a value at the end of the list.
     * @param value ** an int variable to be appended
     */
    public void add(int value) {
        IntList p = this;
        while (p.rest != null) {
            p = p.rest;
        }
        p.rest = new IntList(value, null);
    }

    /**
     * Find the smallest value in the list.
     * @return ** the smallest value in the list.
     */
    public int smallest() {
        int smallest = this.first;
        IntList p = this;
        while (p != null) {
            if (p.first < smallest) {
                smallest = p.first;
            }
            p = p.rest;
        }
        return smallest;
    }

    /**
     * @return ** The sum of the square of every node.
     */
    public int squaredSum() {
        int sum = 0;
        IntList p = this;
        while (p != null) {
            sum += p.first * p.first;
            p = p.rest;
        }
        return sum;
    }

    /**
     * Iterate over a list and change the value of nodes.
     * @param L ** The IntList to iterate over.
     */
    public static void dSquareList(IntList L) {
        while (L != null) {
            L.first = L.first * L.first;
            L = L.rest;
        }
    }

    /**
     * Non-destructive method.
     * @param L ** the list to iterate over.
     * @return ** New list where every element has been squared.
     */
    public static IntList squareList(IntList L) {
        if (L == null) {
            return null;
        }
        IntList res = new IntList(L.first * L.first, null);
        IntList ptr = res;
        L = L.rest;
        while (L != null) {
            ptr.rest = new IntList(L.first * L.first, null);
            L = L.rest;
            ptr = ptr.rest;
        }
        return res;
    }

    /**
     * Destructively catenate List A and B.
     * @param A ** An IntList object
     * @param B ** An IntList object
     * @return ** a modified Int list object A
     */
    public static IntList dcatenate(IntList A, IntList B) {
        IntList p = A;
        while (p.rest != null) {
            p = p.rest;
        }
        while (B != null) {
            p.rest = B;
            p = p.rest;
            B = B.rest;
        }
        return A;
    }

    /**
     * Non-destructively catenate Int list A and B.
     * @param A ** a list object
     * @param B ** another list object
     * @return ** a new list object
     */
    public static IntList catenate(IntList A, IntList B) {
        IntList p = new IntList(A.first, null);
        IntList res = p;
        while (A.rest != null) {
            p.rest = A.rest;
            p = p.rest;
            A = A.rest;
        }
        while (B != null) {
            p.rest = B;
            p = p.rest;
            B = B.rest;
        }
        return res;
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
