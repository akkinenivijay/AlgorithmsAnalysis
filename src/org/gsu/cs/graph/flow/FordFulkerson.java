package org.gsu.cs.graph.flow;

import java.util.LinkedList;
import java.util.Queue;

import org.gsu.cs.graph.AdjacencyListGraph;
import org.gsu.cs.graph.Edge;

public class FordFulkerson {

	private boolean[] marked;
	private Edge[] edgeTo;
	private double value;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AdjacencyListGraph G = new AdjacencyListGraph(4, 6, "");
		System.out.println(G);
		FordFulkerson ff = new FordFulkerson();
		ff.execute(G, 0, 3);
		System.out.println("Max flow from " + 0 + " to " + 3);

		for (int v = 0; v < G.getVertices(); v++) {
			for (Edge e : G.adjacentList(v)) {
				if ((v == e.getFrom()) && e.getFlow() > 0)
					System.out.println("   " + e);
			}
		}

		System.out.println("Max flow value = " + ff.value);

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

	public void execute(AdjacencyListGraph G, int s, int t) {
		value = excess(G, t);
		// if (!isFeasible(G, s, t)) {
		// throw new RuntimeException("Initial flow is infeasible");
		// }

		// while there exists an augmenting path, use it
		while (isAugmentingPathPresent(G, s, t)) {

			// compute bottleneck capacity
			int bottle = Integer.MAX_VALUE;
			for (int v = t; v != s; v = edgeTo[v].other(v)) {
				bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
			}

			// augment flow
			for (int v = t; v != s; v = edgeTo[v].other(v)) {
				edgeTo[v].addResidualFlowTo(v, bottle);
			}

			value += bottle;
		}

	}

	// return excess flow at vertex v
	private int excess(AdjacencyListGraph G, int u) {
		int excess = 0;
		for (Edge e : G.adjacentList(u)) {
			if (u == e.getFrom())
				excess -= e.getFlow();
			else
				excess += e.getFlow();
		}
		return excess;
	}

}
