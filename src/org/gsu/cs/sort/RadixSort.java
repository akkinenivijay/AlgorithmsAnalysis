package org.gsu.cs.sort;

import org.gsu.cs.util.SortUtil;

/**
 * Class to perform radix sort
 * 
 * @author vijay
 * 
 */
public class RadixSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] inputArray = { 1123, 568, 2490, 10200, 786, 581 };

		int base = 10;
		int numberOfPasses = SortUtil.calculateNumberOfPasses(20000, base);

		RadixSort rs = new RadixSort();

		int length = inputArray.length;

		int[] quotientArray = SortUtil.copyArray(inputArray, length);

		rs.performRadixSort(inputArray, base, numberOfPasses, quotientArray,
				length);
	}

	/**
	 * Peforms counting stable sort for the number of passes
	 * 
	 * @param inputArray
	 * @param base
	 * @param numberOfPasses
	 * @param length
	 */
	public void performRadixSort(int[] inputArray, int base,
			int numberOfPasses, int[] quotientArray, int length) {

		CountingSort cs = new CountingSort(quotientArray);

		int[] outputArray = inputArray;

		for (int i = 1; i <= numberOfPasses; i++) {
			outputArray = cs.countingSort(outputArray, base, i, length);
		}

	}

}
