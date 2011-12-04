/**
 * 
 */
package edu.truman.kczs;

import java.awt.Color;

/**
 * @author kylercarlson
 *
 */
public abstract class Wall extends GameShape implements Collidable {
	public Wall(int xPos, int yPos, int width, int height, Color color){
	      super(xPos, yPos, width, height, color);
	   }
	
	public Direction isColliding(Collidable obj1) {
	      // TODO Auto-generated method stub
	      return Direction.NONE;
	   }
}
