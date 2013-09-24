package bg.tu.parallelprogramming.parallel;

import bg.tu.parallelprogramming.utilities.Mesh;

/**
 * 
 * @author kaleksandrov
 */
public class Sorter extends Thread {

	protected Mesh mesh;
	protected int index;
	protected boolean wasSorted;

	public Sorter(Mesh mesh, int index) {
		this.mesh = mesh;
		this.index = index;
		this.wasSorted = true;
	}

	public boolean wasMeshSorted() {
		return this.wasSorted;
	}
}
