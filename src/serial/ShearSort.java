package serial;

import java.util.Collections;

import utilities.ArrayUtils;
import utilities.MergeSort;
import utilities.Mesh;

/**
 * 
 * @author flyingbear
 */
public class ShearSort
{

	private ShearSort()
	{
		// Prevent initialization
	}

	public static int sort(Mesh mesh) throws InterruptedException
	{

		boolean done = false;
		int height = mesh.getHeight();
		int width = mesh.getWidth();
		int iterationCounter = 0;

		while (!done)
		{

			iterationCounter++;
			done = true;

			// sort rows
			for (int j = 0; j < height; ++j)
			{
				int[] row = mesh.getRow(j);
				if (ArrayUtils.odd(j))
				{
					// descending
					done = MergeSort.sort(row, Collections.reverseOrder()) && done;
				}
				else
				{

					// ascending
					done = MergeSort.sort(row) && done;
				}
				mesh.setRow(j, row);
			}

			// sort ascending columns
			for (int i = 0; i < width; ++i)
			{
				int[] column = mesh.getColumn(i);
				done = MergeSort.sort(column) && done;
				mesh.setColumn(i, column);
			}
		}

		return iterationCounter;
	}
}
