/**
 * 
 */
package edu.truman.kczs;

import java.awt.Color;

/**
 * This class defines a ball object that is a rectangle
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
public class Ball extends MoveableShape{

	/**
	 * Constructs a ball
	 * @param xPos The top left x-coordinate for the ball to be placed
	 * @param yPos The top left y-coordinate for the ball to be placed
	 * @param width The length in the x-direction the ball extends
	 * @param height The length in the y-direction the ball extends
	 * @param color The color in which the ball will display
	 * @param speed The speed at which the ball will travel
	 * @param dx The x-component of the ball's trajectory
	 * @param dy The y-component of the ball's trajectory
	 * @param top The top of the rectangle that defines the ball
	 * @param bot The bottom of the rectangle that defines the ball
	 * @param left The left side of the rectangle that defines the ball
	 * @param right The right side of the rectangle that defines the ball
	 */
   public Ball(double xPos, double yPos, int width, int height, Color color, double speed, double dx, double dy, double top, double bot, double left, double right) {
      super(xPos, yPos, width, height, color, speed, dx, dy, top, top, left, right);
   }
}
