package edu.truman.kczs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * Defines what the field will be displayed as
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
public class Field implements Drawable{
	private Color fieldColor;
	private Color lineColor;
	private int lineWidth;
	private int width;
	private int height;

	/**
	 * Constructs the field on which to play
	 * @param width The length of the field in the x-direction
	 * @param height The length of the field in the y-direction
	 * @param fieldColor The color of the field
	 * @param lineColor The color of the center line on the field
	 * @param lineWidth The length of the line in the x-direction
	 */
	public Field(int width, int height, Color fieldColor, Color lineColor, int lineWidth){
		this.fieldColor = fieldColor;
		this.lineColor = lineColor;
		this.lineWidth = lineWidth;
	}

	/**
	 * Defines how the field is going to be drawn
	 */
	public void draw(Graphics2D g2){
		Rectangle2D.Double field = new Rectangle2D.Double(0, 0, width, height);
		Rectangle2D.Double line = new Rectangle2D.Double((width-lineWidth)/2, 0, lineWidth, height );

		g2.setColor(fieldColor);
		g2.fill(field);
		g2.draw(field);

		g2.setColor(lineColor);
		g2.fill(line);
		g2.draw(line);
	}

	/**
	 * Resized the field to a specified width and height
	 * @param width The new length in the x-direction the field will span
	 * @param height The new length in the y-direction the field will span
	 */
	public void resize(int width, int height){
		this.width = width;
		this.height = height;
	}

	/**
	 * Returns the current length in the y-direction of the field
	 * @return The current length in the y-direction of the field
	 */
	public int getHeight(){
		return height;
	}
	/**
	 * Returns the current length in the x-direction of the field
	 * @return the current length in the x-direction of the field
	 */
	public int getWidth() {
		return width;
	}
}
