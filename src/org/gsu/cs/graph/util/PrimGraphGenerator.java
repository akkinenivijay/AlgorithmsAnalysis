package org.gsu.cs.graph.util;

import java.util.Random;

import org.gsu.cs.spanning.Prim;

/**
 * An utility class to generate graphs
 * 
 * @author lokesh
 * 
 */
public class PrimGraphGenerator {

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
					System.out.print(numberOfVertices + ":" + numberOfEdges
							+ " ");
					long totalTime = 0;
					for (int repeat = 0; repeat < 5; repeat++) {
						int[][] adjacencyMatrix = new int[numberOfVertices][numberOfVertices];

						populateEdges(random, numberOfVertices, numberOfEdges,
								adjacencyMatrix);

						System.gc();

						Prim prim = new Prim(numberOfVertices);
						long startTime = System.nanoTime();
						prim.execute(adjacencyMatrix);
						long endTime = System.nanoTime();
						totalTime = totalTime + (endTime - startTime);
						System.gc();
					}
					System.out.print(" " + totalTime / 5);
					System.out.println();
				}
				numberOfEdges = factor * numberOfVertices;
				factor = factor * 2;
			}

			System.out.println();
			power++;
		}
	}

	private static void populateEdges(Random random, int numOfVertices,
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
	}
}
