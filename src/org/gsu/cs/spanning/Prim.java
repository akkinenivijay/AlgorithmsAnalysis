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

	public void execute() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int numberOfVertices = 6;
		// Vertex Array
		Vertex[] vertexArray = new Vertex[numberOfVertices];
		// Priority Array for the vertices
		int[] priorityArray = new int[numberOfVertices];

		int[][] adjacencyMatrix = new int[6][6];

		for (int count = 0; count < numberOfVertices; count++) {
			Vertex vertex = new Vertex();
			vertex.setVertexIndex(count);
			vertexArray[count] = vertex;
			if (count != 0)
				priorityArray[count] = Integer.MAX_VALUE;
			else
				priorityArray[count] = 0;
			count++;
		}

		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(numberOfVertices);
		pq.setDataArray(vertexArray, priorityArray);

		pq.buildHeap();

		while (pq.getHeapSize() != 0) {
			Vertex sourceVertex = pq.retrieveAndDeleteMin();

			System.out.println("MST:" + sourceVertex);
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

}
