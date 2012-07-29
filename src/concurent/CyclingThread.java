package concurent;

/**
 * Implementation of a single task. Each CyclingThread is an instance of a
 * thread that works on a given task from the queue. The thread holds a
 * reference to the queue with tasks. When the current tasks is executed, the
 * next task is polled from the queue and so on until all tasks are done.
 * 
 * @author Kiril Aleksandrov
 * 
 */
public class CyclingThread extends Thread
{

	/**
	 * Reference to the queue with the tasks.
	 */
	private CyclingQueue queue;

	/**
	 * Tell the thread to stop polling tasks from the queue and finish its
	 * execution
	 */
	private boolean setToFinish;

	public CyclingThread(CyclingQueue queue)
	{
		this.queue = queue;
		this.setToFinish = false;
	}

	/**
	 * Raise the flag to force stopping the thread after the current execution
	 * ot the <b>run</b> method.
	 */
	public void finishThread()
	{
		this.setToFinish = true;
	}

	@Override
	public void run()
	{
		while (!super.isInterrupted() && !this.setToFinish)
		{
			Runnable task = null;

			// Getting the next task from the queue
			try
			{
				task = this.queue.deque();
			}
			catch (InterruptedException e)
			{
				if (!this.setToFinish)
				{
					e.printStackTrace();
				}
			}

			// If the queue is empty, wait for new tasks
			if (task != null)
			{
				this.queue.increaseStartedTasks();
				task.run();
				this.queue.increaseFinishedTasks();
			}
		}
	}
}
