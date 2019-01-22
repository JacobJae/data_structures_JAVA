package fundamentals_of_data_structures.range_in_BST;

/**
 * Extends the TreeMap class to allow convenient access to entries within a
 * specified range of key values (findAllInRange).
 * 
 * @author jameselder
 */
public class BSTRange<K, V> extends TreeMap<K, V> {

	/*
	 * Returns the lowest (deepest) position in the subtree rooted at pos that is a
	 * common ancestor to positions with keys k1 and k2, or to the positions they
	 * would occupy were they present.
	 */
	protected Position<Entry<K, V>> findLowestCommonAncestor(K k1, K k2, Position<Entry<K, V>> pos) {
		// implement this method
		try {
			if (isExternal(pos))
				return null;
			else if (compare(k1, pos.getElement()) <= 0 && compare(k2, pos.getElement()) >= 0)
				return pos;
			else if (compare(k1, pos.getElement()) > 0)
				return findLowestCommonAncestor(k1, k2, right(pos));
			else if (compare(k2, pos.getElement()) < 0)
				return findLowestCommonAncestor(k1, k2, left(pos));
			else
				return null;
		} catch (IllegalArgumentException e) {
			return pos;
		}
	}

	/*
	 * Finds all entries in the subtree rooted at pos with keys of k or greater and
	 * copies them to L, in non-decreasing order.
	 */
	protected void findAllAbove(K k, Position<Entry<K, V>> pos, PositionalList<Entry<K, V>> L) {
		// implement this method
		if (pos.getElement() != null) {
			if (compare(k, pos.getElement()) <= 0) {
				findAllAbove(k, left(pos), L);
				L.addLast(pos.getElement());
				findAllAbove(k, right(pos), L);
			} else {
				findAllAbove(k, right(pos), L);
			}
		}
	}

	/*
	 * Finds all entries in the subtree rooted at pos with keys of k or less and
	 * copies them to L, in non-decreasing order.
	 */
	protected void findAllBelow(K k, Position<Entry<K, V>> pos, PositionalList<Entry<K, V>> L) {
		// implement this method
		if (pos.getElement() != null) {
			if (compare(k, pos.getElement()) >= 0) {
				findAllBelow(k, left(pos), L);
				L.addLast(pos.getElement());
				findAllBelow(k, right(pos), L);
			} else {
				findAllBelow(k, left(pos), L);
			}
		}
	}

	/*
	 * Returns all entries with keys no less than k1 and no greater than k2, in
	 * non-decreasing order.
	 */
	public PositionalList<Entry<K, V>> findAllInRange(K k1, K k2) {
		Position<Entry<K, V>> pos = findLowestCommonAncestor(k1, k2, root());
		PositionalList<Entry<K, V>> L = new LinkedPositionalList<Entry<K, V>>();
		if (pos != null) {
			findAllAbove(k1, left(pos), L);
			L.addLast(pos.getElement());
			findAllBelow(k2, right(pos), L);
		}

		return L;
		// implement this method
	}
}
