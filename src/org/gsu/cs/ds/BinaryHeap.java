package org.gsu.cs.ds;

import java.util.Comparator;

import org.gsu.cs.graph.Edge;
import org.gsu.cs.graph.util.EdgeComparator;

public class BinaryHeap<K> {

	public void minHeapify(int i) {
		int smallest;
		int l = left(i);
		int r = right(i);

		if (l < heapSize
				&& (comparator.compare(inputArray[l], inputArray[i]) == -1)) {
			smallest = l;
		} else {
			smallest = i;
		}

		if (r < heapSize
				&& (comparator.compare(inputArray[r], inputArray[smallest]) == -1)) {
			smallest = r;
		}

		if (smallest != i) {
			K temp = inputArray[i];
			inputArray[i] = inputArray[smallest];
			inputArray[smallest] = temp;
			minHeapify(smallest);
		}
	}

	public void buildHeap() {
		this.heapSize = inputArray.length;
		for (int i = inputArray.length / 2; i >= 0; i--) {
			minHeapify(i);
		}
	}

	public K retrieveAndDeleteMin() throws Exception {

		if (inputArray == null || inputArray.length == 0)
			throw new Exception("Heap is empty");
		else {
			if (heapSize > 0) {
				K result = inputArray[0];
				inputArray[0] = inputArray[heapSize - 1];
				heapSize--;
				if (heapSize > 0)
					minHeapify(0);
				return result;
			} else {
				throw new Exception("Heap is Emptied");
			}
		}
	}

	public static void main(String[] args) {

		int i = 0;

		int[] input = { 23, 17, 14, 6, 13, 10, 1, 5, 7, 12 };
		Edge[] edgeArray = new Edge[input.length];

		for (int weight : input) {
			Edge e = new Edge();
			e.setWeight(weight);
			edgeArray[i] = e;
			i++;
		}

		BinaryHeap<Edge> bh = new BinaryHeap<Edge>(edgeArray,
				new EdgeComparator());
		bh.buildHeap();
		try {
			for (int i1 = 0; i1 <= input.length; i1++) {
				Edge e = (Edge) bh.retrieveAndDeleteMin();
				System.out.println("Min Wieghted Edge from the graph is: "
						+ e.getWeight());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public BinaryHeap(K[] inputArray, Comparator<K> comparator) {
		super();
		this.inputArray = inputArray;
		this.comparator = comparator;
	}

	private int heapSize;
	private K[] inputArray;
	private Comparator<K> comparator;

	private int left(int i) {
		return 2 * i + 1;
	}

	private int right(int i) {
		return (2 * i) + 2;
	}

	private int parent(int i) {
		return i / 2;
	}

	public int getHeapSize() {
		return heapSize;
	}

	public void setHeapSize(int heapSize) {
		this.heapSize = heapSize;
	}

	public K[] getInputArray() {
		return inputArray;
	}
}
