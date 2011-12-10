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
import javax.swing.border.BevelBorder;

/**
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
@SuppressWarnings("serial")
public class ScorePanel extends JPanel {
	private int playerScore;
	private JLabel scoreLabel;
	private Color color;
	private Font font;
	public ScorePanel(Color color, Font font)
	{
		this.font = font;
		this.color = color;
		scoreLabel = new JLabel(Integer.toString(playerScore));
		scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		scoreLabel.setFont(this.font);
		scoreLabel.setForeground(this.color);
		scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
		scoreLabel.setAlignmentY(CENTER_ALIGNMENT);
		setBorder(new BevelBorder(BevelBorder.RAISED));
		setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(scoreLabel);

	}
	
	public void setScore(int score){
	playerScore = score;
	remove(scoreLabel);
	scoreLabel = new JLabel(Integer.toString(playerScore));
	scoreLabel.setFont(font);
	scoreLabel.setForeground(color);
	scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
	scoreLabel.setAlignmentY(CENTER_ALIGNMENT);
	add(scoreLabel);
	}
	
	public void setColor(Color newColor){
		color = newColor;
	}
	
	public void paintComponent(Graphics g){
		
		scoreLabel.repaint();
	}
}