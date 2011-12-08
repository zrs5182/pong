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
	public ScorePanel(Color color)
	{
		scoreLabel = new JLabel(Integer.toString(playerScore));
		scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		scoreLabel.setBackground(color);
		setBorder(new BevelBorder(BevelBorder.RAISED));
		setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(scoreLabel);
	}
	
	public void setScore(int score){
	playerScore = score;
	this.remove(scoreLabel);
	scoreLabel = new JLabel(Integer.toString(playerScore));
	this.add(scoreLabel);
	}
	
	public void paintComponent(Graphics g){
		
		scoreLabel.repaint();
	}
}