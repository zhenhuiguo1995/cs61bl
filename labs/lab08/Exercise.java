import java.util.*;

public class Exercise {
    public static List<String> getWord(String inputFileName) {
        List<String> lst = new ArrayList<String>();
        In in = new In();
        while (!in.isEmpty()) {
            lst.add(in.readString());
        }
        return lst;
    }

    public static int countUnique(List<String> words) {
        Set<String> ss = new HashSet<>();
        for (String s: words) {
            ss.add(s);
        }
        return ss.size();
    }

    /**
    public static Map<String, Integer> collectWordCount(List<String> words) {
        Map<String, Integer> counts = new HashMap<String, Integer>();
        for (String t: target) {
            counts.put(t, 0);
        }
        for (String s: words) {
            if (counts.containsKey(s)) {
                counts.put(s, counts.get(s) + 1);
            }
        }
        return counts;
    }
     */
}