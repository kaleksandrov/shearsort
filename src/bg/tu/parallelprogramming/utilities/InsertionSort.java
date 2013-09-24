package bg.tu.parallelprogramming.utilities;

import java.util.Comparator;

/**
 * 
 * @author flyingbear
 */
public class InsertionSort
{

	private InsertionSort()
	{
		// Prevent initializtion
	}

	@SuppressWarnings(
	{ "rawtypes", "unchecked" })
	public static Boolean sort(int[] array)
	{
		int low = 0;
		int high = array.length;
		Boolean wasArraySorted = true;

		for (int i = low; i < high; i++)
		{
			for (int j = i; j > low && ((Comparable) array[j - 1]).compareTo(array[j]) > 0; j--)
			{
				swap(array, j, j - 1);
				wasArraySorted = false;
			}
		}

		return wasArraySorted;
	}

	@SuppressWarnings(
	{ "rawtypes", "unchecked" })
	public static Boolean sort(int[] array, Comparator comparator)
	{
		int low = 0;
		int high = array.length;
		Boolean wasArraySorted = true;

		for (int i = low; i < high; i++)
		{
			for (int j = i; j > low && (comparator.compare(array[j - 1], array[j]) > 0); j--)
			{
				swap(array, j, j - 1);
				wasArraySorted = false;
			}
		}

		return wasArraySorted;
	}

	public static void swap(int[] array, int first, int second)
	{
		int tmp = array[first];
		array[first] = array[second];
		array[second] = tmp;
	}
}
