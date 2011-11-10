package org.gsu.cs.sort;

/**
 * Peforms Insertion Sort by inserting ith element in the appropriate location
 * in theelements before i-1
 * 
 * @author vijay
 * 
 */
public class InsertionSort {

	public void insertionSort(int[] arr) {
		int i, j, newValue;
		for (i = 1; i < arr.length; i++) {
			newValue = arr[i];
			j = i;
			while (j > 0 && arr[j - 1] > newValue) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = newValue;
		}
	}

}
