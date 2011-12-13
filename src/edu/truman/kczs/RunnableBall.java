/**
 * 
 */
package edu.truman.kczs;

import java.awt.Color;

/**
 * @author Kyler
 *
 */
public class RunnableBall extends Ball implements Runnable {

	private boolean threadIsAlive = true;

	public RunnableBall(double xPos, double yPos, int width, int height,
			Color color, double speed, double dx, double dy, double top, double bot, double left, double right) {
		super(xPos, yPos, width, height, color, speed, dx, dy, top, top, left, right);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (threadIsAlive) {
			try {
				this.translate();
				Thread.sleep(Constants.THREAD_DELAY);
			}
			catch (InterruptedException exception) {
				System.out.println("Ball Thread Interrupted");
			}

		}
	}
	
	public void end() {
		threadIsAlive = false;
	}

}