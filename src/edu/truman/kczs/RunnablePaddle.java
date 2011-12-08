package edu.truman.kczs;

import java.awt.Color;

public class RunnablePaddle extends Paddle implements Runnable {

	public RunnablePaddle(double xPos, double yPos, int width, int height,
			Color color, int speed) {
		super(xPos, yPos, width, height, color, speed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		while (true) {
			try {
				this.translate(this.getTheta());
				Thread.sleep(Constants.THREAD_DELAY);
			}
			catch (InterruptedException exception) {
				System.out.println("Ball Thread Exception");
			}
		
		}

	}

}
