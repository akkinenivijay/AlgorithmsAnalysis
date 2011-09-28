package org.gsu.cs.sort;

/**
 * Standard Hoare Quicksort implementation
 * 
 * @author vijay
 * 
 */
public class QuickSort {
	private int[] numbers;
	private int number;

	/**
	 * Public method exposed to perform quick sort
	 * 
	 * @param values
	 */
	public void sort(int[] values) {
		if (values == null || values.length == 0) {
			return;
		}
		this.numbers = values;
		number = values.length;
		quickSort(numbers, 0, number - 1);
	}

	/**
	 * Standard Hoare partitioning implementation
	 * 
	 * @param arr
	 * @param left
	 * @param right
	 * @return
	 */
	int partition(int arr[], int left, int right) {
		int i = left, j = right;
		int tmp;
		int pivot = arr[(left + right) / 2];
		while (i <= j) {
			while (arr[i] < pivot)
				i++;
			while (arr[j] > pivot)
				j--;
			if (i <= j) {
				tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				i++;
				j--;
			}
		}
		return i;
	}

	/**
	 * Pefroms the quicksort by calling the partitioning routine and recursively
	 * on the left and right partition
	 */

	void quickSort(int arr[], int left, int right) {
		int index = partition(arr, left, right);
		if (left < index - 1)
			quickSort(arr, left, index - 1);
		if (index < right)
			quickSort(arr, index, right);
	}

}