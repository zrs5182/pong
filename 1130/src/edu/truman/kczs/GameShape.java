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
public abstract class GameShape {
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
      g2.draw(rect);
   }
}
