package edu.truman.kczs;

import java.awt.Color;

/**
 * 
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
public class Paddle extends MoveableShape{

   public Paddle(double xPos, double yPos, int width, int height, Color color, double speed, double dx, double dy) {
      super(xPos, yPos, width, height, color, speed, dx, dy);
   }

}