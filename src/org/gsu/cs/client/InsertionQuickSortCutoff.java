package org.gsu.cs.client;

import org.gsu.cs.sort.InsertionSort;
import org.gsu.cs.sort.QuickSort;
import org.gsu.cs.util.SortUtil;

public class InsertionQuickSortCutoff {

	public InsertionQuickSortCutoff() {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int noOfLoops = 20;

		for (int size = 1; size <= 256; size++) {

			long averageInsertionSortTime = 0;
			long averageQuickSortTime = 0;

			for (int i = 0; i < noOfLoops; i++) {

				int[] inputArray = SortUtil.generateRandomArray(size);
				//int[] inputArray = SortUtil.worstCaseArrayForSize(size);

				averageInsertionSortTime += performInsertionSortTrialRuns(size,
						inputArray);
				averageQuickSortTime += performQuickSortTrialRuns(size,
						inputArray);
			}

			System.out.println(size + "\t" + averageInsertionSortTime
					/ noOfLoops + "\t" + averageQuickSortTime / noOfLoops);
		}
	}

	private static long performQuickSortTrialRuns(int size, int[] inputArray) {
		long totalTimeForQuickSort = 0;
		long startTime = 0;
		long endTime = 0;
		QuickSort qs = new QuickSort();

		int[] quickSortInputArray = SortUtil.copyArray(inputArray, size);

		startTime = System.nanoTime();
		qs.sort(quickSortInputArray);
		endTime = System.nanoTime();

		totalTimeForQuickSort += endTime - startTime;

		return totalTimeForQuickSort;
	}

	private static long performInsertionSortTrialRuns(int size,
			int[] inputArray) {
		long totalTimeForInsertionSort = 0;
		long startTime = 0;
		long endTime = 0;
		InsertionSort is = new InsertionSort();

		int[] insertionSortInputArray = SortUtil.copyArray(inputArray, size);

		startTime = System.nanoTime();
		is.insertionSort(insertionSortInputArray);
		endTime = System.nanoTime();

		totalTimeForInsertionSort += endTime - startTime;

		return totalTimeForInsertionSort;
	}

}
