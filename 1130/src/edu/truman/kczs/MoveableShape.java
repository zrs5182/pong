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
	
	
	
	public MoveableShape(double xPos, double yPos, int width, int height,Color color, int speed) {
		super(xPos, yPos, width, height, color);
		this.speed = speed;
		// TODO Auto-generated constructor stub
	}
	
	public MoveableShape(double xPos, double yPos, int width, int height,Color color) {
		super(xPos, yPos, width, height, color);
		this.speed = 1;
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
	      this.setX(this.getX() + Math.cos(theta) * speed);
	      this.setY(this.getY() + Math.sin(theta) * speed);
	}
}
