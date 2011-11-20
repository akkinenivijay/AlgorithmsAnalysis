package org.gsu.cs.ds;

import org.gsu.cs.exception.StackEmptyException;

/**
 * Interface for the stack
 * 
 * @author lokesh
 * 
 * @param <K>
 */
public interface Stack<K> {

	public int size();

	public boolean isEmpty();

	public K top() throws StackEmptyException;

	public void push(K element);

	public K pop() throws StackEmptyException;
}
