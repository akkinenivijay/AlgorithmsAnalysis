/**
 * 
 */
package org.gsu.cs.graph.flow;

import java.util.LinkedList;

/**
 * @author Vijay Akkineni
 * 
 */
public class PushRelabelToFront {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PushRelabelToFront plg = new PushRelabelToFront(7, 0, 6);
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
		plg.relabelToFront();

	}

	public void execute() {
		this.initializePreFlow();
		this.relabelToFront();
	}

	public void initializePreFlow() {
		for (int v = 0; v < graph[source].length; v++) {
			if (graph[source][v] == 1) {
				flow[source][v] = capacity[source][v];
				flow[v][source] = -1 * capacity[source][v];
				excess[v] = capacity[source][v];
				excess[source] = excess[source] - capacity[source][v];
				capacity[source][v] = capacity[source][v] - flow[source][v];
				capacity[v][source] = capacity[v][source] - flow[v][source];
			}
		}
	}

	public void relabelToFront() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < numOfVertices; i++) {
			if (!(i == source || i == sink))
				list.add(i);
		}

		int u = -1;

		try {
			u = list.peekFirst();
			// list.removeFirst();
			//
			// headPosition = list.indexOf(u);
		} catch (Exception e) {
			u = -1;
			e.printStackTrace();
		}
		while (u != -1) {
			// System.out.println("u :" + u);
			int oldHeight = height[u];
			discharge(u);

			// System.out.println("u :" + u + " oldHeight: " + oldHeight
			// + " currentHeight: " + height[u]);
			if (height[u] > oldHeight) {
				int tempPosition = list.indexOf(u);
				list.remove(tempPosition);
				list.addFirst(u);
			}

			int headPosition = list.indexOf(u);

			try {
				u = list.get(headPosition + 1);
			} catch (Exception e) {
				u = -1;
				// e.printStackTrace();
			}

		}
	}

	public void discharge(int u) {
		int v = -1;
		int headOfNeighbours = current[u].peek();
		int headPosition = current[u].indexOf(headOfNeighbours);
		while (excess[u] > 0) {
			try {
				v = current[u].get(headPosition);
			} catch (Exception e) {
				v = -1;
			}
			if (v == -1) {
				relabel(u);
				int headOfN = current[u].peek();
				headPosition = current[u].indexOf(headOfN);
			} else if (capacity[u][v] > 0 && height[u] == height[v] + 1) {
				push(u, v);
			} else {
				headPosition = headPosition + 1;
			}
		}
	}

	public void push(int u, int v) {
		// System.out.println(u + " : " + v);
		int temp = Math.min(excess[u], capacity[u][v]);
		flow[u][v] = flow[u][v] + temp;
		flow[v][u] = -1 * flow[u][v];
		excess[u] = excess[u] - temp;
		excess[v] = excess[v] + temp;
		capacity[u][v] = capacity[u][v] - flow[u][v];
		capacity[v][u] = capacity[v][u] - flow[v][u];
	}

	public void relabel(int u) {
		int temp = -1;
		for (int i = 0; i < graph[u].length; i++) {
			if (graph[u][i] == 1) {
				if (capacity[u][i] > 0) {
					if (temp == -1 || temp > height[i]) {
						temp = height[i];
					}
				}
			}
		}

		height[u] = 1 + temp;
	}

	public void addEdge(int source, int destination, int cap) {
		graph[source][destination] = 1;
		current[source].add(destination);
		graph[destination][source] = 1;
		current[destination].add(source);
		capacity[source][destination] = cap;
	}

	private int[] height;
	private int[][] flow;
	private int[][] capacity;
	private int[][] graph;
	private int[] excess;
	private LinkedList<Integer>[] current;
	private int numOfVertices;
	private int source;
	private int sink;

	@SuppressWarnings("unchecked")
	public PushRelabelToFront(int numOfVertices, int source, int sink) {
		super();
		this.numOfVertices = numOfVertices;
		this.source = source;
		this.sink = sink;
		height = new int[numOfVertices];
		height[source] = numOfVertices;
		height[sink] = 0;
		current = new LinkedList[numOfVertices];

		for (int k = 0; k < current.length; k++) {
			current[k] = new LinkedList<Integer>();
		}

		// current[0].add(0);

		flow = new int[numOfVertices][numOfVertices];
		capacity = new int[numOfVertices][numOfVertices];
		excess = new int[numOfVertices];
		graph = new int[numOfVertices][numOfVertices];

		initializeGraph();
	}

	private void initializeGraph() {
		for (int i = 0; i < numOfVertices; i++) {
			for (int j = 0; j < numOfVertices; j++) {
				graph[i][j] = Integer.MAX_VALUE;
			}
		}
	}

}
