import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import org.junit.Test;

public class ParallelTest {

	@Test
    public void testSorting() {
        int[] array = generateRandomArray(200_000_000);
        ForkJoinPool pool = new ForkJoinPool();
        Main.Parallel task = new Main.Parallel(array, 0, array.length);
        pool.invoke(task);
        pool.shutdown();

        if (!isSorted(array)) {
            fail("Array is not sorted correctly");
        }
    }

    @Test
    public void testSortedArray() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ForkJoinPool pool = new ForkJoinPool();
        Main.Parallel task = new Main.Parallel(array, 0, array.length);
        pool.invoke(task);
        pool.shutdown();

        if (!isSorted(array)) {
            fail("Array sorted incorrectly");
        }
    }

    @Test
    public void testReverseSortedArray() {
        int[] array = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        ForkJoinPool pool = new ForkJoinPool();
        Main.Parallel task = new Main.Parallel(array, 0, array.length);
        pool.invoke(task);
        pool.shutdown();

        if (!isSorted(array)) {
            fail("Array is not sorted correctly");
        }
    }

    @Test
    public void testDuplicateElements() {
        int[] array = {3, 5, 2, 7, 3, 9, 2, 5, 7, 9};
        ForkJoinPool pool = new ForkJoinPool();
        Main.Parallel task = new Main.Parallel(array, 0, array.length);
        pool.invoke(task);
        pool.shutdown();

        if (!isSorted(array)) {
            fail("Array sorted incorrectly");
        }
    }

    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    private static boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }

    private static void fail(String message) {
        throw new AssertionError(message);
    }
}