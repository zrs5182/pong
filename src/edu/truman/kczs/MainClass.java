package edu.truman.kczs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.truman.kczs.PlayerPanel;
import edu.truman.kczs.RunnableGame;
import edu.truman.kczs.SceneComponent;


public class MainClass {
	public static final int WIN_WIDTH = 900;
	public static final int WIN_HEIGHT = 760;
	private static Thread gameThread;
	private static boolean paused = false;
	private static RunnableGame game;
	private static JFrame frame;
	private static SceneComponent oldScene;

	public MainClass(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final PlayerPanel player1Options = new PlayerPanel(1);
		final PlayerPanel player2Options = new PlayerPanel(2);

		Font font = new Font("Serif", 0, 120);
		ScorePanel score1 = new ScorePanel(Color.RED, font);
		score1.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		ScorePanel score2 = new ScorePanel(Color.BLUE, font);
		
		JButton startButton = new JButton("New Game");
		JButton pauseButton = new JButton("Pause Game");
		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = .05;
		c.weighty = .03;
		c.gridheight = 2;
		optionsPanel.add(score1, c);
		c.fill = GridBagConstraints.NONE;
		optionsPanel.add(player1Options, c);
		c.gridheight = 1;
		optionsPanel.add(startButton, c);
		c.gridy = 1;
		c.gridx = 2;
		optionsPanel.add(pauseButton, c);
		c.gridy = 0;
		c.gridx = 3;
		c.gridheight = 2;
		optionsPanel.add(player2Options, c);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 4;
		optionsPanel.add(score2, c);
		frame.add(optionsPanel, BorderLayout.SOUTH);

		frame.setSize(WIN_WIDTH, WIN_HEIGHT);
		frame.setVisible(true);

		game = new RunnableGame(player1Options.isHuman(), player1Options.getSkillLevel(), player1Options.getColor(), player2Options.isHuman(), player2Options.getSkillLevel(), player2Options.getColor());
		gameThread = new Thread(game);
		gameThread.start();

		startButton.addActionListener(new
				ActionListener() {
			public void actionPerformed(ActionEvent event) {
				paused = false; // unpause a game if making a new one
				game.end();
				gameThread.interrupt();
				game = new RunnableGame(player1Options.isHuman(), player1Options.getSkillLevel(), player1Options.getColor(), player2Options.isHuman(), player2Options.getSkillLevel(), player2Options.getColor());
				gameThread = new Thread(game);
				gameThread.start();
			}
		});

		pauseButton.addActionListener(new
				ActionListener() {
			public void actionPerformed(ActionEvent event) {
				paused = !paused;
				game.pause(paused);
			}
		});
	}

	public static void addSceneComponent(SceneComponent scene) {
		frame.add(scene, BorderLayout.CENTER);
		frame.setVisible(true);
	}
}