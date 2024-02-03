import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SlopeTest {

	@Test
    public void testCalculateRuns() {
        SkiSlope skiSlope = new SkiSlope();

        
        Map<Integer, List<Integer>> graph1 = createGraph(1, 2);
        int runs1 = skiSlope.calculateRuns(graph1, 1, 2);
        if (runs1 != 1) {
            fail("Test case 1 failed!");
        }

        
        Map<Integer, List<Integer>> graph2 = createGraph(1, 2, 1, 3, 2, 4, 3, 4, 4, 5);
        int runs2 = skiSlope.calculateRuns(graph2, 1, 5);
        if (runs2 != 2) {
            fail("Test case 2 failed!");
        }

        
        Map<Integer, List<Integer>> graph3 = createGraph(1, 2, 2, 3);
        int runs3 = skiSlope.calculateRuns(graph3, 3, 1);
        if (runs3 != 0) {
            fail("Test case 3 failed!");
        }
    }

    private Map<Integer, List<Integer>> createGraph(int... legs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < legs.length; i += 2) {
            int a = legs[i];
            int b = legs[i + 1];
            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
        }
        return graph;
    }
}
