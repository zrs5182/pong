package edu.truman.kczs;

import java.awt.Color;

public class RunnablePaddle extends Paddle implements Runnable {

	public RunnablePaddle(double xPos, double yPos, int width, int height,
			Color color, double speed, double dx, double dy) {
		super(xPos, yPos, width, height, color, speed, dx, dy);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		while (true) {
			try {
				this.translate();
				Thread.sleep(Constants.THREAD_DELAY);
			}
			catch (InterruptedException exception) {
				System.out.println("Paddle Thread Interrupted");
			}
		
		}

	}

}
