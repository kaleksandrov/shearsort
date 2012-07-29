package utilities;

/**
 * 
 * @author flyingbear
 */
public class SortChecker
{

	private SortChecker()
	{
		// Prevent initializing
	}

	public static boolean checkIsMatrixSorted(int[][] matrix)
	{
		boolean isSorted = true;
		int height = matrix.length;
		int width = matrix[0].length;
		int previous = matrix[0][0];
		int current = matrix[0][0];

		for (int i = 0; i < height; ++i)
		{
			if (ArrayUtils.odd(i))
			{
				for (int j = width - 1; j >= 0; j--)
				{
					current = matrix[i][j];
					if (current < previous)
					{
						isSorted = false;
						break;
					}
					else
					{
						previous = current;
					}
				}
			}
			else
			{
				for (int j = 0; j < width; ++j)
				{
					current = matrix[i][j];
					if (current < previous)
					{
						isSorted = false;
						break;
					}
					else
					{
						previous = current;
					}
				}
			}
		}

		return isSorted;
	}
}
