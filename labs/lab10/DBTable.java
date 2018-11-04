import edu.princeton.cs.algs4.Whitelist;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.io.IOError;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DBTable<T> {
    private List<T> entries;

    public DBTable() {
        this.entries = new ArrayList<>();
    }

    public DBTable(Collection<T> lst) {
        entries = new ArrayList<>(lst);
    }

    public void add(T t) {
        entries.add(t);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DBTable<?> other = (DBTable<?>) o;
        return Objects.equals(entries, other.entries);
    }

    /** Add all items from a collection to the table. */
    public void add(Collection<T> col) {
        col.forEach(this::add);
    }

    /** Returns a copy of the entries in this table. */
    List<T> getEntries() {
        return new ArrayList<>(entries);
    }

    /**
     * Returns a list of entries sorted based on the natural ordering of the
     * results of the getter. Non-destructive.
     */
    public <R extends Comparable<R>> List<T> getOrderedBy(Function<T, R> getter) {
        List<T> newList = new ArrayList<>(entries);
        newList.sort(new Comparator<T>() {
            @Override
            public int compare(T t, T t1) {
                return getter.apply(t).compareTo(getter.apply(t1));
            }
        });
        return newList;
    }

    /**
     * Returns a list of entries whose value returned from the getter is found
     * in the whitelist. Non-destructive.
     */
    public <R> List<T> getWhitelisted(Function<T, R> getter, Collection<R> whitelist) {
        List<T> newList =  entries.stream()
                .filter(s -> whitelist.contains(getter.apply(s)))
                .collect(Collectors.toList());
        return newList;
    }

    /**
     * Returns a new DBTable that contains the elements as obtained by the
     * getter. For example, getting a DBTable of usernames would look like:
     * DBTable<String> names = table.getSubtableOf(User::getUsername);
     */
    public <R>DBTable<R> getSubtableOf(Function<T, R> getter) {
        return new DBTable<R>(entries.stream()
                            .map(s -> getter.apply(s))
                            .collect(Collectors.toList()));
//        List<R>  newList = entries.stream()
//                .map(s -> getter.apply(s))
//                .collect(Collectors.toList());
//        DBTable newTable = new DBTable(newList);
//        return newTable;
    }

    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User(2, "Christine", ""),
                new User(4, "Kevin", ""),
                new User(5, "Alex", ""),
                new User(1, "Lauren", ""),
                new User(1, "Catherine", "")
                );
        DBTable<User> t = new DBTable<>(users);
        List<User> l = t.getOrderedBy(User::getName);
        DBTable<String> name = t.getSubtableOf(User::getName);
        l.forEach(System.out::println);
        for (String temp: name.entries) {
            System.out.println(temp);
        }
        List<Integer> friend = Arrays.asList(1);
        List<User> id = t.getWhitelisted(User::getId, friend);
        id.forEach(System.out::println);
    }
}
