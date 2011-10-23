package org.gsu.cs.graph.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GenericLinkedList<K> implements Iterable<K> {

	private int size = 0;
	private Node first;

	public GenericLinkedList() {
		super();
		this.size = 0;
		this.first = null;
	}

	public void add(K item) {

		Node oldFirst = first;
		first = new Node();
		first.setItem(item);
		first.setNext(oldFirst);

		size++;
	}

	@Override
	public Iterator<K> iterator() {
		if (first == null) {
			throw new UnsupportedOperationException(
					"Cannot Instantiate Iterator before adding elements");
		}
		return new ListIterator();
	}

	public boolean isEmpty() {
		return first == null;
	}

	private class ListIterator implements Iterator<K> {
		private Node current = null;

		public ListIterator() {
			super();
			current = first;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public K next() {
			if (!hasNext())
				throw new NoSuchElementException();
			System.out.println(current);
			K item = current.getItem();
			current = current.getNext();
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"Remove operation is not supported on this iterator");

		}
	}

	private class Node {
		private K item;
		private Node next = null;;

		public K getItem() {
			return item;
		}

		public void setItem(K item) {
			this.item = item;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [item=" + item + ", next=" + next + "]";
		}

	}

	public int size() {
		return size;
	}

}
