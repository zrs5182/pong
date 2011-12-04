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
	public int getSpeed();
	public int getAngle();
	public int getWidth();
	public int getHeight();
	public boolean isColliding(Collidable obj1);
}
