package serial;

/**
 *
 * @author flyingbear
 */

import java.util.Scanner;

import utilities.Mesh;

public class Main
{

	private static Mesh mesh = new Mesh();

	public static void main(String[] args)
	{
		switch (args.length)
		{
		case 0:
			initialize();
			break;
		case 1:
			String filename = args[0];
			initialize(filename);
			break;
		case 2:
			int x = Integer.parseInt(args[0]);
			int y = Integer.parseInt(args[1]);
			initialize(x, y);
			break;
		default:
			System.out.println("Wrong input!");
			System.exit(1);
			break;
		}
		doSort();
	}

	private static void initialize()
	{
		Scanner reader = new Scanner(System.in);

		// Initialize mesh
		int m = 100;
		int n = 100;
		try
		{
			System.out.println("Please enter matrix dimensions MxN:");
			System.out.print("M = ");
			m = reader.nextInt();
			System.out.print("N = ");
			n = reader.nextInt();
		}
		catch (Exception e)
		{
			System.out
					.print("Sorry, the value you have entered is not a valid integer value. Default values will be used.");
			m = 100;
			n = 100;
		}
		finally
		{
			System.out.print("Initializing mesh...");
			mesh = new Mesh(m, n);
			System.out.println("\tOK");
		}
	}

	private static void initialize(String filename)
	{
		// Initialize mesh
		System.out.print("Initializing mesh...");
		mesh.loadFromFile(filename);
		System.out.println("\tOK");
	}

	private static void initialize(int X, int Y)
	{
		// Initialize mesh
		int m = X;
		int n = Y;
		System.out.print("Initializing mesh...");
		mesh = new Mesh(m, n);
		System.out.println("\tOK");
	}

	private static void doSort()
	{
		System.out.println("Sorting started...");
		long start = System.currentTimeMillis();
		try
		{
			System.out.println("Total iterrations : " + ShearSort.sort(mesh));
		}
		catch (InterruptedException e)
		{
			System.out.println("Unfortunately something gone wrong with the threadpool... this shouldn't happen...");
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("Sorting finished!");
		System.out.print("Matrix was sorted : ");
		System.out.println(SortChecker.checkIsMatrixSorted(mesh.getMatrix()));
		System.out.println("Total time needed : " + (end - start) + " milliseconds");
	}
}
