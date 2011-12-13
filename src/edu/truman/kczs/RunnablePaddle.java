package edu.truman.kczs;

import java.awt.Color;

public class RunnablePaddle extends Paddle implements Runnable {
	private boolean threadIsAlive = true;
	
	public RunnablePaddle(double xPos, double yPos, int width, int height,
			Color color, double speed, double dx, double dy, double top, double bot, double left, double right) {
		super(xPos, yPos, width, height, color, speed, dx, dy, top, top, left, right);
		// TODO Auto-generated constructor stub
	}

	@Override
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
	
	public void end() {
		threadIsAlive = false;
	}

}
