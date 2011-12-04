/**
 * 
 */
package edu.truman.kczs;

/**
 * @author kylercarlson
 *
 */
public abstract class Wall extends GameShape implements Collidable {
	public Wall(int xPos, int yPos, int width, int height){
	      super(xPos, yPos, width, height);
	   }
	
	public boolean isColliding(Collidable obj1) {
	      // TODO Auto-generated method stub
	      return false;
	   }
}
