package org.gsu.cs.graph.util;

import java.util.Random;

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
		int numOfVertices = 0;
		int numberOfEdges = 0;

		int power = 4;
		int maxPower = 9;

		while (power < maxPower) {
			numOfVertices = (int) Math.pow(2.0, power);
			numberOfEdges = 0;
			System.out.println(numOfVertices);
			int factor = 1;
			while (numberOfEdges <= (numOfVertices * (numOfVertices - 1)) / 2) {
				if (numberOfEdges > 0) {
					System.out.print(numberOfEdges + " ");

					int[][] adjacentMatrix = new int[numOfVertices][numOfVertices];

					populateEdges(random, numOfVertices, numberOfEdges,
							adjacentMatrix);
				}
				numberOfEdges = factor * numOfVertices;
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
