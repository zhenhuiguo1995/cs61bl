import java.security.Key;
import java.util.*;

public class CodingChallenges {

    /**
     * Return the missing number from an array of length N - 1 containing all
     * the values from 0 to N except for one missing number.
     */
    public static int missingNumber(int[] values) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < values.length + 1; i ++) {
            set.add(i);
        }
        for (int s: values){
            set.remove(s);
        }
        List<Integer> s = new ArrayList<>(set);
        return s.get(0);
    }

    /** Returns true if and only if two integers in the array sum up to n. */
    public static boolean sumTo(int[] values, int n) {
        Set<Integer> set = new HashSet<>();
        for (int i: values) {
            set.add(n - i);
        }
        for (int i: values) {
            if (set.contains(i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if and only if s1 is a permutation of s2. s1 is a
     * permutation of s2 if it has the same number of each character as s2.
     */
    public static boolean isPermutation(String s1, String s2) {
        Map<Character, Integer> map1 = new HashMap<>();
        for (char c : s1.toCharArray()) {
            if (map1.containsKey(c)) {
                map1.put(c, map1.get(c) + 1);
            } else {
                map1.put(c, 1);
            }
        }
        Map<Character, Integer> map2 = new HashMap<>();
        for (char c2: s2.toCharArray()) {
            if (map2.containsKey(c2)) {
                map2.put(c2, map2.get(c2) + 1);
            } else {
                map2.put(c2, 1);
            }
        }

        for (char key: map1.keySet()) {
            if (!map2.containsKey(key) || map2.containsKey(key) && map1.get(key) != map2.get(key)) {
                return false;
            }
        }
        return true;
    }
}
