/**
 * 
 */
package edu.truman.kczs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
public class StatusBar extends JPanel {
	private int player1Score;
	private int player2Score;
	private JPanel player1Board;
	private JPanel player2Board;
	private JPanel optionsPanel;
	private JButton pauseButton;
	private JButton newGameButton;
	public StatusBar()
	{
		player1Board = new JPanel();
		player2Board = new JPanel();
		optionsPanel = new JPanel();
		pauseButton = new JButton();
		newGameButton = new JButton();
		
		//pauseButton.addActionListener(new ActionListener());
		
		optionsPanel.add(pauseButton);
		optionsPanel.add(newGameButton);
		
		
		setLayout(new BorderLayout());
		add(player1Board, BorderLayout.WEST);
		add(optionsPanel, BorderLayout.CENTER);
		add(player2Board, BorderLayout.EAST);
		
		
	}

}
