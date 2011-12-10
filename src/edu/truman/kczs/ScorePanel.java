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
import javax.swing.border.EtchedBorder;

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
		scoreLabel.setForeground(color);
		scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
		scoreLabel.setAlignmentY(CENTER_ALIGNMENT);
		setBorder(new EtchedBorder());
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
	scoreLabel.repaint();
	}
	
	public void setColor(Color newColor){
		color = newColor;
		remove(scoreLabel);
		scoreLabel = new JLabel(Integer.toString(6));
		scoreLabel.setFont(font);
		scoreLabel.setForeground(color);
		scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
		scoreLabel.setAlignmentY(CENTER_ALIGNMENT);
		add(scoreLabel);
		scoreLabel.repaint();
		repaint();
	}
	
	public void paintComponent(Graphics g){
		
		scoreLabel.repaint();
	}
}