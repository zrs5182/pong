/**
 * 
 */
package edu.truman.kczs;

/**
 * Defines a goal wall object
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
public class GoalWall extends Wall {
/**
 * Constructs a GoalWall
 * @param xPos The x-coordinate for the GoalWall
 * @param yPos The y-coordinate for the GaolWall
 * @param width The length in the x-direction
 * @param height The length in the y-direction
 */
	public GoalWall(double xPos, double yPos, int width, int height) {
		super(xPos, yPos, width, height, null);
	}
}
