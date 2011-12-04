/**
 * 
 */
package edu.truman.kczs;

/**
 * @author kylercarlson
 *
 */
public abstract class GameShape {
   private int xPos;
   private int yPos;
   private int width;
   private int height;
   
   public GameShape(int xPos, int yPos, int width, int height){
      this.xPos = xPos;
      this.yPos = yPos;
      this.width = width;
      this.height = height;
   }
   
   public int getX() {
      // TODO Auto-generated method stub
      return xPos;
   }

   public int getY() {
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
}
