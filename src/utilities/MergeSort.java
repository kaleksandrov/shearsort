package utilities;

import java.util.Comparator;

/**
 * 
 * @author flyingbear
 */
public class MergeSort
{

	private static final int INSERTIONSORT_THRESHOLD = 7;

	private MergeSort()
	{
		// Prevent initializtion
	}

	@SuppressWarnings(
	{ "rawtypes", "unchecked" })
	private static Boolean mergeSort(int[] src, int[] dest, int low, int high, int off)
	{
		int length = high - low;
		Boolean wasArraySorted = true;

		// Insertion sort on smallest arrays
		if (length < INSERTIONSORT_THRESHOLD)
		{
			wasArraySorted = InsertionSort.sort(dest) && wasArraySorted;
			return wasArraySorted;
		}

		// Recursively sort halves of dest into src
		int destLow = low;
		int destHigh = high;
		low += off;
		high += off;
		int mid = (low + high) >>> 1;
		wasArraySorted = mergeSort(dest, src, low, mid, -off) && wasArraySorted;
		wasArraySorted = mergeSort(dest, src, mid, high, -off) && wasArraySorted;

		// If list is already sorted, just copy from src to dest. This is an
		// optimization that results in faster sorts for nearly ordered lists.
		if (((Comparable) src[mid - 1]).compareTo(src[mid]) <= 0)
		{
			System.arraycopy(src, low, dest, destLow, length);
			return wasArraySorted;
		}

		// Merge sorted halves (now in src) into dest
		for (int i = destLow, p = low, q = mid; i < destHigh; i++)
		{
			if (q >= high || p < mid && ((Comparable) src[p]).compareTo(src[q]) <= 0)
			{
				dest[i] = src[p++];
			}
			else
			{
				dest[i] = src[q++];
			}
		}

		return wasArraySorted;
	}

	@SuppressWarnings(
	{ "rawtypes", "unchecked" })
	private static Boolean mergeSort(int[] src, int[] dest, int low, int high, int off, Comparator comparator)
	{
		int length = high - low;
		Boolean wasArraySorted = true;

		// Insertion sort on smallest arrays
		if (length < INSERTIONSORT_THRESHOLD)
		{
			wasArraySorted = InsertionSort.sort(dest, comparator) && wasArraySorted;
			return wasArraySorted;
		}

		// Recursively sort halves of dest into src
		int destLow = low;
		int destHigh = high;
		low += off;
		high += off;
		int mid = (low + high) >>> 1;
		wasArraySorted = mergeSort(dest, src, low, mid, -off, comparator) && wasArraySorted;
		wasArraySorted = mergeSort(dest, src, mid, high, -off, comparator) && wasArraySorted;

		// If list is already sorted, just copy from src to dest. This is an
		// optimization that results in faster sorts for nearly ordered lists.
		if (comparator.compare(src[mid - 1], src[mid]) <= 0)
		{
			System.arraycopy(src, low, dest, destLow, length);
			return wasArraySorted;
		}

		// Merge sorted halves (now in src) into dest
		for (int i = destLow, p = low, q = mid; i < destHigh; i++)
		{
			if (q >= high || p < mid && comparator.compare(src[p], src[q]) <= 0)
			{
				dest[i] = src[p++];
			}
			else
			{
				dest[i] = src[q++];
			}
		}

		return wasArraySorted;
	}

	public static Boolean sort(int[] array)
	{
		int[] cloned = (int[]) array.clone();
		return mergeSort(cloned, array, 0, array.length, 0);
	}

	@SuppressWarnings("rawtypes")
	public static Boolean sort(int[] array, Comparator comparator)
	{
		int[] cloned = (int[]) array.clone();
		return mergeSort(cloned, array, 0, array.length, 0, comparator);
	}
}
