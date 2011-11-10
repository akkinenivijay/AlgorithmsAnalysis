package org.gsu.cs.util;

import java.util.Random;

/**
 * A Util Class for performng various utility methods for algorithm analysis
 * 
 * @author vijay
 * 
 */
public class SortUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(calculateNumberOfPasses(20000, 130));
		System.out.println(extractIndexNthSignificantDigitForBase(123, 10, 2));

	}

	/**
	 * Calculated the number of passes the counting sort should be performed for
	 * different bases
	 * 
	 * @param limit
	 * @param base
	 * @return
	 */
	public static int calculateNumberOfPasses(int limit, int base) {
		// int count = 0;
		// while (limit / base > 0) {
		// limit = limit / base;
		// count++;

		// }

		int count = (int) Math.ceil(Math.log10(limit) / Math.log10(base));

		return count;
	}

	/**
	 * Extracts the Nth digit from number giVen a base k from input
	 * 
	 * @param input
	 *            Input
	 * @param k
	 *            Base like decimal or binary , hexadecimal
	 * @param digit
	 *            Nth digit to extract
	 * @return
	 */
	public static int extractIndexNthSignificantDigitForBase(int input, int k,
			int digit) {

		int reminder = 0;
		int times = 0;
		while (digit > 0) {
			times = input / k;
			reminder = input % k;

			input = times;
			digit--;
		}

		return reminder;
	}

	/**
	 * Replicates the inputArray and provides a new Array
	 * 
	 * @param inputArray
	 * @param size
	 * @return
	 */
	public static int[] copyArray(int[] inputArray, int size) {
		int[] output = new int[size];
		for (int i = 0; i < size; i++) {
			output[i] = inputArray[i];
		}
		return output;
	}

	/**
	 * generates a random array where the n range lies between 0 - 20000
	 * 
	 * @param size
	 * @return
	 */
	public static int[] generateRandomArray(int size) {
		Random random = new Random();
		int[] input = new int[size];

		for (int i = 0; i < size; i++) {
			input[i] = random.nextInt(20000);
		}
		return input;
	}

	/**
	 * generates an array with the worst case input, since we are performing
	 * ascending order of array we start the array with the max number
	 * 
	 * @param size
	 * @return
	 */
	public static int[] worstCaseArrayForSize(int size) {
		int[] input = new int[size];

		for (int i = 0; i < size; i++) {
			input[i] = size - i;
		}

		return input;
	}

}
