package edu.truman.kczs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Field implements Drawable{
   private Color fieldColor;
   private Color lineColor;
   private int lineWidth;
   private SceneComponent scene;
   
   public Field(SceneComponent scene, Color fieldColor, Color lineColor, int lineWidth){
      this.fieldColor = fieldColor;
      this.scene = scene;
      this.lineColor = lineColor;
      this.lineWidth = lineWidth;
   }

   public void draw(Graphics2D g2){
      Rectangle2D.Double field = new Rectangle2D.Double(0, 0, scene.getWidth(), scene.getHeight());
      Rectangle2D.Double line = new Rectangle2D.Double((scene.getWidth()-lineWidth)/2, 0, lineWidth, scene.getHeight() );
   
      g2.setColor(fieldColor);
      g2.fill(field);
      g2.draw(field);
      
      g2.setColor(lineColor);
      g2.fill(line);
      g2.draw(line);
   }
}
