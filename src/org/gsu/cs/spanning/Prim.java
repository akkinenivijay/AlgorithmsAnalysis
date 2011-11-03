/**
 * 
 */
package org.gsu.cs.spanning;

import org.gsu.cs.ds.PriorityQueue;
import org.gsu.cs.graph.Vertex;

/**
 * @author Vijay Akkineni
 * 
 */
public class Prim {

	Vertex[] vertexArray;

	int[] priorityArray;

	int numberOfVertices;

	/**
	 * Execute the prims algorithm using the priority queue data structure
	 * 
	 * @param numberOfVertices
	 *            Number of vertices in the graph
	 * @param vertexArray
	 *            Array of Vertex Objects
	 * @param priorityArray
	 *            Array of priorities of vertices (Source will have priority 0
	 *            and others priority will be set to Max of Integer
	 *            value(infinity))
	 * @param adjacencyMatrix
	 *            An adjacency matrix representation to retrieve weights in O(1)
	 *            time
	 */
	public void execute(int[][] adjacencyMatrix) {
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(numberOfVertices);
		pq.setDataArray(vertexArray, priorityArray);

		pq.buildHeap();

		pq.printData();

		while (pq.getHeapSize() != 0) {
			Vertex sourceVertex = pq.retrieveAndDeleteMin();
			int u = sourceVertex.getVertexIndex();

			for (int v = 0; v < adjacencyMatrix[u].length; v++) {

				if (adjacencyMatrix[u][v] != 0) {
					if (pq.getEntryMapper().containsKey(vertexArray[v])
							&& adjacencyMatrix[u][v] < priorityArray[v]) {
						vertexArray[v].setPredecessor(sourceVertex);
						priorityArray[v] = adjacencyMatrix[u][v];
						pq.decreaseKey(vertexArray[v], priorityArray[v]);
					}
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int numberOfVertices = 9;

		int[][] adjacencyMatrix = new int[numberOfVertices][numberOfVertices];

		testData(adjacencyMatrix);

		Prim prim = new Prim(numberOfVertices);

		prim.execute(adjacencyMatrix);

		prim.printMST();

	}

	/**
	 * Prints the minimum spanning tree
	 */
	public void printMST() {
		for (Vertex vertex : vertexArray) {
			System.out.print(vertex.getVertexIndex());
			if (vertex.getPredecessor() != null)
				System.out
						.print("-" + vertex.getPredecessor().getVertexIndex());
			System.out.println();
		}
	}

	public Prim(int numberOfVertices) {
		super();
		this.numberOfVertices = numberOfVertices;
		// Vertex Array
		vertexArray = new Vertex[numberOfVertices];
		// Priority Array for the vertices
		priorityArray = new int[numberOfVertices];

		for (int count = 0; count < numberOfVertices; count++) {
			Vertex vertex = new Vertex();
			vertex.setVertexIndex(count);
			vertexArray[count] = vertex;
			if (count != 0)
				priorityArray[count] = Integer.MAX_VALUE;
			else
				priorityArray[count] = 0;
		}
	}

	private static void testData(int[][] adjacencyMatrix) {
		adjacencyMatrix[0][1] = 4;
		adjacencyMatrix[1][0] = 4;

		adjacencyMatrix[1][2] = 8;
		adjacencyMatrix[2][1] = 8;

		adjacencyMatrix[2][3] = 7;
		adjacencyMatrix[3][2] = 7;

		adjacencyMatrix[3][4] = 9;
		adjacencyMatrix[4][3] = 9;

		adjacencyMatrix[4][5] = 10;
		adjacencyMatrix[5][4] = 10;

		adjacencyMatrix[5][6] = 2;
		adjacencyMatrix[6][5] = 2;

		adjacencyMatrix[6][7] = 1;
		adjacencyMatrix[7][6] = 1;

		adjacencyMatrix[7][8] = 7;
		adjacencyMatrix[8][7] = 7;

		adjacencyMatrix[7][1] = 11;
		adjacencyMatrix[1][7] = 11;

		adjacencyMatrix[8][2] = 2;
		adjacencyMatrix[2][8] = 2;

		adjacencyMatrix[2][5] = 4;
		adjacencyMatrix[5][2] = 4;

		adjacencyMatrix[3][5] = 14;
		adjacencyMatrix[5][3] = 14;

		adjacencyMatrix[6][8] = 6;
		adjacencyMatrix[8][6] = 6;

		adjacencyMatrix[7][0] = 8;
		adjacencyMatrix[0][7] = 8;

		System.out.println();
	}

}
