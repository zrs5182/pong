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
	private boolean paused = false;
	private boolean colliding = false;
	private double dx;
	private double dy;
	private double top;
	private double bot;
	private double left;
	private double right;
	
	
	
	
	public MoveableShape(double xPos, double yPos, int width, int height,Color color, double speed, double dx, double dy, double top, double bot, double left, double right) {
		super(xPos, yPos, width, height, color);
		this.speed = speed;
		this.dx = dx;
		this.dy = dy;
		this.top = top;
		this.bot = bot;
		this.left = left;
		this.right = right;
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
			this.setBoundedX(this.getX() +  dx * speed, this.getWidth(), left, right);
			this.setBoundedY(this.getY() +  dy * speed, this.getHeight(), top, bot);
		}
	}
	
	public void flipDirection(Direction direction){
		if (direction == Direction.RIGHT || direction == Direction.LEFT){
			dx *= -1;
		} else if (direction == Direction.UP || direction == Direction.DOWN) {
			dy *= -1;
		} else if (direction == Direction.REFLECT) {
			// hits corner of board
			dx *= -1;
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
	
	
	/**
	 * Will translate in the X direction but not allow for the shape to leave boundaries
	 * @param targetX the desired X
	 * @param width the width of the moving object
	 * @param left the minimum allowed x
	 * @param right the maximum allowed position of the shape (must include width)
	 */
	public void setBoundedX(double targetX, int width, double left, double right){
		if (targetX < left) {
			targetX = left;
		} else if (targetX + width > right) {
			targetX = right - width;
		}
		
		this.setX(targetX);
	}
	
	/**
	 * Will translate in the Y direction but not allow for the shape to leave boundaries
	 * @param targetY the desired Y
	 * @param height the height of the moving object
	 * @param top the minimum allowed y
	 * @param bottom the maximum allowed position of the shape (must include height)
	 */
	public void setBoundedY(double targetY, int height, double top, double bottom){
		if (targetY < top) {
			targetY = top;
		} else if (targetY + height > bottom) {
			targetY = bottom - height;
		}
		this.setY(targetY);
	}
	
	public void setTop(double top){
		this.top = top;
	}
	
	public void setBot(double bot){
		this.bot = bot;
	}
	
	public void setLeft(double left){
		this.left = left;
	}
	
	public void setRight(double right){
		this.right = right;
	}
}
