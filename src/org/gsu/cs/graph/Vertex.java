package org.gsu.cs.graph;

import org.gsu.cs.graph.util.Color;

/**
 * Class to repesent Vertex of the Graph
 * 
 * @author lokesh
 * 
 */
public class Vertex {
	private int vertexIndex;
	private Color color = Color.WHITE;
	private Vertex predecessor = null;
	private int distanceFromSource = Integer.MAX_VALUE;

	public int getVertexIndex() {
		return vertexIndex;
	}

	public void setVertexIndex(int vertexIndex) {
		this.vertexIndex = vertexIndex;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Vertex getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Vertex predecessor) {
		this.predecessor = predecessor;
	}

	public int getDistanceFromSource() {
		return distanceFromSource;
	}

	public void setDistanceFromSource(int distanceFromSource) {
		this.distanceFromSource = distanceFromSource;
	}

	@Override
	public String toString() {
		return "Vertex [vertexIndex=" + vertexIndex + ", color=" + color
				+ ", predecessor=" + predecessor + ", distanceFromSource="
				+ distanceFromSource + "]";
	}

}
