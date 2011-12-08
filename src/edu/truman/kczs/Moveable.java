/**
 * 
 */
package edu.truman.kczs;

/**
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
public interface Moveable {
	public double getSpeed();
	public double getDx();
	public double getDy();
	public void setX(double xPos); // doesn't belong here
	public void setY(double yPos); // doesn't belong here
	public void setSpeed(double speed);
	public void setDx(double dx);
	public void setDy(double dy);
	public void setWidth(int width); // doesn't belong here
	public void setHeight(int height); // doesn't belong here
	public void translate();
	public void pause();
	public void unpause();
}
