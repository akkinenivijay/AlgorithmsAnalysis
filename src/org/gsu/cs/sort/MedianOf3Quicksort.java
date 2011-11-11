package org.gsu.cs.sort;

/**
 * Median Of three Quick Sort - a variant of quick sort
 * 
 * @author vijay
 * 
 */
public class MedianOf3Quicksort {
	private int[] numbers;
	private int number;

	/**
	 * Public method exposed to perform quick sort
	 * 
	 * @param values
	 */
	public void sort(int[] values) {
		// Check for empty or null array
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
	private int partition(int arr[], int left, int right) {
		int i = left + 1, j = right - 2;
		int tmp = 0;
		int pivot = medianOfThree(arr, left, right);
		// System.out.println(i);
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

		swap(arr, i, right - 1); // step 4
		return j;
	}

	/**
	 * Pefroms the quicksort by calling the partitioning routine and performs
	 * insertion sort if the input size is less than 2
	 */
	void quickSort(int arr[], int left, int right) {
		if (right - left < 2)
			insertionSort(arr, left, right);
		else {
			int p = partition(arr, left, right);
			quickSort(arr, left, p);
			quickSort(arr, p + 2, right);
		}
	}

	/**
	 * Perfroms insertion on the given input
	 * 
	 * @param arr
	 * @param left
	 * @param right
	 */
	private void insertionSort(int[] arr, int left, int right) {
		int j, newValue;
		for (int i = left; i < right; i++) {
			newValue = arr[i];
			j = i;
			while (j > left && arr[j - 1] > newValue) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = newValue;
		}

	}

	/**
	 * Calculates the median of the left, center,right and moves the median to
	 * right -1 element and uses the median as the pivot
	 * 
	 * @param arr
	 * @param left
	 * @param right
	 * @return
	 */
	public int medianOfThree(int arr[], int left, int right) {
		int center = (left + right) / 2;
		// order left & center
		if (arr[left] > arr[center])
			swap(arr, left, center);
		// order left & right
		if (arr[left] > arr[right])
			swap(arr, left, right);
		// order center & right
		if (arr[center] > arr[right])
			swap(arr, center, right);

		swap(arr, center, right - 1); // put pivot on right
		return arr[right - 1]; // return median value
	}

	private void swap(int arr[], int i, int j) {
		int temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
	}

}