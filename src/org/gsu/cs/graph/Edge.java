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
public class Edge {

	public Edge() {
		super();
	}

	private int from;
	private int to;
	private int weight;
	private int capacity;
	private int flow;

	public Edge(int from, int to, int capacity) {
		super();
		this.from = from;
		this.to = to;
		this.capacity = capacity;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int getFrom() {
		return from;
	}

	/**
	 * The from vertex
	 * 
	 * @param from
	 */
	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	/**
	 * The To vertex of the Edge
	 * 
	 * @param to
	 */
	public void setTo(int to) {
		this.to = to;
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
		return "Edge [from=" + from + ", to=" + to + ", weight=" + weight
				+ ", capacity=" + capacity + ", flow=" + flow + "]";
	}

	public int other(int u) {
		if (u == from)
			return to;
		else if (u == to)
			return from;
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
		if (vertex == from)
			return flow;
		else if (vertex == to)
			return capacity - flow;
		else
			throw new RuntimeException("Illegal endpoint");
	}

	public void addResidualFlowTo(int vertex, int delta) {
		if (vertex == from)
			flow -= delta;
		else if (vertex == to)
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

}
