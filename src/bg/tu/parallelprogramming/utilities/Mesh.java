package bg.tu.parallelprogramming.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author flyingbear
 */
public class Mesh implements Cloneable
{

	private int[][] matrix;
	private int height = 10;
	private int width = 10;

	public Mesh()
	{
		this.matrix = new int[height][width];
		Mesh.fillMatrix(matrix);
	}

	public Mesh(int[][] matrix)
	{
		this.matrix = matrix;
	}

	public Mesh(int height, int width)
	{
		this.height = height;
		this.width = width;
		this.matrix = new int[height][width];
		Mesh.fillMatrix(matrix);
	}

	public int[] getColumn(int index) throws InterruptedException
	{
		int[] column = new int[height];
		for (int i = 0; i < height; ++i)
		{
			column[i] = matrix[i][index];
		}

		return column;
	}

	public void setColumn(int index, int[] column)
	{
		for (int i = 0; i < height; ++i)
		{
			matrix[i][index] = column[i];
		}
	}

	public int[] getRow(int index) throws InterruptedException
	{
		return matrix[index];
	}

	public void setRow(int index, int[] row)
	{
		matrix[index] = row;
	}

	public void printMesh()
	{
		for (int[] row : matrix)
		{
			for (int element : row)
			{
				System.out.print(element + "\t");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public static void fillMatrix(int[][] array)
	{
		int height = array.length;
		int width = array[0].length;

		Random generator = new Random();
		for (int i = 0; i < height; ++i)
		{
			for (int j = 0; j < width; ++j)
			{
				array[i][j] = generator.nextInt(10);
			}
		}
	}

	public static void printTwoDimensionalArray(int[][] array)
	{
		for (int[] rows : array)
		{
			for (int element : rows)
			{
				System.out.print(element + "\t");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public int getHeight()
	{
		return this.height;
	}

	public int getWidth()
	{
		return this.width;
	}

	public int[][] getMatrix()
	{
		return this.matrix;
	}

	public void writeToFile(String filename)
	{
		try
		{
			PrintWriter writer = new PrintWriter(new File(filename));
			for (int[] row : this.matrix)
			{
				for (int element : row)
				{
					writer.write(new Integer(element).toString());
					writer.write(" ");
				}
				writer.println();
				writer.flush();
			}
			writer.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

	}

	public void loadFromFile(String filename)
	{
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line = null;
			List<String[]> rows = new ArrayList<String[]>();
			while ((line = reader.readLine()) != null)
			{
				String[] elements = line.split(" ");
				rows.add(elements);
			}
			reader.close();

			this.height = rows.size();
			this.width = rows.get(0).length;
			matrix = new int[this.height][this.width];

			for (int i = 0; i < this.height; i++)
			{
				for (int j = 0; j < this.width; j++)
				{
					this.matrix[i][j] = Integer.parseInt(rows.get(i)[j]);
				}
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException
	{

		Mesh clone = (Mesh) super.clone();

		clone.height = this.height;
		clone.width = this.width;
		clone.matrix = this.matrix.clone();

		return clone;

	}
}
