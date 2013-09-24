package bg.tu.parallelprogramming.concurent;

import java.util.concurrent.Executor;

/**
 * Implementation of the Executor interface. This is a thread pool that manages
 * a constant number of threads that execute tasks from a queue. When a single
 * task is done, a new one is polled from the queue.
 * 
 * @author kaleksandrov
 * 
 */
public class CyclingThreadPool implements Executor {
	/**
	 * The number of threads
	 */
	private int threadsCount;

	/**
	 * The queue with tasks
	 */
	private CyclingQueue tasks;

	/**
	 * The array of working threads
	 */
	private CyclingThread[] threads;

	public CyclingThreadPool(final int threadsCount) {
		this.threadsCount = threadsCount;
		this.tasks = new CyclingQueue();
		this.initThreads();
	}

	/**
	 * Initialize the threads
	 */
	private void initThreads() {
		// Initializing the array
		this.threads = new CyclingThread[this.threadsCount];

		// Creating threads
		for (int i = 0; i < this.threads.length; i++) {
			this.threads[i] = new CyclingThread(this.tasks);
		}

		// Starting threads
		for (Thread thread : this.threads) {
			thread.start();
		}
	}

	@Override
	public void execute(Runnable task) {
		this.tasks.enqueue(task);
	}

	/**
	 * Wait to finish the started tasks and stop the execution of the pending
	 * tasks
	 * 
	 * @throws InterruptedException
	 */
	public void shutdown() throws InterruptedException {
		this.waitToFinishStartedTasks();
		for (CyclingThread thread : this.threads) {
			thread.finishThread();
			thread.interrupt();
		}
	}

	/**
	 * Force the current working thread to wait the other threads to finish
	 * their tasks
	 * 
	 * @throws InterruptedException
	 */
	public void waitToFinishStartedTasks() throws InterruptedException {
		this.tasks.waitOnJoinBarrier();
	}
}
