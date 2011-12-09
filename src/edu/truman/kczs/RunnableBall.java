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
	
	public RunnableBall(double xPos, double yPos, int width, int height,
			Color color, double speed, double dx, double dy) {
		super(xPos, yPos, width, height, color, speed, dx, dy);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				this.translate();
				Thread.sleep(Constants.THREAD_DELAY);
			}
			catch (InterruptedException exception) {
				System.out.println("Ball Thread Exception");
			}
		
		}
	}

}
