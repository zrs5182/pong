/**
 * 
 */
package edu.truman.kczs;

import java.awt.Color;

/**
 * Defines a ball that implements the Runnable interface
 * @author Kyler Carlson
 * @auhtor Zach Schwartz
 *
 */
public class RunnableBall extends Ball implements Runnable {

	private boolean threadIsAlive = true;

	/**
	 * Constructs a runnable ball object
	 * @param xPos The top left x-coordinate for the ball to be placed
	 * @param yPos The top left y-coordinate for the ball to be placed
	 * @param width The length in the x-direction the ball extends
	 * @param height The length in the y-direction the ball extends
	 * @param color The color in which the ball will display
	 * @param speed The speed at which the ball will travel
	 * @param dx The x-component of the ball's trajectory
	 * @param dy The y-component of the ball's trajectory
	 * @param top The top bound of legal translation
	 * @param bot The bottom bound of legal translation
	 * @param left The left bound of legal translation
	 * @param right The right bound of legal translation
	 */
	public RunnableBall(double xPos, double yPos, int width, int height,
			Color color, double speed, double dx, double dy, double top, double bot, double left, double right) {
		super(xPos, yPos, width, height, color, speed, dx, dy, top, top, left, right);
	}

	/**
	 * Defines how a RunnableBall thread will behave
	 */
	public void run() {
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

	/**
	 * will let the thread's while loop end
	 */
	public void end() {
		threadIsAlive = false;
	}

}