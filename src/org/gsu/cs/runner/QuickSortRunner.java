package org.gsu.cs.runner;

import org.gsu.cs.sort.QuickSort;

public class QuickSortRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int count = 0;

		for (int arraySize = 99999; arraySize < 100000; arraySize++) {

			long totalTimeForSort = 0;
			QuickSort qs = new QuickSort();

			for (int i = 0; i < 5; i++) {

				int[] inputArray = worstCaseArrayForSize(arraySize);

				long startTime = System.nanoTime();
				qs.sort(inputArray);
				long endTime = System.nanoTime();

				if (i >= 0) {
					long duration = endTime - startTime;
					totalTimeForSort += duration;
					count = count + 1;
				}

			}
			System.out.println(totalTimeForSort / count);
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
