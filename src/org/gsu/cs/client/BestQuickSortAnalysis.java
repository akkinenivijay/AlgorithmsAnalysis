package org.gsu.cs.client;

import java.util.Random;

import org.gsu.cs.sort.MedianOf3Quicksort;
import org.gsu.cs.sort.QuickSort;
import org.gsu.cs.sort.RandomQuickSort;
import org.gsu.cs.util.SortUtil;

public class BestQuickSortAnalysis {

	private static Random random;

	public BestQuickSortAnalysis() {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		random = new Random();

		int power = 1;
		int size = 0;
		int maxPower = 14;
		int noOfLoops = 40;

		while (power < maxPower) {

			System.gc();
			size = (int) Math.pow(2.0, power);

			long averageQuickSortTime = 0;
			long averageRandomQuickSortTime = 0;
			long averageMedianOf3QuickSortTime = 0;

			for (int i = 0; i < noOfLoops; i++) {
				int[] inputArray = SortUtil.generateRandomArray(size);
				// int[] inputArray = worstCaseArrayForSize(size);

				averageQuickSortTime += performQuickSortTrialRuns(size,
						inputArray, noOfLoops);
				averageRandomQuickSortTime += performRandomQuickSortTrialRuns(
						size, inputArray, noOfLoops);
				averageMedianOf3QuickSortTime += performMedianOf3QuickSortTrialRuns(
						size, inputArray, noOfLoops);

			}

			System.out.println(size + "\t" + averageQuickSortTime / noOfLoops
					+ "\t" + averageRandomQuickSortTime / noOfLoops + "\t"
					+ averageMedianOf3QuickSortTime / noOfLoops);

			power++;
		}
	}

	private static long performMedianOf3QuickSortTrialRuns(int size,
			int[] inputArray, int noOfLoops) {

		long totalTimeForMedianOf3QuickSort = 0;
		long startTime = 0;
		long endTime = 0;
		MedianOf3Quicksort qs = new MedianOf3Quicksort();

		// Perform Garbage Collection before each run as we dont want
		// grabge collection to happen in the middle of the sorting
		System.gc();
		// Copy the input array for each run
		int[] quickSortInputArray = SortUtil.copyArray(inputArray, size);

		startTime = System.nanoTime();
		qs.sort(quickSortInputArray);
		endTime = System.nanoTime();

		totalTimeForMedianOf3QuickSort += endTime - startTime;

		return totalTimeForMedianOf3QuickSort;
	}

	private static long performRandomQuickSortTrialRuns(int size,
			int[] inputArray, int noOfLoops) {
		long totalTimeForRandomQuickSort = 0;
		long startTime = 0;
		long endTime = 0;
		RandomQuickSort qs = new RandomQuickSort();

		// Perform Garbage Collection before each run as we dont want
		// grabge collection to happen in the middle of the sorting
		System.gc();
		// Copy the input array for each run
		int[] quickSortInputArray = SortUtil.copyArray(inputArray, size);

		startTime = System.nanoTime();
		qs.sort(quickSortInputArray);
		endTime = System.nanoTime();

		totalTimeForRandomQuickSort += endTime - startTime;

		return totalTimeForRandomQuickSort;
	}

	private static long performQuickSortTrialRuns(int size, int[] inputArray,
			int noOfLoops) {
		long totalTimeForQuickSort = 0;
		long startTime = 0;
		long endTime = 0;
		QuickSort qs = new QuickSort();

		// Perform Garbage Collection before each run as we dont want
		// grabge collection to happen in the middle of the sorting
		System.gc();
		// Copy the input array for each run
		int[] quickSortInputArray = SortUtil.copyArray(inputArray, size);

		startTime = System.nanoTime();
		qs.sort(quickSortInputArray);
		endTime = System.nanoTime();

		totalTimeForQuickSort += endTime - startTime;

		return totalTimeForQuickSort;
	}

}