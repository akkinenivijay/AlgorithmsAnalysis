package org.gsu.cs.graph;

import java.util.Random;

import org.gsu.cs.graph.datatype.GenericLinkedList;

public class AdjacencyListGraph {

	private int vertices;
	private int edges = 0;
	private GenericLinkedList<Edge>[] adjacencyList;

	public AdjacencyListGraph(int vertices) {
		super();
		this.vertices = vertices;
		initializeAdjacencyList(vertices);
	}

	@SuppressWarnings("unchecked")
	private void initializeAdjacencyList(int vertices) {
		adjacencyList = (GenericLinkedList<Edge>[]) new GenericLinkedList[vertices];
		for (int vertex = 0; vertex < vertices; vertex++) {
			adjacencyList[vertex] = new GenericLinkedList<Edge>();
		}

	}

	/**
	 * Random graph with vertices and Edges as integer input
	 * 
	 * @param vertices
	 * @param edges
	 */
	public AdjacencyListGraph(int vertices, int edges) {
		this(vertices);
		Random random = new Random();
		for (int edge = 0; edge < edges; edge++) {
			int u = random.nextInt(vertices);
			int v = random.nextInt(vertices);

			// System.out.println(u + " , " +v);

			// cpacaity limit is set to 10
			int capacity = random.nextInt(10);
			addEdge(new Edge(u, v, capacity));
		}
	}

	public AdjacencyListGraph(int vertices, int edges, String custom) {
		this(vertices);
		// Random random = new Random();
		// for (int edge = 0; edge < 4; edge++) {
		// int u = random.nextInt(vertices);
		// int v = random.nextInt(vertices);
		// int capacity = random.nextInt(10);
		// addEdge(new Edge(u, v, capacity));
		// }

		addEdge(new Edge(0, 1, 3));
		addEdge(new Edge(0, 2, 2));
		addEdge(new Edge(1, 2, 3));
		addEdge(new Edge(2, 3, 4));
		addEdge(new Edge(1, 3, 2));
	}

	// Returns an adjacent list of a vertex
	public Iterable<Edge> adjacentList(int vertex) {
		return adjacencyList[vertex];
	}

	/**
	 * Add an edge in both adjacency List of U and V
	 * 
	 * @param e
	 */
	public void addEdge(Edge edge) {
		edges++;
		int u = edge.getFrom();
		int v = edge.getTo();
		adjacencyList[u].add(edge);
		adjacencyList[v].add(edge);
	}

	public int getVertices() {
		return vertices;
	}

	public int getEdges() {
		return edges;
	}

	// @Override
	// public String toString() {
	// return "AdjacencyListGraph [vertices=" + vertices + ", edges=" + edges
	// + ", adjacencyList=" + Arrays.toString(adjacencyList) + "]";
	// }

	@Override
	public String toString() {
		String NEWLINE = System.getProperty("line.separator");
		StringBuilder s = new StringBuilder();
		s.append(vertices + " " + edges + NEWLINE);
		for (int v = 0; v < vertices; v++) {
			s.append(v + ":  ");
			for (Edge e : adjacencyList[v]) {
				if (e.getTo() != v)
					s.append(e + "  ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

	public static void main(String[] args) {
		AdjacencyListGraph G = new AdjacencyListGraph(4, 6, "");
		System.out.println(G);
	}

}
