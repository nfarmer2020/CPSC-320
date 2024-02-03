import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SkiSlope {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int slopeNumber = 1;
        while (true) {
            System.out.print("Enter the number of junctions and legs for the slope " + slopeNumber + " : ");
            int n = scanner.nextInt();
            int l = scanner.nextInt();

            if (l == 0) {
                break; 
            }

            Map<Integer, List<Integer>> graph = new HashMap<>();
            System.out.println("Enter the legs for the slope " + slopeNumber + ":");
            for (int i = 0; i < l; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            }

            int runs = calculateRuns(graph, 1, 0); 
            System.out.println("Slope " + slopeNumber + " has " + runs + " runs.");
            slopeNumber++;
        }
    }

    public static int calculateRuns(Map<Integer, List<Integer>> graph, int current, int destination) {
        if (current == destination) {
            return 1;
        }

        int runs = 0;
        List<Integer> nextLegs = graph.get(current);

        if (nextLegs != null) {
            for (int next : nextLegs) {
                runs += calculateRuns(graph, next, destination);
            }
        }

        return runs;
    }
}


