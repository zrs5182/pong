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
	public int getX();
	public int getY();
	public int getWidth();
	public int getHeight();
	public Direction isColliding(Collidable obj1);
}
