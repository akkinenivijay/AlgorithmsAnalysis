package org.gsu.cs.graph;

import java.util.Random;

import org.gsu.cs.graph.datatype.GenericLinkedList;
import org.gsu.cs.graph.flow.Dinics;

/**
 * Graph Representation with a Adjacency List Data Structure
 * 
 * @author Vijay Akkineni
 * 
 */
public class DinicAdjacencyListGraph {

	private int vertices;
	private int edges = 0;
	private GenericLinkedList<Edge>[] adjacencyList;

	/**
	 * Basic constructor
	 * 
	 * @param vertices
	 */
	public DinicAdjacencyListGraph(int vertices) {
		super();
		this.vertices = vertices;
		initializeAdjacencyList(vertices);
	}

	@SuppressWarnings("unchecked")
	/**
	 * To initlize the adjacency list
	 * @param vertices
	 */
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
	 *            Number of Vertices
	 * @param edges
	 *            Number of Edges
	 * @param capacity
	 *            Max Flow capacity
	 */
	public DinicAdjacencyListGraph(int vertices, int edges, int capacity) {
		this(vertices);
		Random random = new Random();
		for (int edge = 0; edge < edges; edge++) {
			int u = random.nextInt(vertices);
			int v = random.nextInt(vertices);

			// System.out.println(u + " , " + v);

			// cpacaity limit is set to 10
			capacity = random.nextInt(1000);
			addEdge(new Edge(u, v, capacity));
		}
	}

	/**
	 * Constructor for test graph to test the programs.
	 * 
	 * @param vertices
	 * @param edges
	 * @param custom
	 */
	public DinicAdjacencyListGraph(int vertices, int edges, String custom) {
		this(vertices);
		// addEdge(new Edge(0, 1, 10));
		// addEdge(new Edge(0, 2, 10));
		// addEdge(new Edge(1, 2, 2));
		// addEdge(new Edge(1, 3, 4));
		// addEdge(new Edge(1, 4, 8));
		// addEdge(new Edge(2, 4, 9));
		// addEdge(new Edge(3, 5, 10));
		// addEdge(new Edge(4, 3, 6));
		// addEdge(new Edge(4, 5, 10));

		addEdge(new Edge(0, 1, 3));
		addEdge(new Edge(0, 2, 2));
		addEdge(new Edge(1, 2, 3));
		addEdge(new Edge(2, 3, 4));
		addEdge(new Edge(1, 3, 2));
	}

	/**
	 * Returns an adjacent list of a vertex
	 * 
	 * @param vertex
	 * @return
	 */
	public Iterable<Edge> adjacentList(int vertex) {
		return adjacencyList[vertex];
	}

	/**
	 * Add an edge in only the source vertex as this is a directed graph
	 * 
	 * @param e
	 */
	public void addEdge(Edge edge) {
		edges++;
		int u = edge.getI();
		int v = edge.getJ();
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
	/**
	 * Provides a graph representation to String this is for testing purposes 
	 */
	public String toString() {
		String NEWLINE = System.getProperty("line.separator");
		StringBuilder s = new StringBuilder();
		s.append("vertices: " + vertices + " " + "Edges: " + edges + NEWLINE);
		for (int v = 0; v < vertices; v++) {
			s.append(v + ":  ");
			// dif (adjacencyList[v].size() > 0)
			for (Edge edge : adjacencyList[v]) {
				if (edge.getJ() != v)
					s.append(edge + "  ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

	public static void main(String[] args) {
		DinicAdjacencyListGraph G = new DinicAdjacencyListGraph(4, 5, "");
		// System.out.println(G);
		// System.out.println(G.adjacentList(5));
		//
		// for (Edge e : G.adjacentList(4)) {
		// System.out.println(e);
		// }
		Dinics dn = new Dinics();
		//dn.execute(G, 0, 3);
	}

}
