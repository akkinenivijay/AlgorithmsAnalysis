package org.gsu.cs.spanning;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import org.gsu.cs.ds.BinaryHeap;
import org.gsu.cs.ds.DisjointSetForest;
import org.gsu.cs.ds.Node;
import org.gsu.cs.graph.Edge;
import org.gsu.cs.graph.util.EdgeComparator;

/**
 * 
 * @author Vijay Akkineni
 * 
 */
public class Kruskal {

	private Edge[] edgeArray;
	private Node<Integer>[] vertexArray;
	private Queue<Edge> queue;

	private void execute(int capacity, Comparator comparator) {

		BinaryHeap<Edge> heap = new BinaryHeap<Edge>(capacity);
		heap.setComparator(comparator);
		queue = new LinkedList<Edge>();

		DisjointSetForest<Integer> forest = new DisjointSetForest<Integer>();
		heap.setData(edgeArray);
		heap.setHeapSize(edgeArray.length);
		heap.buildHeap();

		int edgeSize = 0;
		int vertexSize = 0;

		while (edgeSize < edgeArray.length && vertexSize <= vertexArray.length) {
			Edge edge = heap.retrieveAndDeleteMin();
			Node<Integer> from = forest.findSet(vertexArray[edge.getFrom()]);
			Node<Integer> to = forest.findSet(vertexArray[edge.getTo()]);
			if (from != to) {
				vertexSize = vertexSize + 1;
				Node<Integer> tempSet = forest.union(from, to);
				vertexArray[edge.getFrom()] = tempSet;
				vertexArray[edge.getTo()] = tempSet;
				queue.add(edge);
			}
			edgeSize++;
		}

		System.out.println();

		for (Edge node : queue) {
			System.out.println(node.toString());
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Edge[] edgeArray = new Edge[9];

		Edge edge = new Edge();
		edge.setWeight(4);
		edge.setFrom(0);
		edge.setTo(1);
		edgeArray[0] = edge;

		Edge edge1 = new Edge();
		edge1.setWeight(7);
		edge1.setFrom(1);
		edge1.setTo(2);
		edgeArray[1] = edge1;

		Edge edge2 = new Edge();
		edge2.setWeight(5);
		edge2.setFrom(2);
		edge2.setTo(3);
		edgeArray[2] = edge2;

		Edge edge3 = new Edge();
		edge3.setWeight(2);
		edge3.setFrom(3);
		edge3.setTo(4);
		edgeArray[3] = edge3;

		Edge edge4 = new Edge();
		edge4.setWeight(6);
		edge4.setFrom(4);
		edge4.setTo(5);
		edgeArray[4] = edge4;

		Edge edge5 = new Edge();
		edge5.setWeight(1);
		edge5.setFrom(5);
		edge5.setTo(0);
		edgeArray[5] = edge5;

		Edge edge6 = new Edge();
		edge6.setWeight(3);
		edge6.setFrom(5);
		edge6.setTo(1);
		edgeArray[6] = edge6;

		Edge edge7 = new Edge();
		edge7.setWeight(11);
		edge7.setFrom(5);
		edge7.setTo(2);
		edgeArray[7] = edge7;

		Edge edge8 = new Edge();
		edge8.setWeight(3);
		edge8.setFrom(4);
		edge8.setTo(2);
		edgeArray[8] = edge8;

		Node[] vertexArray = { new Node<Integer>(0), new Node<Integer>(1),
				new Node<Integer>(2), new Node<Integer>(3),
				new Node<Integer>(4), new Node<Integer>(5) };

		Kruskal kr = new Kruskal();

		kr.setEdgeArray(edgeArray);
		kr.setVertexArray(vertexArray);

		kr.execute(edgeArray.length, new EdgeComparator());

	}

	public Edge[] getEdgeArray() {
		return edgeArray;
	}

	public void setEdgeArray(Edge[] edgeArray) {
		this.edgeArray = edgeArray;
	}

	public Node<Integer>[] getVertexArray() {
		return vertexArray;
	}

	public void setVertexArray(Node<Integer>[] vertexArray) {
		this.vertexArray = vertexArray;
	}

}
