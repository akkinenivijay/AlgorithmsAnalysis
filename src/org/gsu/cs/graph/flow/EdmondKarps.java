package org.gsu.cs.graph.flow;

import java.util.LinkedList;
import java.util.Queue;

import org.gsu.cs.graph.AdjacencyListGraph;
import org.gsu.cs.graph.Edge;

/**
 * Implementation of Edmond Karps variation of Ford Fulkerson algorithm using
 * Breadth First Search. The runtime of the algorithm is guaranteed to be O(V
 * E*E)
 * 
 * @author Vijay Akkineni
 * 
 */
public class EdmondKarps {

	private boolean[] marked;
	private Edge[] edgeTo;
	private double value;

	/**
	 * Main method to start the Ford Fulkerson algorithm
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int vertices = 100;
		int edges = 4000;
		int capacity = 1000;
		AdjacencyListGraph G = new AdjacencyListGraph(vertices, edges, capacity);
		// System.out.println(G);
		EdmondKarps ff = new EdmondKarps();
		ff.execute(G, 0, vertices - 1);
		System.out.println("Max flow from " + 0 + " to " + 3);

		for (int v = 0; v < G.getVertices(); v++) {
			for (Edge e : G.adjacentList(v)) {
				if ((v == e.getFrom()) && e.getFlow() > 0)
					System.out.println("   " + e);
			}
		}

		System.out.println("Max flow value = " + ff.value);

	}

	/**
	 * Method to check if there is an augmenting path present in the residual
	 * Graph Gf
	 * 
	 * @param G
	 * @param s
	 * @param t
	 * @return
	 */
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

	/**
	 * Execute the Ford Fulkerson method
	 * 
	 * @param G
	 * @param s
	 * @param t
	 */
	public void execute(AdjacencyListGraph G, int s, int t) {
		value = excess(G, t);
		if (!isFeasible(G, s, t)) {
			throw new RuntimeException("Initial flow is infeasible");
		}

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

	/**
	 * return excess flow at vertex v
	 * 
	 * @param G
	 * @param u
	 * @return
	 */
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

	/**
	 * Method to check the validity o the graph if the Capacity Constraints ,
	 * Flow Conservation properties of a graph are satisfied
	 * 
	 * @param G
	 * @param s
	 * @param t
	 * @return
	 */
	private boolean isFeasible(AdjacencyListGraph G, int s, int t) {
		double minVal = 1E-11;

		// check that capacity constraints are satisfied
		for (int v = 0; v < G.getVertices(); v++) {
			for (Edge e : G.adjacentList(v)) {
				if (e.getFlow() < 0 || e.getFlow() > e.getCapacity()) {
					System.err
							.println("Capacity constraint of the flow network is not satisfied: "
									+ e);
					return false;
				}
			}
		}

		// check that net flow into a vertex equals zero, except at source and
		// sink
		if (Math.abs(value + excess(G, s)) > minVal) {
			System.err.println("Excess at source = " + excess(G, s));
			System.err.println("Max flow         = " + value);
			return false;
		}
		if (Math.abs(value - excess(G, t)) > minVal) {
			System.err.println("Excess at sink   = " + excess(G, t));
			System.err.println("Max flow         = " + value);
			return false;
		}
		for (int v = 0; v < G.getVertices(); v++) {
			if (v == s || v == t)
				continue;
			else if (Math.abs(excess(G, v)) > minVal) {
				System.err.println("Net flow out of " + v
						+ " doesn't equal zero");
				return false;
			}
		}
		return true;
	}

}
