package bg.tu.parallelprogramming.utilities;

import java.util.Random;

/**
 * @author kaleksandrov
 */
public class ArrayUtils {

	private ArrayUtils() {
		// Prevent initialization
	}

	public static void swapMatrixElements(int[][] matrix, int firstX,
			int firstY, int secondX, int secondY) {
		int t = matrix[firstX][firstY];
		matrix[firstX][firstY] = matrix[secondX][secondY];
		matrix[secondX][secondY] = t;
	}

	public static void swapArrayElements(int[] array, int first, int second) {
		int tmp = array[first];
		array[first] = array[second];
		array[second] = tmp;
	}

	public static void fillMatrix(int[][] array) {
		int height = array.length;
		int width = array[0].length;

		Random generator = new Random();
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				array[i][j] = generator.nextInt(10);
			}
		}
	}

	public static void fillArray(int[] array) {

		Random generator = new Random();
		for (int i = 0; i < array.length; ++i) {
			array[i] = generator.nextInt(10);
		}
	}

	public static boolean odd(int number) {
		if (number % 2 == 0) {
			return false;
		} else {
			return true;
		}
	}

	public static void printArray(int[] array) {
		for (int element : array) {
			System.out.println(element);
		}
		System.out.println("");
	}

	public static void printTwoDimensionalArray(int[][] array) {
		for (int[] elements : array) {
			for (int element : elements) {
				System.out.print(element + "\t");
			}
			System.out.println("");
		}
		System.out.println("");
	}
}
