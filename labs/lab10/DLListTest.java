import org.junit.Test;
import static org.junit.Assert.*;

public class DLListTest {

    @Test
    public void testFor() {
        DLList<Integer> a = new DLList<>();
        a.addLast(1);
        a.addLast(2);
        a.addLast(3);
        a.addLast(4);
        int count = 0;
        for (Integer i : a) {
            count += i;
        }
        assertEquals(10, count);

        a.addLast(5);
        assertEquals(5, a.size());
        int total = 0;
        for (Integer i: a) {
            total += i;
        }
        assertEquals(15, total);


    }


}
