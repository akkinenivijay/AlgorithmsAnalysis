package org.gsu.cs.runner;
import org.gsu.cs.sort.MedianOf3Quicksort;

public class MedianOf3QuickSortRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int count = 0;
		for (int arraySize = 1; arraySize < 10000; arraySize = arraySize + 20) {
			long totalTimeForSort = 0;
			MedianOf3Quicksort qs = new MedianOf3Quicksort();

			for (int i = 0; i < 1; i++) {

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
			// System.out.println(arraySize + "\t" + totalTimeForSort / count);
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