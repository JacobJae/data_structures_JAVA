package fundamentals_of_data_structures.sparse_vectors;

import java.nio.channels.UnsupportedAddressTypeException;
import java.util.*;

/**
 * Represents a sparse numeric vector. Elements are comprised of a (long)
 * location index an a (double) value. The vector is maintained in increasing
 * order of location index, which facilitates numeric operations like inner
 * products (projections). Note that location indices can be any integer from 1
 * to Long.MAX_VALUE. The representation is based upon a singly-linked list. The
 * following methods are supported: iterator, getSize, getFirst, add, remove,
 * and dot, which takes the dot product of the with a second vector passed as a
 * parameter.
 * 
 * @author jameselder
 */
public class SparseNumericVector implements Iterable {

	protected SparseNumericNode head = null;
	protected SparseNumericNode tail = null;
	protected long size;

	/**
	 * Iterator
	 */
	@Override
	public Iterator<SparseNumericElement> iterator() { // iterator
		return new SparseNumericIterator(this);
	}

	/**
	 * @return number of non-zero elements in vector
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @return the first node in the list.
	 */
	public SparseNumericNode getFirst() {
		return head;
	}

	/**
	 * Add the element to the vector. It is inserted to maintain the vector in
	 * increasing order of index. If the element has zero value, or if an element
	 * with the same index already exists, an UnsupportedOperationException is
	 * thrown.
	 * 
	 * @param e
	 *            element to add
	 */
	public void add(SparseNumericElement e) throws UnsupportedOperationException {
		Iterator<SparseNumericElement> ite = iterator();
		if (!ite.hasNext()) {
			this.head = new SparseNumericNode(e, null);
			this.tail = this.head;
			this.size = 1l;
		} else {
			long step = 0l;
			while (ite.hasNext()) {
				step++;
				SparseNumericElement next = ite.next();
				if (e.getValue() == 0l || e.getIndex() == next.getIndex()) {
					throw new UnsupportedAddressTypeException();
					// Add at the end
				} else if (!ite.hasNext()) {
					if (next.getIndex() < e.getIndex()) {
						SparseNumericNode newNode = new SparseNumericNode(e, null);
						this.tail.setNext(newNode);
						this.tail = newNode;
						this.size++;
						break;
					} else if (next.getIndex() > e.getIndex()) {
						if (this.size == 1l) {
							SparseNumericNode newNode = new SparseNumericNode(e, this.tail);
							this.head = newNode;
							this.size++;
							break;
						} else if (this.size == 2l) {
							SparseNumericNode newNode = new SparseNumericNode(e, this.tail);
							this.head.setNext(newNode);
							newNode.setNext(this.tail);
							this.size++;
							break;
						} else {
							SparseNumericNode currentNode = this.head;
							for (long i = 2l; i < step; i++) {
								currentNode = currentNode.getNext();
							}
							SparseNumericNode newNode = new SparseNumericNode(e, this.tail);
							currentNode.setNext(newNode);
							this.size++;
							break;
						}
					}
				} else if (next.getIndex() >= e.getIndex()) {
					SparseNumericNode currentNode = this.head;
					for (long i = 2l; i < step; i++) {
						currentNode = currentNode.getNext();
					}
					SparseNumericNode nextNode = currentNode.getNext();
					if (nextNode.getElement().getIndex() == e.getIndex()) {
						throw new UnsupportedAddressTypeException();
					} else {
						SparseNumericNode newNode = new SparseNumericNode(e, nextNode);
						currentNode.setNext(newNode);
						this.size++;
						break;
					}
				}
			}
		}
	}

	/**
	 * If an element with the specified index exists, it is removed and the method
	 * returns true. If not, it returns false.
	 *
	 * @param index
	 *            of element to remove
	 * @return true if removed, false if does not exist
	 */
	public boolean remove(Long index) {
		// implement this method
		// this return statement is here to satisfy the compiler - replace it with your
		// code.
		Iterator<SparseNumericElement> ite = iterator();
		boolean bool = false;
		int step = 0;
		while(ite.hasNext()) {
			step++;
			SparseNumericElement next = ite.next();
			if (next.getIndex() == index) {
				bool = true;
				if (this.head.getElement() == next) {
					if (ite.hasNext()) {
						this.head = this.head.getNext();
						this.size--;
					}  else {
						this.head = null;
						this.tail = null;
						this.size = 0;
					}
				} else {
					SparseNumericNode node = this.head;
					for (int i = 2; i < step; i++) {
						node = node.getNext();
					}
					if (node.getNext().getNext() == null) {
						node.setNext(null);
						this.tail = node;
						size--;
					} else {
						SparseNumericNode node2 = node.getNext().getNext();
						node.setNext(node2);
						size--;
					}
				}
				break;
			}
		}
		return bool;
	}

	/**
	 * Returns the inner product of the vector with a second vector passed as a
	 * parameter. The vectors are assumed to reside in the same space. Runs in
	 * O(m+n) time, where m and n are the number of non-zero elements in each
	 * vector.
	 * 
	 * @param Y
	 *            Second vector with which to take inner product
	 * @return result of inner product
	 */
	public double dot(SparseNumericVector Y) {
		// implement this method
		// this return statement is here to satisfy the compiler - replace it with your
		// code.
		if (this.size == 0l || Y.getSize() == 0l)
			return 0;
		double sum = 0.0;
		SparseNumericNode headX = this.head;
		SparseNumericNode headY = Y.getFirst();
		
		while (headX.getNext() != null && headY.getNext() != null) {
			if (headX.getElement().getIndex() > headY.getElement().getIndex()) {
				headY = headY.getNext();
			} else if (headX.getElement().getIndex() == headY.getElement().getIndex()) {
				sum += headX.getElement().getValue() * headY.getElement().getValue();
				if (headY.getNext() == null)
					return sum;
				headY = headY.getNext();
			} else {
				headX = headX.getNext();
			}	
		}
		
		return sum;
	}

	/**
	 * returns string representation of sparse vector
	 */

	@Override
	public String toString() {
		String sparseVectorString = "";
		Iterator<SparseNumericElement> it = iterator();
		SparseNumericElement x;
		while (it.hasNext()) {
			x = it.next();
			sparseVectorString += "(index " + x.getIndex() + ", value " + x.getValue() + ")\n";
		}
		return sparseVectorString;
	}
}
