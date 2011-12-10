/**
 * 
 */
package edu.truman.kczs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
@SuppressWarnings("serial")
public class SceneComponent extends JComponent {

   private ArrayList<Drawable> pieces;
   
   public SceneComponent(){
   this.pieces = new ArrayList<Drawable>();
   }
   
   public void add(Drawable p) {
      pieces.add(p);
      repaint();
   }
   
   public void remove(Drawable p) {
	   pieces.remove(p);
   }
   
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      for (Drawable p  : pieces) {
         p.draw(g2);
      }
   }
}
