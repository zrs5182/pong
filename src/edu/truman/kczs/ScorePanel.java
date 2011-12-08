/**
 * 
 */
package edu.truman.kczs;

import java.awt.Color;
import java.awt.Component;
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
	public ScorePanel(Color color)
	{
		this.color=color;
		scoreLabel = new JLabel(Integer.toString(playerScore));
		scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		scoreLabel.setBackground(color);
		setBorder(new BevelBorder(BevelBorder.RAISED));
		setAlignmentX(Component.CENTER_ALIGNMENT);
		add(scoreLabel);
	}
	
	public void setScore(int score){
	playerScore = score;
	}
	
	public void paintComponent(Graphics g){
		scoreLabel = new JLabel(Integer.toString(playerScore));
	}
}