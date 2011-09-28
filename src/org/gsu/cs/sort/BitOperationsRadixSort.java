package org.gsu.cs.sort;

/**
 * This class performs a radix sort using a radix of 2 raised to power k
 * 
 * @author vijay
 * 
 */
public class BitOperationsRadixSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] inputArray = { 1123, 568, 2490, 10200, 786, 581 };

		BitOperationsRadixSort rs = new BitOperationsRadixSort();

		rs.performRadixSort(inputArray, 4);

		for (int inp : inputArray) {
			System.out.println(inp);
		}
	}

	/**
	 * The inputs to the method are the array and the number of bits the shift
	 * should happen
	 */
	public static void performRadixSort(int[] a, int bits) {

		int length = a.length;

		int[] b = new int[length];
		int[] original = b;

		// ~(-1<<bits) gives the all posible values for the number of bits. Ex
		// bits=4 ~(1<<bits) = 15 and we perform bits size right shifts so that
		// we get the next set of digits
		int rshift = 0;
		for (int mask = ~(-1 << bits); mask != 0; mask <<= bits, rshift += bits) {

			int[] countArray = new int[1 << bits];

			// This is standard counting sort mechanism where we calculate the
			// number of occurences of the first "bits" and place them in the
			// countArray

			for (int p = 0; p < length; ++p) {
				int key = (a[p] & mask) >> rshift;
				++countArray[key];
			}

			// We perform a cumulative sum on the count array
			for (int i = 1; i < countArray.length; ++i) {
				countArray[i] += countArray[i - 1];
			}

			// Finally placing the elements in the output array by peforming mask
			// and shifting
			for (int p = length - 1; p >= 0; --p) {
				int key = (a[p] & mask) >> rshift;
				--countArray[key];
				b[countArray[key]] = a[p];
			}

			int[] temp = b;
			b = a;
			a = temp;
		}

		if (a == original) {
			System.arraycopy(a, 0, b, 0, length);
		}
	}
}
