package fundamentals_of_data_structures.sorting;

import java.util.*;

/**
 *
 * Provides two static methods for sorting Integer arrays (heapSort and
 * mergeSort)
 * 
 * @author jameselder
 */
public class YorkArrays {

	/*
	 * Sorts the input array of Integers a using HeapSort. Result is returned in a.
	 * Makes use of java.util.PriorityQueue. Sorting is NOT in place - PriorityQueue
	 * allocates a separate heap-based priority queue. Not a stable sort, in
	 * general. Throws a null pointer exception if the input array is null.
	 */
	public static void heapSort(Integer[] a) throws NullPointerException {
		// implement this method.
		if (a == null)
			throw new NullPointerException();

		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		for (int i = 0; i < a.length; i++)
			heap.offer(a[i]);

		for (int i = 0; i < a.length; i++)
			a[i] = heap.poll();
	}

	/*
	 * Sorts the input array of Integers a using mergeSort and returns result.
	 * Sorting is stable. Throws a null pointer exception if the input array is
	 * null.
	 */
	public static Integer[] mergeSort(Integer[] a) throws NullPointerException {
		return (mergeSort(a, 0, a.length - 1));
	}

	/*
	 * Sorts the input subarray of Integers a[p...q] using MergeSort and returns
	 * result. Sorting is stable.
	 */
	private static Integer[] mergeSort(Integer[] a, int p, int q) {
		// implement this method.
		if (p >= q) {
			Integer[] end = {a[p]};
			return end;
		}
		int mid = (p + q) / 2;
		Integer[] left = mergeSort(a, p, mid);
		Integer[] right = mergeSort(a, mid + 1, q);
		return merge(left, right);
	}

	/*
	 * Merges two sorted arrays of Integers into a single sorted array. Given two
	 * equal elements, one in a and one in b, the element in a precedes the element
	 * in b.
	 */
	private static Integer[] merge(Integer[] a, Integer[] b) {
		// implement this method.
		Integer[] temp = new Integer[a.length + b.length];
		int step = 0;
		int aIndex = 0;
		int bIndex = 0;
		while (a.length != aIndex && b.length != bIndex) {
			if (a[aIndex] <= b[bIndex]) {
				temp[step] = a[aIndex];
				aIndex++;
			} else {
				temp[step] = b[bIndex];
				bIndex++;				
			}
			step++;
		}
		if (a.length == aIndex) {
			for (int i = step; i < a.length + b.length; i++) {
				temp[i] = b[bIndex];
				bIndex++;
			}
		} else {
			for (int i = step; i < a.length + b.length; i++) {
				temp[i] = a[aIndex];
				aIndex++;
			}
		}
		return temp;
	}
}
