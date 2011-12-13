/**
 * 
 */
package edu.truman.kczs;

import java.awt.Color;

/**
 * Defines what a MoveableShape
 * @author Kyler Carlson
 * @author Zach Schwartz
 */
public abstract class MoveableShape extends GameShape implements Moveable {
	private double speed;
	private boolean paused = false;
	private double dx;
	private double dy;
	private double top;
	private double bot;
	private double left;
	private double right;



	/**
	 * Defines construction for a MoveableShape Object
	 * @param xPos The x-coordinate
	 * @param yPos The y-coordinate
	 * @param width The length in the x-direction
	 * @param height The length in the y-direction
	 * @param color The color of the MoveableShape
	 * @param speed The speed the object moves at
	 * @param dx The trajectory in the x-direction
	 * @param dy The trajectory in the y-direction
	 * @param top The top side of the rectangle bounding legal translation
	 * @param bot The bottom side of the rectangle bounding legal translation
	 * @param left The left side of the rectangle bounding legal translation
	 * @param right The right side of the rectangle bounding legal translation
	 */
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

	/**
	 * Returns the speed of the object as a double
	 * @return The speed of the object
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * Returns the trajectory in the x-direction
	 * @return The trajectory in the x-direction
	 */
	public double getDx() {

		return dx;
	}

	/**
	 * Returns the trajectory in the y-direction
	 * @return The trajectory in the y-direction
	 */
	public double getDy() {
		return dy;
	}

	/**
	 * Sets the speed of the MoveableShape
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * Sets the trajectory in the x-direction
	 */
	public void setDx(double dx) {
		this.dx = dx;
	}

	/**
	 * Sets the trajectory in the y-direction
	 */	
	public void setDy(double dy) {
		this.dy = dy;
	}

	/**
	 * Moves the MoveableShape using a legal bounding box
	 */
	public void translate(){
		if (!paused){
			this.setBoundedX(this.getX() +  dx * speed, this.getWidth(), left, right);
			this.setBoundedY(this.getY() +  dy * speed, this.getHeight(), top, bot);
		}
	}

	/**
	 * Changes the direction of the MoveableShape
	 * @param direction The direction of the object
	 */
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

		if (Constants.SPEED_INCREMENT_ENABLED) speed += Constants.SPEED_INCREMENT;
	}

	/**
	 * Stops the MoveableObject from moving
	 */
	public void pause() {
		paused = true;
	}

	/**
	 * Resumes the MovealbeObject to moving
	 */
	public void unpause() {
		paused = false;
	}

	/** 
	 * @return paused if the object is paused
	 */
	public boolean getPaused() {
		return paused;
	}

	/**
	 * Will translate in the x-direction but not allow for the shape to leave boundaries
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

	/**
	 * Sets the top bounding legal area for translation of the MoveableShape
	 * @param top The top of the MoveableShape
	 */
	public void setTop(double top){
		this.top = top;
	}

	/**
	 * Sets the bottom bounding legal area for translation of the MoveableShape
	 * @param bot The bottom of the MoveableShape
	 */
	public void setBot(double bot){
		this.bot = bot;
	}

	/**
	 * Sets the left bounding legal area for translation of the MoveableShape
	 * @param left The left side of the MoveableShape
	 */
	public void setLeft(double left){
		this.left = left;
	}

	/**
	 * Sets the right bounding legal area for translation  of the MoveableShape
	 * @param right The right side of the MoveableShape
	 */
	public void setRight(double right){
		this.right = right;
	}

	/**
	 * @return top of legal movement bounding box
	 */
	public double getTop(){
		return top;
	}

	/**
	 * @return bottom of legal movement bounding box
	 */
	public double getBot(){
		return bot;
	}

	/**
	 * @return left of legal movement bounding box
	 */
	public double getLeft(){
		return left;
	}

	/**
	 * @return right of legal movement bounding box
	 */
	public double getRight(){
		return right;
	}
}
