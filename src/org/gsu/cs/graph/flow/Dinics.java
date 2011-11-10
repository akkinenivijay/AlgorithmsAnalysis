package org.gsu.cs.graph.flow;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.gsu.cs.graph.DinicAdjacencyListGraph;
import org.gsu.cs.graph.Edge;

public class Dinics {

	private int[] distance;
	private Edge[] edgeTo;
	private int value;

	/**
	 * Execute the Ford Fulkerson method
	 * 
	 * @param G
	 * @param s
	 * @param t
	 */
	public void execute(DinicAdjacencyListGraph G, int s, int t) {
		int flow = 0;

		// while there exists an augmenting path, use it
		while (isAugmentingPathPresent(G, s, t)) {

			int df = DinicDFS(G, s, t, Integer.MAX_VALUE);

			System.out.println(df);
			if (df == 0)
				break;
			flow += df;

			System.out.println(flow);

		}

	}

	private int DinicDFS(DinicAdjacencyListGraph G, int s, int t, int maxValue) {
		if (maxValue == 0)
			return 0;
		if (s == t)
			return maxValue;

		int ret = 0;

		for (Edge edge : G.adjacentList(s)) {
			if (distance[edge.other(s)] == distance[s] + 1
					&& edge.residualCapacityTo(edge.getJ()) > 0) {
				int tadd = DinicDFS(G, edge.getJ(), t, Math.min(
						edge.residualCapacityTo(edge.getJ()), maxValue));
				ret = ret + tadd;
				edge.addResidualFlowTo(edge.other(edge.getI()), tadd);
				System.out.println(edge);
				// edge.setCapacity(edge.getCapacity() - tadd);
				// edge.setFlow(edge.getFlow() + tadd);
				if (edge.residualCapacityTo(edge.getJ()) == 0)
					break;
			}
		}

		return ret;
	}

	/**
	 * Method to check if there is an augmenting path present in the residual
	 * Graph Gf using BFS
	 * 
	 * @param G
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isAugmentingPathPresent(DinicAdjacencyListGraph G, int s,
			int t) {
		if (s == t)
			return false;
		Queue<Integer> queue = new LinkedList<Integer>();
		distance = new int[G.getVertices()];
		Arrays.fill(distance, Integer.MAX_VALUE);

		queue.add(s);
		distance[s] = 0;

		while (!queue.isEmpty()) {
			int u = queue.poll();
			if (u == t)
				break;
			for (Edge edge : G.adjacentList(u)) {
				int v = edge.other(u);

				if (distance[v] == Integer.MAX_VALUE
						&& (edge.residualCapacityTo(v)) > 0) {
					distance[v] = distance[edge.getI()] + 1;
					queue.add(v);
				}

			}
		}
		return distance[t] >= 0;
	}

	/**
	 * return excess flow at vertex v
	 * 
	 * @param G
	 * @param u
	 * @return
	 */
	private int excess(DinicAdjacencyListGraph G, int u) {
		int excess = 0;
		for (Edge e : G.adjacentList(u)) {
			if (u == e.getI())
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
	public boolean isFeasible(DinicAdjacencyListGraph G, int s, int t) {
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
