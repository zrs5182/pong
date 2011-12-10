/**
 * 
 */
package edu.truman.kczs;

import java.awt.Color;

/**
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
public class Player {
   private String name;
   private int score;
   private Color color;
   
   public Player(String name, Color color){
      this.name = name;
      this.color = color;
   }

   public void addPoint(){
      score++;
   }
   
   public int getScore(){
      return score;
   }
   
   public Color getColor(){
	   return color;
   }
   
   public String toString(){
      return name;
   }
}