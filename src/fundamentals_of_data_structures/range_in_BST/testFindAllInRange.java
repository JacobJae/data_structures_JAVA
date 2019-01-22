package fundamentals_of_data_structures.range_in_BST;

import java.util.*;

/**
 * Tests BSTRange
 * 
 * @author jameselder
 */
public class testFindAllInRange {

	public static void main(String[] args) {
//		BSTRange<Integer, String> number = new BSTRange<Integer, String>();
//		
//		PositionalList<Entry<Integer, String>> numList;
//		Iterator<Entry<Integer, String>> numIter;
////		Random random = new Random();
////		int [] arr = new int[100];
//		int k1 = 3;
//		int k2 = 7;
//		int [] nums = {45, 86, 72, 52, 5, 11, 18, 52, 27, 71, 56, 40, 69, 15, 85, 45, 56, 76, 42, 36, 77, 79, 75, 94, 43, 91, 40, 49, 90, 30, 18, 91, 9, 17, 80, 5, 92, 36, 17, 19, 92, 91, 96, 3, 55, 79, 80, 27, 42, 52, 51, 61, 62, 3, 33, 30, 63, 97, 54, 58, 83, 84, 12, 48, 90, 3, 72, 18, 15, 49, 16, 16, 60, 77, 38, 11, 80, 41, 80, 84, 94, 52, 99, 87, 41, 18, 94, 70, 51, 56, 91, 2, 48, 30, 6, 78, 95, 42, 28, 27};
////		int [] nums = {45, 86, 72, 52, 5, 11, 18, 52, 27, 71};
//		System.out.println(nums.length);
//		for (int i = 0; i < nums.length; i++) {
//			//int num = random.nextInt(100);
////			arr[i % 100] = nums[i % 100];
//			number.put(nums[i], new Integer(nums[i]).toString());
//		}
////		for (int j = 0; j < 100; j++) {
////			System.out.print();
////		}
//		System.out.println();
//		System.out.println("My test");

//		try { // should output nothing
//			numList = number.findAllInRange(k1, k2);
//			numIter = numList.iterator();
//
//			System.out.println("Test " + k1 + " to " + k2);
//			while (numIter.hasNext()) {
//				System.out.println(numIter.next().getValue());
//			}
//		} catch (Exception x) {
//			System.out.println("My Test Case Failed");
//		}
//		
//		System.out.println("End");
		
		BSTRange<Integer, String> medals = new BSTRange<Integer, String>();
		PositionalList<Entry<Integer, String>> medalList;
		Iterator<Entry<Integer, String>> entryIter;
		int k1 = 3;
		int k2 = 7;

		System.out.println("2018 Winter Olympics Medal Standings:");

		try { // should output nothing
			medalList = medals.findAllInRange(k1, k2);
			entryIter = medalList.iterator();

			System.out.println("The countries ranked from " + k1 + " to " + k2 + " in medal standings are:");
			while (entryIter.hasNext()) {
				System.out.println(entryIter.next().getValue());
			}
		} catch (Exception x) {
			System.out.println("Test Case 1 Failed");
		}

		medals.put(1, "Norway");
		medals.put(2, "Germany");
		medals.put(3, "Canada");
		medals.put(4, "USA");
		medals.put(5, "Netherlands");
		medals.put(6, "Sweden");
		medals.put(7, "South Korea");
		medals.put(8, "Switzerland");
		medals.put(9, "France");
		medals.put(10, "Austria");

		try { // should output Canada, USA, Netherlands, Sweden, South Korea
			medalList = medals.findAllInRange(k1, k2);
			entryIter = medalList.iterator();

			System.out.println("The countries ranked from " + k1 + " to " + k2 + " in medal standings are:");
			while (entryIter.hasNext()) {
				System.out.println(entryIter.next().getValue());
			}
		} catch (Exception x) {
			System.out.println("Test Case 2 Failed");
		}

		k1 = -10;
		k2 = -5;
		try { // should output nothing
			medalList = medals.findAllInRange(k1, k2);
			entryIter = medalList.iterator();

			System.out.println("The countries ranked from " + k1 + " to " + k2 + " in medal standings are:");
			while (entryIter.hasNext()) {
				System.out.println(entryIter.next().getValue());
			}
		} catch (Exception x) {
			System.out.println("Test Case 3 Failed");
		}

		k1 = 5;
		k2 = 4;
		try { // should output nothing
			medalList = medals.findAllInRange(k1, k2);
			entryIter = medalList.iterator();

			System.out.println("The countries ranked from " + k1 + " to " + k2 + " in medal standings are:");
			while (entryIter.hasNext()) {
				System.out.println(entryIter.next().getValue());
			}
		} catch (Exception x) {
			System.out.println("Test Case 4 Failed");
		}

		k1 = 3;
		k2 = 3;
		try {// should output Canada
			medalList = medals.findAllInRange(k1, k2);
			entryIter = medalList.iterator();

			System.out.println("The countries ranked from " + k1 + " to " + k2 + " in medal standings are:");
			while (entryIter.hasNext()) {
				System.out.println(entryIter.next().getValue());
			}
		} catch (Exception x) {
			System.out.println("Test Case 5 Failed");
		}

		k1 = -10;
		k2 = 10;
		try {// should output Norway, Germany, Canada, USA, Netherlands, Sweden, South Korea,
				// Switzerland, France, Austria
			medalList = medals.findAllInRange(k1, k2);
			entryIter = medalList.iterator();

			System.out.println("The countries ranked from " + k1 + " to " + k2 + " in medal standings are:");
			while (entryIter.hasNext()) {
				System.out.println(entryIter.next().getValue());
			}
		} catch (Exception x) {
			System.out.println("Test Case 6 Failed");
		}
		
	}
}
