package fundamentals_of_data_structures.emergency_ward_triage;

import java.util.*;

/**
 * Adaptible priority queue using location-aware entries in a min-heap, based on
 * an extendable array. The order in which equal entries were added is
 * preserved.
 *
 * @author jameselder
 * @param <E>
 *            The entry type.
 */
public class APQ<E> {

	private final ArrayList<E> apq; // will store the min heap
	private final Comparator<E> comparator; // to compare the entries
	private final Locator<E> locator; // to locate the entries within the queue
	/**
	 * Constructor
	 * 
	 * @param comparator
	 *            used to compare the entries
	 * @param locator
	 *            used to locate the entries in the queue
	 * @throws NullPointerException
	 *             if comparator or locator parameters are null
	 */
	public APQ(Comparator<E> comparator, Locator<E> locator) throws NullPointerException {
		if (comparator == null || locator == null) {
			throw new NullPointerException();
		}
		apq = new ArrayList<>();
		apq.add(null); // dummy value at index = 0
		this.comparator = comparator;
		this.locator = locator;
	}

	/**
	 * Inserts the specified entry into this priority queue.
	 *
	 * @param e
	 *            the entry to insert
	 * @throws NullPointerException
	 *             if parameter e is null
	 */
	public void offer(E e) throws NullPointerException {
		if (e == null) {
			throw new NullPointerException();
		}
		this.apq.add(e);
		locator.set(e, size());
		upheap(size());
	}

	/**
	 * Removes the entry at the specified location.
	 *
	 * @param pos
	 *            the location of the entry to remove
	 * @throws BoundaryViolationException
	 *             if pos is out of range
	 */
	public void remove(int pos) throws BoundaryViolationException {
		if (pos < 1 && pos > size()) {
			throw new BoundaryViolationException();
		}
		swap(pos, size());
		apq.remove(size());
		downheap(pos);
	}

	/**
	 * Removes the first entry in the priority queue.
	 */
	public E poll() {
		swap(1, size());
		E entry = apq.remove(size());
		downheap(1);
		return entry;
	}

	/**
	 * Returns but does not remove the first entry in the priority queue.
	 */
	public E peek() {
		if (isEmpty()) {
			return null;
		}
		return apq.get(1);
	}

	public boolean isEmpty() {
		return (size() == 0);
	}

	public int size() {
		return apq.size() - 1; // dummy node at location 0
	}

	/**
	 * Shift the entry at pos upward in the heap to restore the minheap property
	 * 
	 * @param pos
	 *            the location of the entry to move
	 */
	private void upheap(int pos) {
		if (pos < 2) {
		} else {
			if (this.comparator.compare(apq.get(pos), apq.get(pos / 2)) < 0) 
				swap(pos, pos / 2);
			upheap(pos / 2);
		}
	}

	/**
	 * Shift the entry at pos downward in the heap to restore the minheap property
	 * 
	 * @param pos
	 *            the location of the entry to move
	 */
	private void downheap(int pos) {
		if (pos * 2 > size()) {

		} else {
			int pos2 = pos * 2;
			
			if (pos * 2 != size() && this.comparator.compare(apq.get(pos2), apq.get(pos2 + 1)) > 0)
				pos2++;
			if (this.comparator.compare(apq.get(pos), apq.get(pos2)) > 0) 
				swap(pos, pos2);
			
			downheap(pos2);
		}
	}

	/**
	 * Swaps the entries at the specified locations.
	 *
	 * @param pos1
	 *            the location of the first entry
	 * @param pos2
	 *            the location of the second entry
	 */
	private void swap(int pos1, int pos2) {
		E entry1 = apq.get(pos1);
		E entry2 = apq.get(pos2);
		apq.set(pos2, entry1);
		apq.set(pos1, entry2);
		locator.set(entry1, pos2);
		locator.set(entry2, pos1);
	}
}