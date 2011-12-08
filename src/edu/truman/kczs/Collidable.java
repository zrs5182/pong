/**
 * 
 */
package edu.truman.kczs;

/**
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
public interface Collidable {
	public double getX();
	public double getY();
	public int getWidth();
	public int getHeight();
	public Direction isColliding(Collidable obj1);
}
