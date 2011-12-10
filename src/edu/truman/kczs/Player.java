/**
 * 
 */
package edu.truman.kczs;

/**
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
public class Player {
   private String name;
   private int score;
   private Paddle paddle;
   
   public Player(String name, int paddleX, int paddleY, int paddleW, int paddleH){
      this.name = name;
   }

   public void addPoint(){
      score++;
   }
   
   public int getScore(){
      return score;
   }
   
   public void moveUp(){
      paddle.translate();
   }
   
   public void moveDown(){
      paddle.translate();
   }
   
   public String toString(){
      return name;
   }
   
   public double getPaddleX(){
	   return paddle.getX();
   }
   
}
