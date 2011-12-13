/**
 * 
 */
package edu.truman.kczs;

import java.awt.Color;

/**
 * Defines a player
 * @author Kyler Carlson
 * @author Zach Schwartz
 */
public class Player {
   private boolean isHuman;
   private SkillLevel skillLevel;
   private Color color;
   /**
    * Constructs a player object
    * @param isHuman Boolean defining if the player is a human player
    * @param skillLevel The skill level of the player
    * @param color The color of the player
    */
   public Player(boolean isHuman, SkillLevel skillLevel, Color color){
      this.isHuman = isHuman;
      this.skillLevel = skillLevel;
      this.color = color;
   }

   /**
    * Returns the colors of the player
    * @return The color of the player
    */
   public Color getColor(){
	   return color;
   }

}