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
	
	public void setScore(int score){
		playerScore = score;
		newLabel(foregroundColor, playerScore);	
	}
	
	public void setColor(Color newColor){
		foregroundColor = newColor;
		newLabel(foregroundColor, playerScore);
	}
	
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
	
	public int getScore(){
		return playerScore;
	}
	
	public void paintComponent(Graphics g){
		
		scoreLabel.repaint();
	}
}