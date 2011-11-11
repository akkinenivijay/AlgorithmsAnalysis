package org.gsu.cs.runner;
import org.gsu.cs.sort.InsertionSort;

public class InsertionSortRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		for (int arraySize = 1; arraySize < 50; arraySize++) {

			long totalTimeForSort = 0;
			InsertionSort is = new InsertionSort();

			for (int i = 0; i < 100000; i++) {

				int[] inputArray = worstCaseArrayForSize(arraySize);

				long startTime = System.nanoTime();
				is.insertionSort(inputArray);
				long endTime = System.nanoTime();

				if (i > 999) {
					long duration = endTime - startTime;
					totalTimeForSort += duration;
				}

			}
			System.out.println(totalTimeForSort / 99000);
		}

	}

	private static int[] worstCaseArrayForSize(int size) {
		int[] input = new int[size];

		for (int i = 0; i < size; i++) {
			input[i] = size - i;
		}

		return input;
	}

}
