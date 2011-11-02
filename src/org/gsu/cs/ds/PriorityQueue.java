package org.gsu.cs.ds;

import java.util.HashMap;
import java.util.Map;

import org.gsu.cs.graph.Edge;

/**
 * A Priority Queue Implementation with support for decrease key operation. This
 * data structure is useful for Prim's MST Algo(jarnik's) Dijkistra's Shortest
 * path and many more use cases.
 * 
 * @author Vijay Akkineni
 * 
 * @param <K>
 */
public class PriorityQueue<K> {

	/**
	 * An Entry Class to encapsulate the Generic element, its Index in the
	 * priorityQueue array element and the priority for the element (Currently
	 * the priority is strongly typed as Int should be changed to a generic type
	 * so that any comparable element can be used as priority (Such as Double,
	 * or even a wieght of the edge, not sure at this point on how to implement)
	 * 
	 * @author va2839
	 * 
	 * @param <K>
	 */
	private final class Entry<T> {

		public T element;
		public int index;
		public int priority;

		@Override
		public String toString() {
			return "Entry [element=" + element + ", index=" + index
					+ ", priority=" + priority + "]";
		}
	}

	/**
	 * The current size of the heap
	 */
	private int heapSize = 0;
	/**
	 * Maximum Capacity of the Heap
	 */
	private int heapCapacity = 0;
	private Map<K, Entry<K>> entryMapper = new HashMap<K, Entry<K>>();
	/**
	 * Data stored in the priority queue and we use the array indices to
	 * determine the right and left children of the node. A standard trait of
	 * heaps and PQ
	 */
	private Entry<K>[] dataArray;

	public void setDataArray(K[] dataArray, int[] priorityArray) {
		int i = 0;

		for (K element : dataArray) {
			Entry<K> entry = new Entry<K>();
			entry.element = element;
			entry.index = i;
			entry.priority = priorityArray[i];
			this.dataArray[i] = entry;
			entryMapper.put(entry.element, entry);
			i++;
		}

		this.heapSize = dataArray.length;

	}

	/**
	 * Method to insert elements into heap maintaining the heap property
	 * throughout
	 * 
	 * Asymptotic Bound = O(logn)
	 * 
	 * @param element
	 */
	public void insertIntoHeap(K element, int priority) {

		if (heapSize < heapCapacity) {

			Entry<K> entry = new Entry<K>();
			entry.element = element;
			entry.priority = priority;
			entry.index = heapSize;
			dataArray[heapSize] = entry;
			preserveHeapProperty(heapSize);
			entryMapper.put(element, entry);
			this.heapSize++;
		} else {
			System.out.println("Heap Size cannot exceed capacity");
		}
	}

	/**
	 * Method to Min - Heapify the underlying data with asymptotic bound of
	 * O(logn)
	 * 
	 * @param i
	 *            index from which to maintain the min property
	 */
	private void minHeapify(int i) {
		int smallest;
		int l = left(i);
		int r = right(i);

		if (l < heapSize
				&& (mincompare(dataArray[l].priority, dataArray[i].priority) == 0)) {
			smallest = l;
		} else {
			smallest = i;
		}

		if (r < heapSize
				&& (mincompare(dataArray[r].priority,
						dataArray[smallest].priority) == 0)) {
			smallest = r;
		}

		if (smallest != i) {
			Entry<K> temp = dataArray[i];

			dataArray[i] = dataArray[smallest];
			dataArray[i].index = i;

			dataArray[smallest] = temp;
			dataArray[smallest].index = smallest;

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
		this.heapSize = dataArray.length;
		for (int i = dataArray.length / 2; i >= 0; i--) {
			minHeapify(i);
		}
	}

	public void decreaseKey(K element, int priority) {
		Entry<K> entry = entryMapper.get(element);
		int elementPosition = entry.index;
		if (entry.priority < priority) {
			System.out.println("New Priority is greater than the current "
					+ "priority - Decrese Priority operation only");
		} else {
			entry.priority = priority;
			preserveHeapProperty(elementPosition);
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

		if (dataArray == null || heapSize == 0) {
			System.out.println("Heap is empty");
		}
		if (heapSize > 0) {
			Entry<K> result = dataArray[0];
			dataArray[0] = dataArray[heapSize - 1];
			heapSize--;
			if (heapSize > 0) {
				minHeapify(0);
			}
			entryMapper.remove(result.element);
			return result.element;
		}

		return null;
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
			if (mincompare(dataArray[parentIndex].priority,
					dataArray[nodeIndex].priority) == 1) {
				Entry<K> temp = dataArray[parentIndex];

				dataArray[parentIndex] = dataArray[nodeIndex];
				dataArray[parentIndex].index = parentIndex;

				dataArray[nodeIndex] = temp;
				dataArray[nodeIndex].index = nodeIndex;

				preserveHeapProperty(parentIndex);
			}
		}
	}

	private int mincompare(int priority, int priority2) {
		if (priority > priority2) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Utility method to print data in Binary Heap
	 */
	public void printData() {
		for (Entry<K> element : dataArray) {
			System.out.println(element);
		}
		System.out.println();
	}

	public void printMap() {

		for (K key : entryMapper.keySet()) {
			System.out.print((entryMapper.get(key).index) + ":"
					+ (entryMapper.get(key).priority) + " ");
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

	@SuppressWarnings("unchecked")
	public PriorityQueue(int capacity) {
		super();
		this.heapCapacity = capacity;
		this.dataArray = new Entry[heapCapacity];
	}

	public static void main(String[] args) {

		PriorityQueue<Edge> bh = null;
		try {
			bh = new PriorityQueue<Edge>(10);

			int[] input = { 23, 17, 14, 6, 13, 10, 1, 5, 7, 12 };

			Edge[] edgeArray = new Edge[input.length];

			int i = 0;

			for (int weight : input) {
				Edge edge = new Edge();
				edge.setWeight(weight);
				// bh.insertIntoHeap(edge, edge.getWeight());
				edgeArray[i] = edge;
				i++;
			}

			bh.setDataArray(edgeArray, input);

			// bh.printData();
			bh.printMap();
			bh.buildHeap();
			bh.printMap();

			bh.decreaseKey(edgeArray[0], 0);
			bh.printMap();

			for (int i1 = 0; i1 < input.length; i1++) {
				Edge e = (Edge) bh.retrieveAndDeleteMin();
				System.out.print(e.getWeight() + " ");
			}
			//
			// System.out.println();
			//
			// bh.printData();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int getHeapSize() {
		return heapSize;
	}

	public Map<K, Entry<K>> getEntryMapper() {
		return entryMapper;
	}

}
