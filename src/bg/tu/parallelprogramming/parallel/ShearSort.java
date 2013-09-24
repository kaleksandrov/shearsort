package bg.tu.parallelprogramming.parallel;

import java.util.Collections;

import bg.tu.parallelprogramming.concurent.CyclingThreadPool;
import bg.tu.parallelprogramming.utilities.ArrayUtils;
import bg.tu.parallelprogramming.utilities.Mesh;

/**
 * 
 * @author kaleksandrov
 */
public class ShearSort {

	private static int threadsCount = 0;

	private ShearSort() {
		// Prevent initialization
	}

	static {
		threadsCount = Runtime.getRuntime().availableProcessors();
	}

	public static int sort(Mesh mesh) throws InterruptedException {

		int iterationsCount = 0;
		int height = mesh.getHeight();
		int width = mesh.getWidth();

		CyclingThreadPool pool = new CyclingThreadPool(threadsCount);

		boolean done = false;

		RowSorter[] rowSorters = new RowSorter[height];
		for (int j = 0; j < height; ++j) {
			if (ArrayUtils.odd(j)) {
				rowSorters[j] = new RowSorter(mesh, j,
						Collections.reverseOrder());
			} else {
				rowSorters[j] = new RowSorter(mesh, j);
			}
		}

		ColumnSorter[] columnSorters = new ColumnSorter[width];
		for (int i = 0; i < width; ++i) {
			columnSorters[i] = new ColumnSorter(mesh, i);
		}

		while (!done) {
			iterationsCount++;
			done = true;

			for (int i = 0; i < rowSorters.length; i++) {
				pool.execute(rowSorters[i]);
			}

			pool.waitToFinishStartedTasks();

			for (int i = 0; i < height; ++i) {
				done = rowSorters[i].wasMeshSorted() && done;
			}

			for (int i = 0; i < columnSorters.length; i++) {
				pool.execute(columnSorters[i]);
			}

			pool.waitToFinishStartedTasks();

			for (int i = 0; i < width; ++i) {
				done = columnSorters[i].wasMeshSorted() && done;
			}
		}

		pool.shutdown();

		return iterationsCount;
	}

	public static int getThreadsCount() {
		return ShearSort.threadsCount;
	}

	public static void setThreadsCount(int threadsCount) {
		ShearSort.threadsCount = threadsCount;
	}
}
