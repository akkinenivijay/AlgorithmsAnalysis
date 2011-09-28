package org.gsu.cs.client;

import org.gsu.cs.sort.BitOperationsRadixSort;
import org.gsu.cs.sort.InsertionSort;
import org.gsu.cs.sort.MedianOf3Quicksort;
import org.gsu.cs.util.SortUtil;

public class InsertionVsQuickVsRadix {

	public InsertionVsQuickVsRadix() {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int power = 6;
		int size = 0;
		int maxPower = 14;
		int noOfLoops = 20;

		while (power <= maxPower) {

			System.gc();
			size = (int) Math.pow(2.0, power);

			long averageInsertionSortTime = 0;
			long averageQuickSortTime = 0;
			long averageRadixSortTime = 0;

			for (int i = 0; i < noOfLoops; i++) {
				// int[] inputArray = SortUtil.generateRandomArray(size);
				int[] inputArray = SortUtil.worstCaseArrayForSize(size);

				averageInsertionSortTime = performInsertionSortTrialRuns(size,
						inputArray);
				System.gc();

				averageQuickSortTime = performQuickSortTrialRuns(size,
						inputArray);
				System.gc();

				averageRadixSortTime = performRadixSortTrialRuns(size,
						inputArray);
				System.gc();
			}

			System.out.println(size + "\t" + averageInsertionSortTime
					/ noOfLoops + "\t" + averageQuickSortTime / noOfLoops
					+ "\t" + averageRadixSortTime / noOfLoops);
			power++;
		}
	}

	private static long performRadixSortTrialRuns(int size, int[] inputArray) {
		long totalTimeForRadixSort = 0;
		long startTime = 0;
		long endTime = 0;
		BitOperationsRadixSort bs = new BitOperationsRadixSort();

		// Copy the input array for each run
		int[] quickSortInputArray = SortUtil.copyArray(inputArray, size);

		startTime = System.nanoTime();
		bs.performRadixSort(quickSortInputArray, 8);
		endTime = System.nanoTime();

		totalTimeForRadixSort += endTime - startTime;

		return totalTimeForRadixSort;
	}

	private static long performQuickSortTrialRuns(int size, int[] inputArray) {
		long totalTimeForQuickSort = 0;
		long startTime = 0;
		long endTime = 0;

		MedianOf3Quicksort qs = new MedianOf3Quicksort();

		// Copy the input array for each run
		int[] quickSortInputArray = SortUtil.copyArray(inputArray, size);

		startTime = System.nanoTime();
		qs.sort(quickSortInputArray);
		endTime = System.nanoTime();

		totalTimeForQuickSort += endTime - startTime;

		return totalTimeForQuickSort;
	}

	private static long performInsertionSortTrialRuns(int size, int[] inputArray) {
		long totalTimeForInsertionSort = 0;
		long startTime = 0;
		long endTime = 0;
		InsertionSort is = new InsertionSort();

		// Copy the input array for each run
		int[] insertionSortInputArray = SortUtil.copyArray(inputArray, size);

		startTime = System.nanoTime();
		is.insertionSort(insertionSortInputArray);
		endTime = System.nanoTime();

		totalTimeForInsertionSort += endTime - startTime;

		return totalTimeForInsertionSort;
	}

}
