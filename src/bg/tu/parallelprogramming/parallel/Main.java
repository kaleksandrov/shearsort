package bg.tu.parallelprogramming.parallel;

/**
 *
 * @author kaleksandrov
 */

import java.util.Scanner;

import bg.tu.parallelprogramming.utilities.Mesh;
import bg.tu.parallelprogramming.utilities.SortChecker;

public class Main {

	private static Mesh mesh = new Mesh();

	public static void main(String[] args) {
		switch (args.length) {
		case 0:
			initialize();
			break;
		case 2:
			String filename = args[0];
			int threads = Integer.parseInt(args[1]);
			initialize(filename, threads);
			break;
		case 3:
			int x = Integer.parseInt(args[0]);
			int y = Integer.parseInt(args[1]);
			int threadsCount = Integer.parseInt(args[2]);
			initialize(x, y, threadsCount);
			break;
		default:
			System.exit(1);
			break;
		}
		doSort();
	}

	private static void initialize() {
		Scanner reader = new Scanner(System.in);

		// Initialize mesh
		int m = 100;
		int n = 100;
		try {
			System.out.println("Please enter matrix dimensions MxN:");
			System.out.print("M = ");
			m = reader.nextInt();
			System.out.print("N = ");
			n = reader.nextInt();
		} catch (Exception e) {
			System.out
					.print("Sorry, the value you have entered is not a valid integer value. Default values will be used.");
			m = 100;
			n = 100;
		} finally {
			System.out.print("Initializing mesh...");
			mesh = new Mesh(m, n);
			System.out.println("\tOK");
		}

		// Initialize threadpool
		try {
			System.out.println("Please eneter working threads count:");
			int threadsCount = reader.nextInt();
			System.out.print("Initializing threadpool...");
			ShearSort.setThreadsCount(threadsCount);
		} catch (Exception e) {
			System.out
					.print("Sorry, the value you have entered is not a valid integer value. Using processors count instead...");
		} finally {
			System.out.println("\tOK");
			System.out
					.println("Threads count : " + ShearSort.getThreadsCount());
		}
	}

	private static void initialize(String filename, int threadsCount) {
		// Initialize mesh
		System.out.print("Initializing mesh...");
		mesh.loadFromFile(filename);
		System.out.println("\tOK");

		// Initialize threadpool
		System.out.print("Initializing threadpool...");
		ShearSort.setThreadsCount(threadsCount);
		System.out.println("\tOK");
	}

	private static void initialize(int X, int Y, int threadsCount) {
		// Initialize mesh
		int m = X;
		int n = Y;
		System.out.print("Initializing mesh...");
		mesh = new Mesh(m, n);
		System.out.println("\tOK");

		// Initialize threadpool
		System.out.print("Initializing threadpool...");
		ShearSort.setThreadsCount(threadsCount);
		System.out.println("\tOK");
	}

	private static void doSort() {
		System.out.println("Sorting started...");
		long start = System.currentTimeMillis();
		try {
			System.out.println("Total iterrations : " + ShearSort.sort(mesh));
		} catch (InterruptedException e) {
			System.out
					.println("Unfortunately something gone wrong with the threadpool... this shouldn't happen...");
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("Sorting finished!");
		System.out.print("Matrix was sorted : ");
		System.out.println(SortChecker.checkIsMatrixSorted(mesh.getMatrix()));
		System.out.println("Total time needed : " + (end - start)
				+ " milliseconds");
	}
}
