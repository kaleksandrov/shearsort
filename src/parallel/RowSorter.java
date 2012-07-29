package parallel;

import java.util.Comparator;

import utilities.MergeSort;
import utilities.Mesh;

/**
 * 
 * @author flyingbear
 */
public class RowSorter extends Sorter
{

	@SuppressWarnings("rawtypes")
	private Comparator comparator;

	public RowSorter(Mesh mesh, int index)
	{
		super(mesh, index);
		this.comparator = null;
	}

	@SuppressWarnings("rawtypes")
	public RowSorter(Mesh mesh, int index, Comparator comparator)
	{
		super(mesh, index);
		this.comparator = comparator;
	}

	@Override
	public void run()
	{
		try
		{
			int[] row = mesh.getRow(index);
			if (comparator != null)
			{
				wasSorted = MergeSort.sort(row, comparator);
			}
			else
			{
				wasSorted = MergeSort.sort(row);
			}
			mesh.setRow(index, row);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
