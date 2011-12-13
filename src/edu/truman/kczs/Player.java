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
   private boolean isHuman;
   private SkillLevel skillLevel;
   private Color color;
   
   public Player(boolean isHuman, SkillLevel skillLevel, Color color){
      this.isHuman = isHuman;
      this.skillLevel = skillLevel;
      this.color = color;
   }

   
   public Color getColor(){
	   return color;
   }

}