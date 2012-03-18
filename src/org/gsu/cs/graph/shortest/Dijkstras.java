/**
 * 
 */
package org.gsu.cs.graph.shortest;

import org.gsu.cs.ds.PriorityQueue;
import org.gsu.cs.graph.Vertex;
import org.gsu.cs.spanning.Prim;

/**
 * @author lokesh
 * 
 */
public class Dijkstras {

	public Dijkstras(int numberOfVertices) {
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

	Vertex[] vertexArray;

	int[] priorityArray;

	int numberOfVertices;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int numberOfVertices = 5;

		int[][] adjacencyMatrix = new int[numberOfVertices][numberOfVertices];

		testData(adjacencyMatrix);

		Dijkstras dij = new Dijkstras(numberOfVertices);
		dij.execute(adjacencyMatrix);

	}

	public void execute(int[][] adjacencyMatrix) {
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(numberOfVertices);
		pq.setDataArray(vertexArray, priorityArray);

		pq.buildHeap();
		pq.printData();
	}

	private static void testData(int[][] adjacencyMatrix) {
		adjacencyMatrix[0][1] = 10;
		adjacencyMatrix[1][0] = Integer.MAX_VALUE;

		adjacencyMatrix[1][2] = 1;
		adjacencyMatrix[2][1] = Integer.MAX_VALUE;

		adjacencyMatrix[4][2] = 9;
		adjacencyMatrix[2][4] = Integer.MAX_VALUE;

		adjacencyMatrix[4][1] = 3;
		adjacencyMatrix[1][4] = 2;

		adjacencyMatrix[2][3] = 4;
		adjacencyMatrix[3][2] = 6;

		adjacencyMatrix[3][4] = Integer.MAX_VALUE;
		adjacencyMatrix[4][3] = 2;

		adjacencyMatrix[3][0] = 7;
		adjacencyMatrix[0][3] = Integer.MAX_VALUE;

		adjacencyMatrix[4][0] = Integer.MAX_VALUE;
		adjacencyMatrix[0][4] = 5;

	}

}
