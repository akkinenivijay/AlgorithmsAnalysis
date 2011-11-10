package org.gsu.cs.graph.util;

import java.util.Random;

import org.gsu.cs.graph.AdjacencyListGraph;
import org.gsu.cs.graph.Edge;
import org.gsu.cs.graph.flow.Dinics;
import org.gsu.cs.graph.flow.EdmondKarps;

public class MaximumFlowBenchMarker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] adjacencyMatrix = null;
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
					long dinicsTime = 0;
					long karpsTime = 0;
					for (int repeat = 0; repeat < 5; repeat++) {

						adjacencyMatrix = new int[numberOfVertices][numberOfVertices];

						AdjacencyListGraph G = new AdjacencyListGraph(
								numberOfVertices);

						populateEdges(random, numberOfVertices, numberOfEdges,
								G, adjacencyMatrix);

						System.gc();

						Dinics din = new Dinics();
						long startTime = System.nanoTime();
						din.execute(G, 0, numberOfVertices - 1);
						long endTime = System.nanoTime();
						dinicsTime = dinicsTime + (endTime - startTime);
						System.gc();

						startTime = System.nanoTime();
						EdmondKarps ek = new EdmondKarps();
						ek.execute(G, 0, numberOfVertices - 1);
						endTime = System.nanoTime();

						karpsTime = karpsTime + (endTime - startTime);
						System.gc();

					}
					System.out.print(dinicsTime / 5 + "\t" + karpsTime);
					System.out.println();
				}
				numberOfEdges = factor * numberOfVertices;
				factor = factor * 2;
			}

			System.out.println();
			power++;
		}

	}

	private static void populateEdges(Random random, int numberOfVertices,
			int numberOfEdges, AdjacencyListGraph g, int[][] adjacencyMatrix) {

		for (int i = 0; i < numberOfVertices - 1; i++) {
			int weight = random.nextInt(1001);
			if (weight == 0) {
				weight = 1;
			}
			adjacencyMatrix[i][i + 1] = weight;
			g.addEdge(new Edge(i, i + 1, weight));
		}

		int index = 0;
		while (index < numberOfEdges - numberOfVertices) {
			boolean validate = true;
			int u = random.nextInt(numberOfVertices);
			int v = random.nextInt(numberOfVertices);
			int w = random.nextInt(1000);

			while (validate == true) {
				if (adjacencyMatrix[u][v] == 0) {
					adjacencyMatrix[u][v] = w;
					g.addEdge(new Edge(u, v, w));
					validate = false;
				} else {
					u = random.nextInt(numberOfVertices);
					v = random.nextInt(numberOfVertices);
				}
			}
			index++;
		}

	}
}
