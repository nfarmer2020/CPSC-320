import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class HeapTest {

	@Test
    public void testMakeTree() {
        String input = "5 3 8 1 7";
        Scanner scanner = new Scanner(input);

        ArrayList<Integer> result = HeapSort.makeTree(scanner);

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(5);
        expected.add(3);
        expected.add(8);
        expected.add(1);
        expected.add(7);

        if (!result.equals(expected)) {
            fail("Tree was not made correctly");
        }
    }

    @Test
    public void testMakeHeap() {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(3);
        numbers.add(8);
        numbers.add(1);
        numbers.add(7);

        HeapSort.makeHeap(numbers, numbers.size());

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(8);
        expected.add(7);
        expected.add(5);
        expected.add(1);
        expected.add(3);

        if (!numbers.equals(expected)) {
            fail("Heap was not made correctly");
        }
    }

    @Test
    public void testHeapSort() {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(3);
        numbers.add(8);
        numbers.add(1);
        numbers.add(7);

        ArrayList<Integer> result = HeapSort.heapSort(numbers);

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(8);
        expected.add(7);
        expected.add(5);
        expected.add(3);
        expected.add(1);

        if (!result.equals(expected)) {
            fail("Heap was not sorted correctly");
        }
    }
}
    


