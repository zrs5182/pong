package edu.truman.kczs;

import java.awt.Color;

/**
 * Defines a Paddle for the game Pong
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
public class Paddle extends MoveableShape{

	/**
	 * Constructs a paddle
	 * @param xPos The top left x-coordinate for the paddle to be placed
	 * @param yPos The top left y-coordinate for the paddle to be placed
	 * @param width The length in the x-direction the paddle extends
	 * @param height The length in the y-direction the paddle extends
	 * @param color The color in which the paddle will display
	 * @param speed The speed at which the paddle will travel
	 * @param dx The x-component of the paddle's trajectory
	 * @param dy The y-component of the paddle's trajectory
	 * @param top The top of the rectangle that defines the paddle
	 * @param bot The bottom of the rectangle that defines the paddle
	 * @param left The left side of the rectangle that defines the paddle
	 * @param right The right side of the rectangle that defines the paddle
	 */
   public Paddle(double xPos, double yPos, int width, int height, Color color, double speed, double dx, double dy, double top, double bot, double left, double right) {
      super(xPos, yPos, width, height, color, speed, dx, dy, top, top, left, right);
   }

}
