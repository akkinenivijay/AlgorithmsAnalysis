/**
 * 
 */
package org.gsu.cs.ds;

import java.lang.reflect.Array;

import org.gsu.cs.exception.StackEmptyException;

/**
 * @author lokesh
 * 
 */
public class ArrayBasedStackImpl<K> implements Stack<K> {

	// Default capacity
	private static final int DEFAULT_CAPACITY = 1024;

	private int capacity = 0;

	private int top = -1;

	private K[] data;

	@SuppressWarnings("unchecked")
	public ArrayBasedStackImpl(Class<K> clazz) {
		super();
		data = (K[]) Array.newInstance(clazz, DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public ArrayBasedStackImpl(Class<K> clazz, int capacity) {
		super();
		this.capacity = capacity;
		data = (K[]) Array.newInstance(clazz, capacity);
	}

	@Override
	public int size() {
		return top + 1;
	}

	@Override
	public boolean isEmpty() {
		if (size() <= 0)
			return false;
		else
			return true;
	}

	@Override
	public K top() throws StackEmptyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void push(K element) {
		// TODO Auto-generated method stub

	}

	@Override
	public K pop() throws StackEmptyException {
		// TODO Auto-generated method stub
		return null;
	}

}
