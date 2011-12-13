package edu.truman.kczs;

import java.awt.Color;
/**
 * Defines a paddle that implements the Runnable interface
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
public class RunnablePaddle extends Paddle implements Runnable {
	private boolean threadIsAlive = true;
	
	/**
	 * Constructs a paddle that is runnable
	 * @param xPos The top left x-coordinate for the paddle to be placed
	 * @param yPos The top left y-coordinate for the paddle to be placed
	 * @param width The length in the x-direction the paddle extends
	 * @param height The length in the y-direction the paddle extends
	 * @param color The color in which the paddle will display
	 * @param speed The speed at which the paddle will travel
	 * @param dx The x-component of the paddle's trajectory
	 * @param dy The y-component of the paddle's trajectory
	 * @param top The top bound of legal translation
	 * @param bot The bottom bound of legal translation
	 * @param left The left bound of legal translation
	 * @param right The right bound of legal translation
	 */
	public RunnablePaddle(double xPos, double yPos, int width, int height,
			Color color, double speed, double dx, double dy, double top, double bot, double left, double right) {
		super(xPos, yPos, width, height, color, speed, dx, dy, top, top, left, right);
	}

	/**
	 * Defines how a RunnablePaddle thread will behave
	 */
	public void run() {
		while (threadIsAlive) {
			try {
				this.translate();
				Thread.sleep(Constants.THREAD_DELAY);
			}
			catch (InterruptedException exception) {
				System.out.println("Paddle Thread Interrupted");
			}
		
		}

	}
	
	/**
	 * Kills the while loop running thread
	 */
	public void end() {
		threadIsAlive = false;
	}

}
