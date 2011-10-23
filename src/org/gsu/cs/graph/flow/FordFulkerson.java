package org.gsu.cs.graph.flow;

import java.util.LinkedList;
import java.util.Queue;

import org.gsu.cs.graph.AdjacencyListGraph;
import org.gsu.cs.graph.Edge;

public class FordFulkerson {

	private boolean[] marked;
	private Edge[] edgeTo;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean isAugmentingPathPresent(AdjacencyListGraph G, int s, int t) {
		edgeTo = new Edge[G.getVertices()];
		marked = new boolean[G.getVertices()];
		Queue<Integer> queue = new LinkedList<Integer>();

		queue.add(s);
		marked[s] = true;

		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (Edge edge : G.adjacentList(u)) {
				int v = edge.other(u);

				if (edge.residualCapacityTo(v) > 0) {
					if (!marked[v]) {
						edgeTo[v] = edge;
						marked[v] = true;
						queue.add(v);
					}
				}
			}
		}

		return marked[t];
	}

}
