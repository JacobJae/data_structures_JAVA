package fundamentals_of_data_structures.pair_sums;

import java.util.*;

/**
 * Represents a sorted integer array. Provides a method, kpairSum, that
 * determines whether the array contains two elements that sum to a given
 * integer k. Runs in O(n) time, where n is the length of the array.
 * 
 * @author jameselder
 */
public class SortedIntegerArray {

	protected int[] sortedIntegerArray;

	public SortedIntegerArray(int[] integerArray) {
		sortedIntegerArray = integerArray.clone();
		Arrays.sort(sortedIntegerArray);
	}

	/**
	 * Determines whether the array contains two elements that sum to a given
	 * integer k. Runs in O(n) time, where n is the length of the array.
	 * 
	 * @author jameselder
	 */
	public boolean kPairSum(Integer k) {
		if (sortedIntegerArray.length < 1)
			return false;
		int i = 0;
		int j = sortedIntegerArray.length - 1;
		return kPairSumInterval(k, i, j);
	}

	private boolean kPairSumInterval(Integer k, int i, int j) {
		// Base case
		if (i == j)
			return false;

		// Inductive step
		long sum = sortedIntegerArray[i] + sortedIntegerArray[j];
		if (sum == k)
			return true;
		if (sum > k) {
			j--;
			if (kPairSumInterval(k, i, j)) {
				return true;
			} else {
				return false;
			}
		} else {
			i++;
			if (kPairSumInterval(k, i, j)) {
				return true;
			} else {
				return false;
			}
		}
	}

}
