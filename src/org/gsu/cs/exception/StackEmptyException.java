package org.gsu.cs.exception;

/**
 * EXCEPTION THAT WILL BE THROWN when stack is empty
 * 
 * @author lokesh
 * 
 */
public class StackEmptyException extends Exception {

	private static final long serialVersionUID = -5200220843104119506L;

	public StackEmptyException() {
		super();
	}

	public StackEmptyException(String message) {
		super(message);
	}

}
