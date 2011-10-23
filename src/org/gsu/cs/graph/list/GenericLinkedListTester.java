package org.gsu.cs.graph.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GenericLinkedListTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GenericLinkedList<Integer> gll = new GenericLinkedList<Integer>();

		Iterator<Integer> iterator = null;

		try {
			iterator = gll.iterator();
		} catch (UnsupportedOperationException uoe) {
			System.out
					.println("Throwing UnSupported Operation as expected as the there are not elements in the list");
		}

		gll.add(100);
		gll.add(200);
		gll.add(300);
		gll.add(400);
		gll.add(500);
		gll.add(600);

		iterator = gll.iterator();

		try {
			iterator.remove();
		} catch (UnsupportedOperationException e) {
			System.out
					.println("Throwing Unsupported Operation Exception as expected");
		}

		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		try {
			iterator.next();
		} catch (NoSuchElementException e) {
			System.out
					.println("Throwing No Such Element Exception when there are no elements");
		}

		// This shouldnt work as there are no elemtns in the current iterator
		// left to iterate, need to instantiat e another iterator
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		iterator = gll.iterator();

		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

}
