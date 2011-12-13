package edu.truman.kczs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
	private static ScorePanel score1;
	private static ScorePanel score2;
   private static JPanel optionsPanel;
	
	public MainClass(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final PlayerPanel player1Options = new PlayerPanel(1, Constants.DEFAULT_FOREGROUND_COLOR, Constants.DEFAULT_BACKGROUND_COLOR);
		final PlayerPanel player2Options = new PlayerPanel(2, Constants.DEFAULT_FOREGROUND_COLOR, Constants.DEFAULT_BACKGROUND_COLOR);

		Font font = new Font("Serif", 0, 120);
		score1 = new ScorePanel(Constants.DEFAULT_PLAYER_ONE_COLOR, Constants.DEFAULT_BACKGROUND_COLOR, font);
		score1.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		score2 = new ScorePanel(Constants.DEFAULT_PLAYER_TWO_COLOR, Constants.DEFAULT_BACKGROUND_COLOR, font);
		
		JButton startButton = new JButton("New Game");
		JButton pauseButton = new JButton("Pause Game");
		
		//makes JButtons opaque/bg/fg
		startButton.setOpaque(true);
		startButton.setBackground(Constants.DEFAULT_BACKGROUND_COLOR);
		startButton.setForeground(Constants.DEFAULT_FOREGROUND_COLOR);
		pauseButton.setOpaque(true);
		pauseButton.setBackground(Constants.DEFAULT_BACKGROUND_COLOR);
		pauseButton.setForeground(Constants.DEFAULT_FOREGROUND_COLOR);
		
		optionsPanel = new JPanel();
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
		
		//sets options panel opaque/bg/fg
		optionsPanel.setBackground(Constants.DEFAULT_BACKGROUND_COLOR);
		optionsPanel.setForeground(Constants.DEFAULT_FOREGROUND_COLOR);
		
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
				game = new RunnableGame(player1Options.isHuman(), player1Options.getSkillLevel(), player1Options.getColor(), player2Options.isHuman(), player2Options.getSkillLevel(), player2Options.getColor());
				gameThread = new Thread(game);
				MainClass.setScoreColors(player1Options.getColor(), player2Options.getColor());
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

	private static void setScores(int player1Score, int player2Score){
		score1.setScore(player1Score);
		score2.setScore(player2Score);
		frame.remove(optionsPanel);
		frame.add(optionsPanel, BorderLayout.SOUTH);
		frame.repaint();
		frame.setVisible(true);
	}
	public static void addSceneComponent(SceneComponent scene) {
		if (oldScene != null) frame.remove(oldScene);
		frame.add(scene, BorderLayout.CENTER);
		frame.repaint();
		frame.setVisible(true);
		oldScene = scene;
	}
	public static void setScoreColors(Color player1Color, Color player2Color){
		score1.setColor(player1Color);
		score2.setColor(player2Color);
		frame.remove(optionsPanel);
		frame.add(optionsPanel, BorderLayout.SOUTH);
		frame.repaint();
		frame.setVisible(true);
	}
	
	public static void setPlayer1Score(int player1Score){
		setScores(player1Score, score2.getScore());
	}
	public static void setPlayer2Score(int player2Score){
		setScores(score1.getScore(), player2Score);
	}
}