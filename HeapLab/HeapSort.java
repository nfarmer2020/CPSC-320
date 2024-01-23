import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class HeapSort {

	 public static ArrayList<Integer> makeTree(Scanner input) {
	        // store ints into Array
	        ArrayList<Integer> numbers = new ArrayList<>();
	        while (input.hasNextInt()) {
	            numbers.add(input.nextInt());
	        }
	        return numbers;
	    }

	    public static void makeHeap(ArrayList<Integer> numbers, int N) {
	        // max heap from binary tree
	        for (int i = N / 2 - 1; i >= 0; i--) {
	            heapify(numbers, N, i);
	        }
	    }

	    private static void heapify(ArrayList<Integer> numbers, int N, int i) {
	        int largest = i;
	        int left = 2 * i + 1;
	        int right = 2 * i + 2;

	        if (left < N && numbers.get(left) > numbers.get(largest)) {
	            largest = left;
	        }

	        if (right < N && numbers.get(right) > numbers.get(largest)) {
	            largest = right;
	        }

	        if (largest != i) {
	            // Swap
	            int temp = numbers.get(i);
	            numbers.set(i, numbers.get(largest));
	            numbers.set(largest, temp);

	            heapify(numbers, N, largest);
	        }
	    }

	    public static ArrayList<Integer> heapSort(ArrayList<Integer> numbers) {
	        int N = numbers.size();

	        //  max heap
	        makeHeap(numbers, N);

	        // Heap sort
	        for (int i = N - 1; i >= 0; i--) {
	            // Swap root with last element
	            int temp = numbers.get(0);
	            numbers.set(0, numbers.get(i));
	            numbers.set(i, temp);

	            // Heapify  reduced heap
	            heapify(numbers, i, 0);
	        }
	        Collections.reverse(numbers);
	        
	        return numbers;
	    }
	

	    public static void main(String[] args) {
	        try {
	            JFileChooser fileChooser = new JFileChooser();
	            fileChooser.setDialogTitle("Choose a file!");
	            int result = fileChooser.showOpenDialog(null);

	            if (result == JFileChooser.APPROVE_OPTION) {
	                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
	                Scanner scanner = new Scanner(new BufferedReader(new FileReader(filePath)));

	                ArrayList<Integer> numbers = makeTree(scanner);

	                heapSort(numbers);

	                System.out.println("Sorted numbers: " + numbers);
	            } else {
	                System.out.println("No file selected!");
	            }
	        } catch (IOException e) {
	            System.err.println("Error reading file! " + e.getMessage());
	        }
	    }
	}
