package fundamentals_of_data_structures.integral_images;

import java.lang.reflect.Array;

/**
 * Represents an integer integral image, which allows the user to query the mean
 * value of an arbitrary rectangular subimage in O(1) time. Uses O(n) memory,
 * where n is the number of pixels in the image.
 *
 * @author jameselder
 */
public class IntegralImage {

	private final int[][] integralImage;
	private final int imageHeight; // height of image (first index)
	private final int imageWidth; // width of image (second index)

	/**
	 * Constructs an integral image from the given input image.
	 *
	 * @author jameselder
	 * @param image
	 *            The image represented
	 * @throws InvalidImageException
	 *             Thrown if input array is not rectangular
	 */
	public IntegralImage(int[][] image) throws InvalidImageException {
		// implement this method.
		int height = Array.getLength(image);
		int width = Array.getLength(image[0]);
		this.integralImage = new int[height][width];
		integralImage[0][0] = image[0][0];
		for (int i = 1; i < height; i++)
			integralImage[0][i] = integralImage[0][i - 1] + image[0][i];
		for (int i = 1; i < width; i++)
			integralImage[i][0] = integralImage[i - 1][0] + image[i][0];
		for (int i = 1; i < height; i++) {
			if (Array.getLength(image[i]) != width)
				throw new InvalidImageException();
			for (int j = 1; j < width; j++) {
				integralImage[i][j] = integralImage[i][j - 1] + integralImage[i - 1][j] - integralImage[i - 1][j - 1] + image[i][j];				
			}
		}
		this.imageHeight = height;
		this.imageWidth = width;
	}

	/**
	 * Returns the mean value of the rectangular sub-image specified by the top,
	 * bottom, left and right parameters. The sub-image should include pixels in
	 * rows top and bottom and columns left and right. For example, top = 1, bottom
	 * = 2, left = 1, right = 2 specifies a 2 x 2 sub-image starting at (top, left)
	 * coordinate (1, 1).
	 *
	 * @author jameselder
	 * @param top
	 *            top row of sub-image
	 * @param bottom
	 *            bottom row of sub-image
	 * @param left
	 *            left column of sub-image
	 * @param right
	 *            right column of sub-image
	 * @return
	 * @throws BoundaryViolationException
	 *             if image indices are out of range
	 * @throws NullSubImageException
	 *             if top > bottom or left > right
	 */
	public double meanSubImage(int top, int bottom, int left, int right)
			throws BoundaryViolationException, NullSubImageException {
		// implement this method
		if (top >= this.imageHeight || bottom >= this.imageHeight || left >= this.imageWidth || right >= this.imageWidth)
			throw new BoundaryViolationException();
		if (top > bottom || left > right)
			throw new NullSubImageException();
		double avg = 0.0;
		int pixNum = (bottom - top + 1) * (right - left + 1);
		if (left == 0 && top == 0) {
			avg = this.integralImage[bottom][right];
			avg = avg / pixNum;
		} else if (left == 0) {
			avg = this.integralImage[bottom][right] - this.integralImage[top - 1][right];
			avg = avg / pixNum;
		} else if (top == 0) {
			avg = this.integralImage[bottom][right] - this.integralImage[bottom][left - 1];
			avg = avg / pixNum;			
		} else {
			avg = this.integralImage[bottom][right] - this.integralImage[bottom][left - 1] - this.integralImage[top - 1][right] + this.integralImage[top - 1][left - 1];
			avg = avg / pixNum;
		}
		return avg;
	}
	
	public int[][] get() {
		return this.integralImage;
	}
}
