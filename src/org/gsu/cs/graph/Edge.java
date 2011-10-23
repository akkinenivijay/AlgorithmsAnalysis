/**
 * 
 */
package org.gsu.cs.graph;

/**
 * @author Vijay Akkineni
 * 
 */
public class Edge {

	private int from;
	private int to;
	private int capacity;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Edge [from=" + from + ", to=" + to + ", capacity=" + capacity
				+ "]";
	}

}
