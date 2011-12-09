/**
 * 
 */
package edu.truman.kczs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * @author kylercarlson
 *
 */
public abstract class GameShape implements Collidable, Drawable{
   private double xPos;
   private double yPos;
   private int width;
   private int height;
   private Color color;
   
   public GameShape(double xPos, double yPos, int width, int height, Color color){
      this.xPos = xPos;
      this.yPos = yPos;
      this.width = width;
      this.height = height;
      this.color = color;
   }
   
   public double getX() {
      // TODO Auto-generated method stub
      return xPos;
   }

   public double getY() {
      // TODO Auto-generated method stub
      return yPos;
   }

   public int getWidth() {
      // TODO Auto-generated method stub
      return width;
   }

   public int getHeight() {
      // TODO Auto-generated method stub
      return height;
   }
   
   public Color getColor() {
      return color;
   }
   
   public void setColor(Color color) {
      this.color = color;
   }
   
   public void draw(Graphics2D g2){
      Rectangle.Double rect = new Rectangle.Double(xPos, yPos, width, height);
      g2.setColor(color);
      g2.fill(rect);
      g2.draw(rect);
   }
   
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
		  System.out.println("3 Corners");
		  return Direction.REFLECT; // if 3 corners bounce back
	  } else if (corner1 && corner2) {
		  System.out.println(12);
		  return Direction.UP;
	  } else if (corner2 && corner3) {
		  System.out.println(23);
		  return Direction.LEFT;
	  } else if (corner3 && corner4) {
		  System.out.println(34);
		  return Direction.DOWN;
	  } else if (corner4 && corner1) {
		  System.out.println(41);
		  return Direction.RIGHT;
	  } else if (corner1) {
		  System.out.println(1);
		  if (xPos + width - (obj.getX()) <= yPos + height - (obj.getY())){
	        	 return Direction.RIGHT; // in the event of a tie, give it to the defending player
	      } else {
	        	 return Direction.DOWN;
	         }
	  } else if (corner2) {
		  System.out.println(2);
		  if (obj.getX() + obj.getWidth() - xPos <= yPos + height - obj.getY()){
	        	 return Direction.LEFT; // in the event of a tie, give it to the defending player
	         } else {
	        	 return Direction.UP;
	         }
	  } else if (corner3) {
		  System.out.println(3);
		  if (obj.getX() + obj.getWidth() - xPos <= obj.getY() + obj.getHeight() - yPos){
	        	 return Direction.LEFT; // in the event of a tie, give it to the defending player
	         } else {
	        	 return Direction.DOWN;
	         }
	  } else if (corner4) {
		  System.out.println(4);
		  if (xPos + width - (obj.getX()) <= obj.getY() + obj.getHeight() - yPos){
	        	 return Direction.RIGHT; // in the event of a tie, give it to the defending player
	         } else {
	        	 return Direction.UP;
	         }
	  }
	  
      return Direction.NONE;
   }
   
   public void setX(double xPos){
	   this.xPos = xPos;
   }
   
   public void setY(double yPos){
	   this.yPos = yPos;
   }
   
   public void setWidth(int width){
	   this.width = width;
   }
   
   public void setHeight(int height){
	   this.height = height;
   }
}
