package bg.tu.parallelprogramming.parallel;

import bg.tu.parallelprogramming.utilities.MergeSort;
import bg.tu.parallelprogramming.utilities.Mesh;

/**
 * @author flyingbear
 */
public class ColumnSorter extends Sorter
{

	public ColumnSorter(Mesh mesh, int index)
	{
		super(mesh, index);
	}

	@Override
	public void run()
	{
		try
		{
			int[] column = mesh.getColumn(index);
			wasSorted = MergeSort.sort(column);
			mesh.setColumn(index, column);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
