package org.gsu.cs.graph.util;

import java.util.Comparator;

import org.gsu.cs.graph.Edge;

public class EdgeComparator implements Comparator<Edge> {

	@Override
	public int compare(Edge edge1, Edge edge2) {
		if (edge1.getWeight() < edge2.getWeight())
			return -1;
		else if (edge1.getWeight() < edge2.getWeight())
			return 1;
		else
			return 0;
	}

}
