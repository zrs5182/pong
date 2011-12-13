/**
 * 
 */
package edu.truman.kczs;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class defines a Score Panel to display the player's score
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
@SuppressWarnings("serial")
public class ScorePanel extends JPanel {
	private int playerScore;
	private JLabel scoreLabel;
	private Color backgroundColor;
	private Color foregroundColor;
	private Font font;
	
	/**
	 * Constructor for a ScorePanel to keep track of and display a player's score
	 * @param foregroundColor Color of objects in the foreground
	 * @param backgroundColor Color of objects in the background
	 * @param font Font in which the score will be displayed
	 */
	public ScorePanel(Color foregroundColor, Color backgroundColor, Font font)
	{
		this.font = font;
		this.backgroundColor = backgroundColor;
		this.foregroundColor = foregroundColor;
		scoreLabel = new JLabel(Integer.toString(playerScore));
		scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		scoreLabel.setFont(this.font);
		scoreLabel.setBackground(backgroundColor);
		scoreLabel.setForeground(foregroundColor);
		setBackground(backgroundColor);
		setForeground(foregroundColor);
		scoreLabel.setOpaque(true);
		setOpaque(true);
		scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
		scoreLabel.setAlignmentY(CENTER_ALIGNMENT);
		setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(scoreLabel);

	}
	
	/**
	 * Sets the score on the score panel
	 * @param score The new score to set for the player
	 */
	public void setScore(int score){
		playerScore = score;
		newLabel(foregroundColor, playerScore);	
	}
	
	/**
	 * Sets the color in which the player's score will be displayed
	 * @param newColor The new color the score will be displayed in
	 */
	public void setColor(Color newColor){
		foregroundColor = newColor;
		newLabel(foregroundColor, playerScore);
	}
	
	/**
	 * Creates a new label that is updated with the score and color
	 * @param newColor The color in which the new JLabel will display the score
	 * @param score The score the new JLabel will display
	 */
	private void newLabel(Color newColor, int score)
	{
		playerScore = score;
		foregroundColor = newColor;
		remove(scoreLabel);
		scoreLabel = new JLabel(Integer.toString(playerScore));
		scoreLabel.setFont(font);
		scoreLabel.setBackground(backgroundColor);
		scoreLabel.setForeground(foregroundColor);
		setBackground(Color.BLACK);
		scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
		scoreLabel.setAlignmentY(CENTER_ALIGNMENT);
		add(scoreLabel);
	}
	/**
	 * Returns the current score of the player
	 * @return The current score of the player
	 */
	public int getScore(){
		return playerScore;
	}
	
	/**
	 * Defines how to paint this component onto the screen
	 */
	public void paintComponent(Graphics g){
		
		scoreLabel.repaint();
	}
}