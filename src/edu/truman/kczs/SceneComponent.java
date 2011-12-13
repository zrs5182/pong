/**
 * 
 */
package edu.truman.kczs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * Defines the scene in which objects will be painted
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
@SuppressWarnings("serial")
public class SceneComponent extends JComponent {
   private ArrayList<Drawable> pieces;
   /**
    * Constructs the scene
    */
   public SceneComponent(){
   this.pieces = new ArrayList<Drawable>();
   }
   /**
    * Adds a Drawable piece to the scene
    * @param p A Drawable piece to be added to the scene
    */
   public void add(Drawable p) {
      pieces.add(p);
      repaint();
   }
   
   /**
    * Removes a Drawable piece from the scene
    * @param p A Drawable piece to be removed
    */
   public void remove(Drawable p) {
	   pieces.remove(p);
   }
   
   /**
    * Defines how a scene is painted to the screen
    */
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      for (Drawable p  : pieces) {
         p.draw(g2);
      }
   }
}
