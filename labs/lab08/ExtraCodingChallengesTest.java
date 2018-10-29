import org.junit.Test;
import static org.junit.Assert.*;

public class ExtraCodingChallengesTest {
    CodingChallenges Chanllenge = new CodingChallenges();

    @Test
    public void testMissingNumber() {
        int[] a1 = new int[]{0, 1, 2, 3, 5};
        assertEquals(4, Chanllenge.missingNumber(a1));
        int[] a2 = new int[]{1, 2, 3, 4};
        assertEquals(0, Chanllenge.missingNumber(a2));
    }

    @Test
    public void testSumTo() {
        int[] a1 = new int[]{1, 3, 5, 8, 2, 6};
        assertEquals(true, Chanllenge.sumTo(a1, 4));
        assertEquals(false, Chanllenge.sumTo(a1, 20));
        assertEquals(true, Chanllenge.sumTo(a1, 8));
        assertEquals(true, Chanllenge.sumTo(a1, 14));
    }

    @Test
    public void testIsPermutation() {
        String s1 = "madam";
        String s2 = "adama";
        assertEquals(false, Chanllenge.isPermutation(s1, s2));
        String s3 = "aaaaaabbbbbdddd";
        String s4 = "aaaabbbbbdddddd";
        assertEquals(false, Chanllenge.isPermutation(s3, s4));
        String s5 = "eat";
        String s6 = "tea";
        assertEquals(true, Chanllenge.isPermutation(s5, s6));
    }

}