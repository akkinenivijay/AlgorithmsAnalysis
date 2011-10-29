package org.gsu.cs.ds;

import java.util.Comparator;

import org.gsu.cs.graph.Edge;
import org.gsu.cs.graph.util.EdgeComparator;

public class BinaryHeap<K> {

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

	public void buildHeap() {
		this.heapSize = data.length;
		for (int i = data.length / 2; i >= 0; i--) {
			minHeapify(i);
		}
	}

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

	public void insertIntoHeap(K element) {

		if (heapSize < heapCapacity) {
			data[heapSize] = element;
			this.heapSize++;
			minHeapify(heapSize);
		} else {
			System.out.println("Heap Size cannot exceed capacity");
		}
	}

	public void printData() {
		for (K element : data) {
			System.out.print(element + " ");
		}
		System.out.println();
	}

	private int left(int i) {
		return 2 * i + 1;
	}

	private int right(int i) {
		return (2 * i) + 2;
	}

	private int parent(int i) {
		return i / 2;
	}

	private int heapSize = 0;
	private int heapCapacity = 0;
	private K[] data;
	private Comparator<K> comparator;

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

			bh.buildHeap();

			for (int i1 = 0; i1 < input.length; i1++) {
				Edge e = (Edge) bh.retrieveAndDeleteMin();
				System.out.print(e.getWeight() + " ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
