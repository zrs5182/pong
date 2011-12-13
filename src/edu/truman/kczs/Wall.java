/**
 * 
 */
package edu.truman.kczs;

import java.awt.Color;

/**
 * Defines a wall of the game 
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
public class Wall extends GameShape {
	/**
	 * Constructs a wall
	 * @param xPos The x-coordinate of the wall
	 * @param yPos The y-coordinate of the wall
	 * @param width The length in the x-direction of the wall
	 * @param height The length in the y-direction of the wall
	 * @param color The color of a wall
	 */
	public Wall(double xPos, double yPos, int width, int height, Color color){
		super(xPos, yPos, width, height, color);
	}
}
