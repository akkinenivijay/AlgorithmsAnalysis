package org.gsu.cs.sort;

import java.util.Random;

/**
 * A variant of Quick Sort where we use randomly selected pivot to exchange with
 * the left most element and perform quicksort
 * 
 * @author vijay
 * 
 */
public class RandomQuickSort {
	private int[] numbers;
	private int number;
	private Random random = new Random();

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
		int i = left, j = right;
		int tmp;
		int pivot = arr[left];
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
		;
		return i;
	}

	/**
	 * Pefroms the quicksort by calling the random partitioning
	 */
	private void quickSort(int arr[], int left, int right) {
		int index = randomizedPartition(arr, left, right);
		if (left < index - 1)
			quickSort(arr, left, index - 1);
		if (index < right)
			quickSort(arr, index, right);
	}

	/**
	 * 
	 * Selects a random number from the input array with in the given range of
	 * left to right and exchanges it with the left element
	 * 
	 * @param arr
	 *            input array
	 * @param left
	 *            left boundary
	 * @param right
	 *            right boundary
	 * @return
	 */
	private int randomizedPartition(int arr[], int left, int right) {

		int randomNum = random.nextInt(right - left + 1) + left;

		// Exchanging random with low
		int temp = numbers[randomNum];
		arr[randomNum] = arr[left];
		arr[left] = temp;

		return partition(arr, left, right);
	}
}