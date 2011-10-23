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

	private int from;
	private int to;
	private int capacity;

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
		return "Edge [from=" + from + ", to=" + to + ", capacity=" + capacity
				+ "]";
	}

}
