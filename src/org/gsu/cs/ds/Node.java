package org.gsu.cs.ds;

public class Node<K> {
	Node<K> parent;
	int rank;
	K object;

	public Node(K x) {
		object = x;
		parent = this;
		rank = 0;
	}

	@Override
	public String toString() {
		if (parent != this)
			return "Node [parent=" + parent + ", rank=" + rank + ", object="
					+ object + "]";
		else {
			return "Node [ rank=" + rank + ", object=" + object + "]";
		}
	}
}
