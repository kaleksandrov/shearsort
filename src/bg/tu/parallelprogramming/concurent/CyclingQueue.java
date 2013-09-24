package bg.tu.parallelprogramming.concurent;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This is an implementation of a queue. It holds runnable tasks that should be
 * executed.
 * 
 * @author kaleksandrov
 * 
 */
public class CyclingQueue {

	/**
	 * The queue with runnable tasks
	 */
	private Queue<Runnable> tasks;

	/**
	 * Flag indicating when the queue can accept more taks
	 */
	private boolean isLocked;

	/**
	 * The started tasks count
	 */
	private int startedTasks;

	/**
	 * The finished tasks count
	 */
	private int finishedTasks;

	/**
	 * Barrier object for the threads to be joined. This mechanism allows to
	 * tell all the threads to wait the others to finish
	 */
	private Object joinBarrier;

	/**
	 * Barrier object fot the threads to wait on when there are no tasks to be
	 * executed. When a task is added to the queue, a single thread is waken up.
	 */
	private Object taskBarrier;

	public CyclingQueue() {
		this.tasks = new LinkedList<Runnable>();
		this.isLocked = false;
		this.startedTasks = 0;
		this.finishedTasks = 0;
		this.joinBarrier = new Object();
		this.taskBarrier = new Object();
	}

	/**
	 * Adds a task to head of the queue
	 * 
	 * @param task
	 *            The task to be added
	 */
	public void enqueue(final Runnable task) {
		synchronized (this.taskBarrier) {
			if (!this.isLocked) {
				this.tasks.add(task);
				this.taskBarrier.notify();
			}
		}
	}

	/**
	 * Remove a task from the tail of the queue
	 * 
	 * @return The last task
	 * @throws InterruptedException
	 *             When synchronization problem occurs
	 */
	public Runnable deque() throws InterruptedException {
		synchronized (this.taskBarrier) {
			if (this.tasks.isEmpty()) {
				this.taskBarrier.wait();
			}
			return this.tasks.poll();
		}
	}

	/**
	 * Rejects all pending tasks. Remove them from the queue and marks the queue
	 * as locked.
	 */
	public void rejectAll() {
		this.isLocked = true;
		this.tasks.clear();
	}

	/**
	 * Reject all new incoming tasks. Marks the queue as locked.
	 */
	public void rejectNew() {
		this.isLocked = true;
	}

	/**
	 * Get started tasks count
	 * 
	 * @return The number of started tasks
	 */
	public int getStartedTasks() {
		return this.startedTasks;
	}

	/**
	 * Increase started tasks counter
	 */
	public void increaseStartedTasks() {
		synchronized (this.joinBarrier) {
			this.startedTasks++;
		}
	}

	/**
	 * Get finished tasks count
	 * 
	 * @return The number of finished tasks
	 */
	public int getFinishedTasks() {
		return this.finishedTasks;
	}

	/**
	 * Increase the number of finished tasks
	 */
	public void increaseFinishedTasks() {
		synchronized (this.joinBarrier) {
			this.finishedTasks++;
			if ((this.finishedTasks == this.startedTasks)
					&& (0 == this.tasks.size())) {
				this.isLocked = false;
				this.joinBarrier.notifyAll();
			}
		}
	}

	/**
	 * Makes the current thread to wait on the join barrier
	 * 
	 * @throws InterruptedException
	 */
	public void waitOnJoinBarrier() throws InterruptedException {
		if (0 != this.tasks.size()) {
			this.isLocked = true;
			synchronized (this.joinBarrier) {
				this.joinBarrier.wait();
			}
		}
	}

	/**
	 * Notiifies all threads that are waiting on the task barrier
	 */
	public void notifyAllThreads() {
		synchronized (this.taskBarrier) {
			this.taskBarrier.notifyAll();
		}
	}
}
