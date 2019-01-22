package fundamentals_of_data_structures.sparse_vectors;

import java.util.*;

/**
 *
 * An iterator for a sparse numeric array.
 * 
 * @author jameselder
 */
public class SparseNumericIterator implements Iterator<SparseNumericElement> {

	protected SparseNumericVector list;
	protected SparseNumericNode position;

	/**
	 *
	 * @param X
	 *            Sparse vector over which we will iterate
	 */
	public SparseNumericIterator(SparseNumericVector X) {
		list = X;
		position = X.getFirst();
	}

	public boolean hasNext() {
		return position != null;
	}

	public void remove() {
	}

	public SparseNumericElement next() throws NoSuchElementException {
		SparseNumericElement element;
		if (position == null) {
			throw new NoSuchElementException("No next element");
		} else {
			element = position.getElement();
			position = position.getNext();
			return element;
		}
	}
}
