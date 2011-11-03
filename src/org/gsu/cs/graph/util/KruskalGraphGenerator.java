package org.gsu.cs.graph.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.gsu.cs.ds.Node;
import org.gsu.cs.graph.Edge;
import org.gsu.cs.spanning.Kruskal;
import org.gsu.cs.spanning.Prim;

/**
 * An utility class to generate graphs
 * 
 * @author Vijay Akkineni
 * 
 */
public class KruskalGraphGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Random random = new Random();
		int numberOfVertices = 0;
		int numberOfEdges = 0;

		int power = 4;
		int maxPower = 9;

		while (power < maxPower) {
			numberOfVertices = (int) Math.pow(2.0, power);
			numberOfEdges = 0;
			int factor = 1;
			while (numberOfEdges <= (numberOfVertices * (numberOfVertices - 1)) / 2) {
				if (numberOfEdges > 0) {
					System.out.print(numberOfEdges / numberOfVertices + "\t");
					long totalTime = 0;
					for (int repeat = 0; repeat < 5; repeat++) {

						Node<Integer>[] vertexArray = new Node[numberOfVertices];

						for (int vertex = 0; vertex < numberOfVertices; vertex++) {
							vertexArray[vertex] = new Node<Integer>(vertex);
						}

						int[][] adjacencyMatrix = new int[numberOfVertices][numberOfVertices];

						Edge[] edgeArray = populateEdges(random,
								numberOfVertices, numberOfEdges,
								adjacencyMatrix);

						System.gc();

						Kruskal kruskal = new Kruskal();
						kruskal.setEdgeArray(edgeArray);
						kruskal.setVertexArray(vertexArray);
						long startTime = System.nanoTime();
						kruskal.execute();
						long endTime = System.nanoTime();
						totalTime = totalTime + (endTime - startTime);
						System.gc();
					}
					System.out.print(totalTime / 5);
					System.out.println();
				}
				numberOfEdges = factor * numberOfVertices;
				factor = factor * 2;
			}

			System.out.println();
			power++;
		}
	}

	private static Edge[] populateEdges(Random random, int numOfVertices,
			int numberOfEdges, int[][] adjacentMatrix) {
		for (int i = 0; i < numOfVertices - 1; i++) {
			int weight = random.nextInt(1001);
			if (weight == 0) {
				weight = 1;
			}
			adjacentMatrix[i][i + 1] = weight;
		}

		int index = 0;
		while (index < numberOfEdges - numOfVertices) {
			boolean validate = true;
			int u = random.nextInt(numOfVertices);
			int v = random.nextInt(numOfVertices);
			int w = random.nextInt(1000);

			while (validate == true) {
				if (adjacentMatrix[u][v] == 0) {
					adjacentMatrix[u][v] = w;
					validate = false;
				} else {
					u = random.nextInt(numOfVertices);
					v = random.nextInt(numOfVertices);
				}
			}
			index++;
		}

		List<Edge> edgeList = new ArrayList<Edge>();

		int count = 0;
		for (int u = 0; u < numOfVertices; u++) {
			for (int v = 0; v < numOfVertices; v++) {
				if (u != v && u <= v)
					if (adjacentMatrix[u][v] != 0) {
						Edge edge = new Edge();
						edge.setWeight(adjacentMatrix[u][v]);
						edge.setFrom(u);
						edge.setTo(v);
						edgeList.add(edge);
						count++;
					}
			}
		}

		Edge[] edgeArray = new Edge[count];
		int idx = 0;
		for (Edge e : edgeList) {
			edgeArray[idx] = e;
			idx++;
		}

		return edgeArray;
	}
}
