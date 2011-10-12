package org.gsu.cs.sort;

/**
 * 
 * This Class performs counting sort which is a stable sort based on the keys
 * 
 * @author vijay akkineni
 * 
 */
public class CountingSort {

	public CountingSort(int[] quotient) {
		super();
		this.quotient = quotient;
	}

	int[] quotient = null;

	/**
	 * Method which performs counting sort based on the number of passes
	 * 
	 * @param a
	 *            Input Array
	 * @param k
	 *            Base like Decimal,Binary or Hexadecimal could be any base.
	 * @param digit
	 *            Nth digit to be extracted
	 * @param length
	 * @return
	 */
	public int[] countingSort(int a[], int k, int digit, int length) {

		int[] c = new int[k];

		int lsd[] = new int[length];

		int[] b = new int[length];

		for (int i = 0; i < k; i++)
			c[i] = 0;

		// c[i] stores the number of items with key equal to nth significant
		// digit from the left depending on the base k
		for (int i = 0; i < length; i++) {
			int index = extractIndexNthSignificantDigitForBase(k, quotient, i);
			lsd[i] = index;
			c[index] = c[index] + 1;
		}

		// Count array stores the number of items with key less than i
		for (int i = 1; i < k; i++) {
			c[i] = c[i] + c[i - 1];
		}

		// item is moved into its correct position in the output array
		int[] newq = new int[length];
		for (int j = length - 1; j >= 0; j--) {
			int index = lsd[j];

			b[c[index] - 1] = a[j];
			newq[c[index] - 1] = quotient[j];
			c[index] = c[index] - 1;
		}

		quotient = newq;

		return b;
	}

	public int extractIndexNthSignificantDigitForBase(int k, int[] quot, int i) {

		int reminder = quot[i] % k;
		int quotient = quot[i] / k;

		quot[i] = quotient;

		return reminder;
	}

}
