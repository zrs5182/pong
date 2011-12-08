/**
 * 
 */
package edu.truman.kczs;

import java.awt.Color;

/**
 * @author kylercarlson
 *
 */
public abstract class MoveableShape extends GameShape implements Moveable {
	private double speed;
	private double theta;
	private boolean paused = false;
	private boolean colliding = false;
	
	
	public MoveableShape(double xPos, double yPos, int width, int height,Color color, int speed) {
		super(xPos, yPos, width, height, color);
		this.speed = speed;
		// TODO Auto-generated constructor stub
	}
	
	public MoveableShape(double xPos, double yPos, int width, int height,Color color) {
		super(xPos, yPos, width, height, color);
		this.speed = 0;
		// TODO Auto-generated constructor stub
	} 

	/* (non-Javadoc)
	 * @see edu.truman.kczs.Moveable#getSpeed()
	 */
	public double getSpeed() {
		// TODO Auto-generated method stub
		return speed;
	}

	/* (non-Javadoc)
	 * @see edu.truman.kczs.Moveable#getAngle()
	 */
	public double getTheta() {
		// TODO Auto-generated method stub
		return theta;
	}

	/* (non-Javadoc)
	 * @see edu.truman.kczs.Moveable#setSpeed()
	 */
	public void setSpeed(double speed) {
		// TODO Auto-generated method stub
		this.speed = speed;
	}

	/* (non-Javadoc)
	 * @see edu.truman.kczs.Moveable#setAngle()
	 */
	public void setTheta(double theta) {
		// TODO Auto-generated method stub
		this.theta = theta;
	}
	
	public void translate(double theta){
		if (!paused){
			this.setX(this.getX() + Math.cos(theta) * speed);
			this.setY(this.getY() + Math.sin(theta) * speed);
			System.out.println(this.getX() + " " + this.getY());
		}
	}
	
	public void flipTheta(Direction direction){
		double newTheta = 0.0;
		double x = 0.0;
		double y = 0.0;
		if (direction == Direction.UP || direction == Direction.DOWN){
			x = -1 * Math.cos(theta);
			y = Math.sin(theta);
		} else if (direction == Direction.LEFT || direction == Direction.RIGHT){
			x = Math.cos(theta);
			y = -1 * Math.sin(theta);
		}
		newTheta = Math.atan(y/x);
		if ((x > 0 && y < 0) || (x < 0 && y > 0)) newTheta *= -1;
		System.out.println("Flip theta: " + Math.toDegrees(theta) + " " + Math.toDegrees(newTheta));
		theta = newTheta;
	}
	
	public void pause() {
		paused = true;
	}
	
	public void unpause() {
		paused = false;
	}
	
	public void setColliding(boolean b){
		colliding = b;
	}
	
	public boolean getColliding(){
		return colliding;
	}
}
