import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * created by hug 4/9/2018
 * Basic sanity check for your GraphDB construction on a tiny clean input graph.
 */
public class TestGraphBuildingTiny {
    private static GraphDB graphTiny;
    private static final String OSM_DB_PATH_TINY = "../library-su18/bearmaps/tiny-clean.osm.xml";
    private static boolean initialized = false;

    /**
     * Initializes the student graphs.
     * You should not need to modify this code. If you do, then the Autograder
     * may not work with your code.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        if (initialized) {
            return;
        }

        graphTiny = new GraphDB(OSM_DB_PATH_TINY);
        initialized = true;
    }

    /**
     * All nodes in the tiny-clean file are valid, so the clean method
     * should not remove any vertices for this graph, i.e. all 7 nodes
     * should survive the cleaning process.
     */
    @Test
    public void testNodeCountTinyGraph() {
        Iterable<Long> ids = graphTiny.vertices();
        int numberOfNodes = TestGraphBuilding.countIterableItems(ids);
        assertEquals("Your graph should have 7 nodes.", 7, numberOfNodes);
    }

    @Test
    public void testAdjacent() {
        long v = 63L;
        HashSet<Long> expected = new HashSet<>();
        HashSet<Long> actual = new HashSet<>();
        expected.add(55L);
        expected.add(41L);
        expected.add(66L);

        for (long neighbor : graphTiny.adjacent(v)) {
            actual.add(neighbor);
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testLonAndLat() {
        long v = 63L;
        assertEquals(-122.23, graphTiny.lon(v), 0.00001);
        assertEquals(37.85, graphTiny.lat(v), 0.00001);
    }

    @Test
    public void testDistance() {
        long v = 22L;
        long w = 46L;
        assertEquals(2.974465227907971, graphTiny.distance(v, w), 0.00001);
    }

    @Test
    public void testClosest() {
        double lon = -122.25;
        double lat = 37.87;
        assertEquals("If your actual value is 46, make sure you are using the correct distance "
                + "metrics.", 55L, graphTiny.closest(lon, lat));
    }
}
