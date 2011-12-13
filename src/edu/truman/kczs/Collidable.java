/**
 * 
 */
package edu.truman.kczs;

/**
 * This interface groups together objects that can collide into other objects
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