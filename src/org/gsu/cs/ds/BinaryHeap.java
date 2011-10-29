package org.gsu.cs.ds;

import java.util.Comparator;

import org.gsu.cs.graph.Edge;
import org.gsu.cs.graph.util.EdgeComparator;

/**
 * A generic class to build Binary Heap, This class is an implementation of Min
 * Heap
 * 
 * @author Vijay Akkineni
 * 
 * @param <K>
 */
public class BinaryHeap<K> {

	/**
	 * Method to Min - Heapify the underlying data with asymptotic bound of O(logn)
	 * 
	 * @param i
	 *            index from which to maintain the min property
	 */
	public void minHeapify(int i) {
		int smallest;
		int l = left(i);
		int r = right(i);

		if (l < heapSize && (comparator.compare(data[l], data[i]) == -1)) {
			smallest = l;
		} else {
			smallest = i;
		}

		if (r < heapSize && (comparator.compare(data[r], data[smallest]) == -1)) {
			smallest = r;
		}

		if (smallest != i) {
			K temp = data[i];
			data[i] = data[smallest];
			data[smallest] = temp;
			minHeapify(smallest);
		}
	}

	/**
	 * If the elements are not inserted using the InsertIntoHeap method build
	 * heap method builds the heap maintaining the heap property
	 * 
	 * Aysmptotic Bound = O(nlogn)
	 */
	public void buildHeap() {
		this.heapSize = data.length;
		for (int i = data.length / 2; i >= 0; i--) {
			minHeapify(i);
		}
	}

	/**
	 * Method to retrieve the minimum value from the heap and build the heap
	 * again
	 * 
	 * Asymptotic Upper bound = O(logn)
	 * 
	 * @return
	 */
	public K retrieveAndDeleteMin() {

		if (data == null || heapSize == 0)
			System.out.println("Heap is empty");

		if (heapSize > 0) {
			K result = data[0];
			data[0] = data[heapSize - 1];
			heapSize--;
			if (heapSize > 0)
				minHeapify(0);
			return result;
		}

		return null;
	}

	/**
	 * Method to insert elements into heap maintaining the heap property
	 * throughout
	 * 
	 * Asymptotic Bound = O(logn)
	 * 
	 * @param element
	 */
	public void insertIntoHeap(K element) {

		if (heapSize < heapCapacity) {
			data[heapSize] = element;
			preserveHeapProperty(heapSize);
			this.heapSize++;
		} else {
			System.out.println("Heap Size cannot exceed capacity");
		}
	}

	/**
	 * Method shifts the inserted element to the parent after checking for the
	 * min condition
	 * 
	 * @param nodeIndex
	 */
	private void preserveHeapProperty(int nodeIndex) {
		int parentIndex;
		if (nodeIndex != 0) {
			parentIndex = parent(nodeIndex);
			if (comparator.compare(data[parentIndex], data[nodeIndex]) == 1) {
				K tmp = data[parentIndex];
				data[parentIndex] = data[nodeIndex];
				data[nodeIndex] = tmp;
				preserveHeapProperty(parentIndex);
			}
		}
	}

	/**
	 * Utility method to print data in Binary Heap
	 */
	public void printData() {
		for (K element : data) {
			if (element instanceof Edge) {
				Edge e = (Edge) element;
				System.out.print(e.getWeight() + " ");
			} else {
				System.out.print(element + " ");
			}

		}
		System.out.println();
	}

	/**
	 * Returns the left child of the index
	 * 
	 * @param i
	 * @return
	 */
	private int left(int i) {
		return 2 * i + 1;
	}

	/**
	 * Returns the right child of the index
	 * 
	 * @param i
	 * @return
	 */
	private int right(int i) {
		return (2 * i) + 2;
	}

	/**
	 * Returns the parent of the index
	 * 
	 * @param i
	 * @return
	 */
	private int parent(int i) {
		return i / 2;
	}

	/**
	 * The current size of the heap
	 */
	private int heapSize = 0;

	/**
	 * Max Capacity of the Heap
	 */
	private int heapCapacity = 0;

	/**
	 * Input Data
	 */
	private K[] data;

	/**
	 * Comparator used to compare elements
	 */
	private Comparator<K> comparator;

	@SuppressWarnings("unchecked")
	public BinaryHeap(int capacity) {
		super();
		this.heapCapacity = capacity;
		this.data = (K[]) new Object[heapCapacity];
	}

	public void setComparator(Comparator<K> comparator) {
		this.comparator = comparator;
	}

	public static void main(String[] args) {

		BinaryHeap<Edge> bh = null;
		try {
			bh = new BinaryHeap<Edge>(10);
			bh.setComparator(new EdgeComparator());

			int[] input = { 23, 17, 14, 6, 13, 10, 1, 5, 7, 12 };

			for (int weight : input) {
				Edge edge = new Edge();
				edge.setWeight(weight);
				bh.insertIntoHeap(edge);
			}
			
			bh.printData();
			// bh.buildHeap();

			for (int i1 = 0; i1 < input.length; i1++) {
				Edge e = (Edge) bh.retrieveAndDeleteMin();
				System.out.print(e.getWeight() + " ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
