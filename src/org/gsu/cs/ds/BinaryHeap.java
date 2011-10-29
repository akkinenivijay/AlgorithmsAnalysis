package org.gsu.cs.ds;

import org.gsu.cs.graph.Edge;

public class BinaryHeap {

	private int heapSize;

	private int left(int i) {
		return 2 * i + 1;
	}

	private int right(int i) {
		return (2 * i) + 2;
	}

	private int parent(int i) {
		return i / 2;
	}

	public Edge[] buildHeap(Edge[] array) {
		this.heapSize = array.length;
		for (int i = array.length / 2; i >= 0; i--) {
			minHeapify(array, i);
		}
		for (Edge e : array) {
			System.out.print(e.getWeight() + "  ");
		}
		System.out.println("\n");

		return array;
	}

	public Edge retrieveAndDeleteMin(Edge[] array) throws Exception {

		if (array == null || array.length == 0)
			throw new Exception("Heap is empty");
		else {
			if (heapSize > 0) {
				Edge result = array[0];
				array[0] = array[heapSize - 1];
				heapSize--;
				if (heapSize > 0)
					minHeapify(array, 0);
				return result;
			} else {
				throw new Exception("Heap is Emptied");
			}
		}
	}

	public Edge[] minHeapify(Edge[] array, int i) {
		int smallest;
		int l = left(i);
		int r = right(i);

		if (l < heapSize && array[l].getWeight() < array[i].getWeight()) {
			smallest = l;
		} else {
			smallest = i;
		}

		if (r < heapSize && array[r].getWeight() < array[smallest].getWeight()) {
			smallest = r;
		}

		if (smallest != i) {
			Edge temp = array[i];
			array[i] = array[smallest];
			array[smallest] = temp;
			minHeapify(array, smallest);
		}

		return array;
	}

	public int getHeapSize() {
		return heapSize;
	}

	public void setHeapSize(int heapSize) {
		this.heapSize = heapSize;
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

		BinaryHeap bh = new BinaryHeap();
		Edge[] temp = bh.buildHeap(edgeArray);
		try {
			for (int i1 = 0; i1 <= input.length; i1++) {
				Edge e = bh.retrieveAndDeleteMin(temp);
				System.out.println("Min Wieghted Edge from the graph is: "
						+ e.getWeight());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
