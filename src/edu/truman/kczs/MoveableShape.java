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
	private double dx;
	private double dy;
	
	
	public MoveableShape(double xPos, double yPos, int width, int height,Color color, double speed, double dx, double dy) {
		super(xPos, yPos, width, height, color);
		this.speed = speed;
		this.dx = dx;
		this.dy = dy;
	}
	
	public MoveableShape(double xPos, double yPos, int width, int height,Color color) {
		super(xPos, yPos, width, height, color);
		this.speed = 0;
		this.dx = 0;
		this.dy = 0;
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
	public double getDx() {
		// TODO Auto-generated method stub
		return dx;
	}
	
	public double getDy() {
		return dy;
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
	public void setDx(double dx) {
		// TODO Auto-generated method stub
		this.dx = dx;
	}
	
	public void setDy(double dy) {
		this.dy = dy;
	}
	
	public void translate(){
		if (!paused){
			this.setX(this.getX() +  dx * speed);
			this.setY(this.getY() +  dy * speed);
		}
	}
	
	public void flipDirection(Direction direction){
		//double newTheta = 0.0;
		//double x = 0.0;
		//double y = 0.0;
		//if (direction == Direction.UP || direction == Direction.DOWN){
		//	x = -1 * Math.cos(theta);
		//	y = Math.sin(theta);
		//} else if (direction == Direction.LEFT || direction == Direction.RIGHT){
		//	x = Math.cos(theta);
		//	y = -1 * Math.sin(theta);
		//}
		//newTheta = Math.atan(y/x);
		//if ((x > 0 && y > 0) || (x < 0 && y < 0)) newTheta *= -1;
		
		if (direction == Direction.RIGHT || direction == Direction.LEFT){
			dx *= -1;
		} else if (direction == Direction.UP || direction == Direction.DOWN) {
			dy *= -1;
		}
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
