/**
 * 
 */
package edu.truman.kczs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Defines shapes that will be involved in game play
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
public abstract class GameShape implements Collidable, Drawable{
   private double xPos;
   private double yPos;
   private int width;
   private int height;
   private Color color;
   
   /**
    * Constructs a GameShape object
    * @param xPos The x-position of the game object
    * @param yPos The y-position of the game object
    * @param width The length in the x-direction of the game object
    * @param height The length in the y-direction of the game object
    * @param color The color of the game object
    */
   public GameShape(double xPos, double yPos, int width, int height, Color color){
      this.xPos = xPos;
      this.yPos = yPos;
      this.width = width;
      this.height = height;
      this.color = color;
   }
   
/**
 * Returns the x-position
 * @return The x-position
 */
   public double getX() {
      return xPos;
   }

   /**
    * Returns the y-position
    * @return The y-position
    */
   public double getY() {
      return yPos;
   }

   /**
    * Returns the length in the x-direction
    * @return the length in the x-direction
    */
   public int getWidth() {
	   return width;
   }

   /**
    * Returns the length in the y-direction
    * @return the length in the y-direction
    */
   public int getHeight() {
      return height;
   }
   
   /**
    * Returns the color
    * @return The color of the GameShape
    */
   public Color getColor() {
      return color;
   }
   
   /**
    * Sets the color of a game shape
    * @param color The color the game shape will be set to
    */
   public void setColor(Color color) {
      this.color = color;
   }
   
   /**
    * Defines how the shape will be drawn
    */
   public void draw(Graphics2D g2){
      Rectangle.Double rect = new Rectangle.Double(xPos, yPos, width, height);
      g2.setColor(color);
      g2.fill(rect);
      g2.draw(rect);
   }
   /**
    * Determines what direction to objects are colliding in
    */
   public Direction isColliding(Collidable obj) {
	   boolean corner1 = false; // lower right
	   boolean corner2 = false; // lower left
	   boolean corner3 = false; // upper left
	   boolean corner4 = false; // upper right
	   
	   if (xPos < obj.getX() && yPos < obj.getY() && xPos + width > obj.getX() && yPos + height > obj.getY()){
         corner1 = true;
      }
	  if (xPos < obj.getX() + obj.getWidth() && yPos < obj.getY() && xPos + width > obj.getX() + obj.getWidth() && yPos + height > obj.getY()){
         corner2 = true;
      }
	  if (xPos < obj.getX() + obj.getWidth() && yPos < obj.getY() + obj.getHeight() && xPos + width > obj.getX() + obj.getWidth() && yPos + height > obj.getY() + obj.getHeight()){
         corner3 = true; 
      }
	  if (xPos < obj.getX() && yPos < obj.getY() + obj.getHeight() && xPos + width > obj.getX() && yPos + height > obj.getY() + obj.getHeight()){
         corner4 = true; 
      }
	   
	  if ((corner1 && corner2 && corner3) || (corner2 && corner3 && corner4) || (corner1 && corner2 && corner4) || (corner1 && corner3 && corner4)){
		  return Direction.REFLECT; // if 3 corners bounce back
	  } else if (corner1 && corner2) {
		  return Direction.UP;
	  } else if (corner2 && corner3) {
		  return Direction.LEFT;
	  } else if (corner3 && corner4) {
		  return Direction.DOWN;
	  } else if (corner4 && corner1) {
		  return Direction.RIGHT;
	  } else if (corner1) {
		  if (xPos + width - (obj.getX()) <= yPos + height - (obj.getY())){
	        	 return Direction.RIGHT; // in the event of a tie, give it to the defending player
	      } else {
	        	 return Direction.DOWN;
	         }
	  } else if (corner2) {
		  if (obj.getX() + obj.getWidth() - xPos <= yPos + height - obj.getY()){
	        	 return Direction.LEFT; // in the event of a tie, give it to the defending player
	         } else {
	        	 return Direction.UP;
	         }
	  } else if (corner3) {
		  if (obj.getX() + obj.getWidth() - xPos <= obj.getY() + obj.getHeight() - yPos){
	        	 return Direction.LEFT; // in the event of a tie, give it to the defending player
	         } else {
	        	 return Direction.DOWN;
	         }
	  } else if (corner4) {
		  if (xPos + width - (obj.getX()) <= obj.getY() + obj.getHeight() - yPos){
	        	 return Direction.RIGHT; // in the event of a tie, give it to the defending player
	         } else {
	        	 return Direction.UP;
	         }
	  }
	  
      return Direction.NONE;
   }
   
   /**
    * Sets the x-coordinate for the GameShape
    * @param xPos The x-coordinate of the object
    */
   public void setX(double xPos){
	   this.xPos = xPos;
   }
   
   /**
    * Sets the y-coordinate for the GameShape
    * @param yPos The y-coordinate of the object
    */
   public void setY(double yPos){
	   this.yPos = yPos;
   }
   
   /**
    *Sets the length in the x-direction of the GameShape 
    * @param width The new length in the x-direction
    */
   public void setWidth(int width){
	   this.width = width;
   }
   
   /**
    * Sets the length in the y-direction of the GameShape
    * @param height The new length in the y-direction
    */
   public void setHeight(int height){
	   this.height = height;
   }
}
