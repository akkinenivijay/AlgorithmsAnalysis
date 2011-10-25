package org.gsu.cs.graph.datatype;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A Linked List implementation which can support generics
 * 
 * @author Vijay Akkineni
 * 
 * @param <K>
 *            Any Object can be passed as inputs as this is a generic place
 *            holder for types
 */
public class GenericLinkedList<K> implements Iterable<K> {

	private int size = 0;
	private Node first;

	public GenericLinkedList() {
		super();
		this.size = 0;
		this.first = null;
	}

	/**
	 * Adds a new item to the List
	 * 
	 * @param item
	 */
	public void add(K item) {

		Node oldFirst = first;
		first = new Node();
		first.setItem(item);
		first.setNext(oldFirst);

		size++;
	}

	/**
	 * Returns a new iterator and throws UnSupportedOperationException when
	 * there are no elements in the lsist
	 */
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

	/**
	 * iterator implementation to iterate over GenericLinkedList
	 * 
	 * @author lokesh
	 * 
	 */
	private class ListIterator implements Iterator<K> {
		private Node current = null;

		public ListIterator() {
			super();
			current = first;
		}

		/**
		 * return true if current is not null
		 */
		@Override
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * returns the next elements in the list and throws
		 * NoSuchElementException when the list is empty
		 */
		@Override
		public K next() {
			if (!hasNext())
				throw new NoSuchElementException();
			K item = current.getItem();
			current = current.getNext();
			return item;
		}

		/**
		 * Remvoe operation is not supported as we dont want to remove any
		 * elements in graph algorithms
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"Remove operation is not supported on this iterator");

		}
	}

	/**
	 * Supporting class for the linked list
	 * 
	 * @author lokesh
	 * 
	 */
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

	@Override
	public String toString() {
		return "GenericLinkedList [size=" + size + ", first=" + first + "]";
	}

}
