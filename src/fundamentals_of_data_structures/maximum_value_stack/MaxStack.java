package fundamentals_of_data_structures.maximum_value_stack;

import java.util.*;

/**
 * Specializes the stack data structure for comparable elements, and provides a
 * method for determining the maximum element on the stack in O(1) time.
 * 
 * @author jameselder
 */
public class MaxStack<E extends Comparable<E>> extends Stack<E> {

	private Stack<E> stack;
	private Stack<E> maxStack;

	public MaxStack() {
		stack = new Stack<>();
		maxStack = new Stack<>();
	}

	/* must run in O(1) time */
	public E push(E element) {
		this.stack.push(element);
		if (this.maxStack.isEmpty()) {
			this.maxStack.push(element);
		} else {
			E max = this.maxStack.pop();
			if (max.compareTo(element) >= 0) {
				this.maxStack.push(max);
				this.maxStack.push(max);
			} else {
				this.maxStack.push(max);
				this.maxStack.push(element);
			}
		}
		return element;
	}

	/* @exception EmptyStackException if this stack is empty. */
	/* must run in O(1) time */
	public synchronized E pop() {
		this.maxStack.pop();
		return this.stack.pop();
	}

	/* Returns the maximum value currenctly on the stack. */
	/* @exception EmptyStackException if this stack is empty. */
	/* must run in O(1) time */
	public synchronized E max() {
		return this.maxStack.peek();
	}
}
