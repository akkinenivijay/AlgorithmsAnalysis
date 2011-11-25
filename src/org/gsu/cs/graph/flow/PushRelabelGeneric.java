/**
 * 
 */
package org.gsu.cs.graph.flow;

import java.util.Arrays;

/**
 * @author Vijay Akkineni
 * 
 */
public class PushRelabelGeneric {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PushRelabelGeneric plg = new PushRelabelGeneric(7, 0, 6);
		plg.addEdge(0, 1, 5);
		plg.addEdge(0, 2, 10);
		plg.addEdge(0, 4, 4);
		plg.addEdge(1, 6, 3);
		plg.addEdge(1, 3, 1);
		plg.addEdge(2, 3, 7);
		plg.addEdge(2, 4, 3);
		plg.addEdge(2, 5, 7);
		plg.addEdge(3, 6, 5);
		plg.addEdge(4, 5, 6);
		plg.addEdge(5, 6, 4);

		plg.initializePreFlow();

		System.out.println(plg.excess[plg.source]);
		System.out.println(plg.excess[1]);

	}

	public void initializePreFlow() {
		for (int v = 0; v < graph[source].length; v++) {
			if (graph[source][v] == 1) {
				flow[source][v] = capacity[source][v];
				flow[v][source] = -1 * capacity[v][source];
				excess[v] = capacity[source][v];
				excess[source] = excess[source] - capacity[source][v];
				capacity[source][v] = capacity[source][v] - flow[source][v];
				capacity[v][source] = capacity[v][source] - flow[v][source];
			}
		}
	}

	public void push(int u, int v) {
		int temp = Math.min(excess[u], capacity[u][v]);
		flow[u][v] = flow[u][v] + temp;
		flow[v][u] = -1 * flow[u][v];
		excess[u] = excess[u] - temp;
		excess[v] = excess[v] + temp;
		capacity[u][v] = capacity[u][v] - flow[u][v];
		capacity[v][u] = capacity[v][u] - flow[v][u];
	}

	public void relabel(int u) {

	}

	public void addEdge(int source, int destination, int cap) {
		capacity[source][destination] = cap;
		graph[source][destination] = 1;
		graph[destination][source] = 1;
	}

	private int[] height;
	private int[][] flow;
	private int[][] capacity;
	private int[][] graph;
	private int[] excess;
	private int numOfVertices;
	private int source;
	private int sink;

	public PushRelabelGeneric(int numOfVertices, int source, int sink) {
		super();
		this.numOfVertices = numOfVertices;
		this.source = source;
		this.sink = sink;
		height = new int[numOfVertices];
		height[source] = numOfVertices;
		height[sink] = 0;

		flow = new int[numOfVertices][numOfVertices];
		capacity = new int[numOfVertices][numOfVertices];
		excess = new int[numOfVertices];
		graph = new int[numOfVertices][numOfVertices];

		initializeGraph();

		System.out.println(graph[numOfVertices - 1][numOfVertices - 1]);
	}

	private void initializeGraph() {
		for (int i = 0; i < numOfVertices; i++) {
			for (int j = 0; j < numOfVertices; j++) {
				graph[i][j] = Integer.MAX_VALUE;
			}
		}
	}

}
