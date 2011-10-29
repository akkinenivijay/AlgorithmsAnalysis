package org.gsu.cs.ds;

public class DisjointSetForest<K> {

	public Node<K> makeSet(K representative) {
		return new Node<K>(representative);
	}

	public Node<K> union(Node<K> element1, Node<K> element2) {
		return link(findSet(element1), findSet(element2));
	}

	private Node<K> link(Node<K> node1, Node<K> node2) {
		if (node1.rank > node2.rank) {
			node2.parent = node1;
			return node2;
		} else {
			node1.parent = node2;
			if (node1.rank == node2.rank) {
				node2.rank = node2.rank + 1;
			}
			return node1;
		}
	}

	public Node<K> findSet(Node<K> node) {
		if (node != node.parent) {
			node.parent = findSet(node.parent);
		}
		return node.parent;
	}

	public static void main(String[] args) {
		System.out.println("Testing the disjoint forest ");
		Node[] A = { new Node<Integer>(1), new Node<Integer>(2),
				new Node<Integer>(3), new Node<Integer>(4),
				new Node<Integer>(5) };

		for (int i = 0; i < A.length; i++) {
			System.out.println(A[i]);
		}

		System.out.println();

		DisjointSetForest<Integer> forest = new DisjointSetForest<Integer>();

		Node<Integer> tempSet = forest.union(A[0], A[1]);
		A[0] = tempSet;
		A[1] = tempSet;

		Node<Integer> tempSet1 = forest.union(A[1], A[2]);
		A[1] = tempSet1;
		A[2] = tempSet1;

		for (int i = 0; i < A.length; i++) {
			System.out.println(A[i]);
		}

	}

}
