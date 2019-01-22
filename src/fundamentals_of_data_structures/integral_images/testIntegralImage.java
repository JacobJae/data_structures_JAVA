package fundamentals_of_data_structures.integral_images;

/**
 * Tests the IntegralImage class.
 * 
 * @author jameselder
 */
public class testIntegralImage {

	public static void main(String[] args) {
		// int[][] image1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int[][] image1 = { { 1, 0, 0, 1, 1, 1 }, { 2, 1, 1, 1, 2, 1 }, { 1, 2, 1, 2, 0, 1 }, { 1, 1, 0, 1, 1, 2 },
				{ 0, 1, 2, 1, 2, 1 }, { 1, 0, 1, 0, 1, 1 } };
		int top, bottom, left, right;
		double mean;

		IntegralImage integralImage1;
		top = 3;
		bottom = 4;
		left = 1;
		right = 3;

		try {
			integralImage1 = new IntegralImage(image1);
		} catch (InvalidImageException iix) {
			System.out.println("Invalid Image Exception");
			return;
		}

		int[][] sad = integralImage1.get();
		for (int i = 0; i < 6; i++) {
			System.out.println();
			for (int j = 0; j < 6; j++) {
				System.out.print(" " + sad[i][j]);
			}
		}
		System.out.println();

		try {
			mean = integralImage1.meanSubImage(top, bottom, left, right); // should be 7.0
			System.out.println("The mean of the subimage from (" + top + "," + left + ") to (" + bottom + "," + right
					+ ") is " + mean);
		} catch (BoundaryViolationException bvx) {
			System.out.println("Index out of range.");
		} catch (NullSubImageException nsix) {
			System.out.println("Null sub-image.");
		}

	}
}
