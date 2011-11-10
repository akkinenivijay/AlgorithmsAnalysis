/**
 * 
 */
package org.gsu.cs.graph;

/**
 * A class to represent Edge of the graph
 * 
 * @author Vijay Akkineni
 * 
 */
public class Edge implements Comparable<Edge> {

	public Edge() {
		super();
	}

	private int i;
	private int j;
	private int weight;
	private int capacity;
	private int flow;

	public Edge(int from, int to, int capacity) {
		super();
		this.i = from;
		this.j = to;
		this.capacity = capacity;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int getI() {
		return i;
	}

	/**
	 * The from vertex
	 * 
	 * @param from
	 */
	public void setI(int from) {
		this.i = from;
	}

	public int getJ() {
		return j;
	}

	/**
	 * The To vertex of the Edge
	 * 
	 * @param to
	 */
	public void setJ(int to) {
		this.j = to;
	}

	public int getCapacity() {
		return capacity;
	}

	/**
	 * Capacity field can also be used a weights for normal graph algorithms and
	 * capacity is coined for network flow algorithms
	 * 
	 * @param capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Edge [from=" + i + ", to=" + j + ", weight=" + weight
				+ ", capacity=" + capacity + ", flow=" + flow + "]";
	}

	public int other(int u) {
		if (u == i)
			return j;
		else if (u == j)
			return i;
		else
			throw new RuntimeException("Illegal endpoint");
	}

	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}

	public int residualCapacityTo(int vertex) {
		if (vertex == i)
			return flow;
		else if (vertex == j)
			return capacity - flow;
		else
			throw new RuntimeException("Illegal endpoint");
	}

	public void addResidualFlowTo(int vertex, int delta) {
		if (vertex == i)
			flow -= delta;
		else if (vertex == j)
			flow += delta;
		else
			throw new RuntimeException("Illegal endpoint");
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge anotherEdge) {
		if (!(anotherEdge instanceof Edge)) {
			throw new IllegalArgumentException(
					"object passed to campare is not of type Edge");
		} else {
			if (this.getWeight() < ((Edge) anotherEdge).getWeight()) {
				return -1;
			}
			if (this.getWeight() == ((Edge) anotherEdge).getWeight()) {
				return -1;
			}
			if (this.getWeight() > ((Edge) anotherEdge).getWeight()) {
				return 1;
			}

		}

		return 0;
	}

}
