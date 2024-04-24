import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Main {
	static class Parallel extends RecursiveAction implements Serializable {
       
        /**
		 * 
		 */
		
		private static int THRESHOLD = 10000; 
        private int[] array;
        private int left;
        private int right;

        public Parallel(int[] array, int left, int right) {
            this.array = array;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {
            if (right - left <= THRESHOLD) {
            
                Arrays.sort(array, left, right);
            } else {
           
                int mid = left + (right - left) / 2;
                Parallel leftTask = new Parallel(array, left, mid);
                Parallel rightTask = new Parallel(array, mid, right);
                invokeAll(leftTask, rightTask);
      
                merge(array, left, mid, right);
            }
        }

        private void merge(int[] array, int left, int mid, int right) {
            int[] temp = new int[right - left];
            int i = left, j = mid, k = 0;

            while (i < mid && j < right) {
                if (array[i] <= array[j]) {
                    temp[k++] = array[i++];
                } else {
                    temp[k++] = array[j++];
                }
            }

            while (i < mid) {
                temp[k++] = array[i++];
            }

            while (j < right) {
                temp[k++] = array[j++];
            }

            System.arraycopy(temp, 0, array, left, temp.length);
        }
    }

    public static void main(String[] args) {
        int[] array = generateRandomArray(200_000_000);
        ForkJoinPool pool = new ForkJoinPool();
        Parallel task = new Parallel(array, 0, array.length);
        pool.invoke(task);
        pool.shutdown();

    
        if (isSorted(array)) {
            System.out.println("Array is sorted correctly.");
        } else {
            System.out.println("Array is not sorted correctly.");
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
}

