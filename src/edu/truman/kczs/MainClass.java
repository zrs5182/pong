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
import javax.swing.JPanel;
import edu.truman.kczs.PlayerPanel;
import edu.truman.kczs.RunnableGame;
import edu.truman.kczs.SceneComponent;

/**
 * Will make the frame for the game, make the bottom panel of the window, and start a RunnableGame thread to handle the game itself
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
public class MainClass {
	private static Thread gameThread;
	private static boolean paused = false;
	private static RunnableGame game;
	private static JFrame frame;
	private static SceneComponent oldScene;
	private static ScorePanel score1;
	private static ScorePanel score2;
	private static JPanel optionsPanel;

	/**
	 * Constructor to make an object
	 */
	public MainClass(){
	}

	/**
	 * Makes a frame, makes bottom panel, adds bottom panel, and makes game thread
	 */
	public void go(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Pong - by: Kyler Carlson and Zach Schwartz");
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

		frame.setSize(Constants.WIN_WIDTH, Constants.WIN_HEIGHT);
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

	/**
	 * Static method to allow the game thread to update scores
	 * @param player1Score Player one's score
	 * @param player2Score Player two's score
	 */
	public static void setPlayerScores(int player1Score, int player2Score){
		score1.setScore(player1Score);
		score2.setScore(player2Score);
		frame.remove(optionsPanel);
		frame.add(optionsPanel, BorderLayout.SOUTH);
		frame.repaint();
		frame.setVisible(true);
	}
	/**
	 * Static method to add the scene made by a RunnableGame to the frame
	 * @param scene the scene to be added
	 */
	public static void addSceneComponent(SceneComponent scene) {
		if (oldScene != null) frame.remove(oldScene);
		frame.add(scene, BorderLayout.CENTER);
		frame.repaint();
		frame.setVisible(true);
		oldScene = scene;
	}
	/**
	 * Static method to allow the game thread to change the color of players on the bottom panel
	 * @param player1Color desired color of player one
	 * @param player2Color desired color of player two
	 */
	public static void setScoreColors(Color player1Color, Color player2Color){
		score1.setColor(player1Color);
		score2.setColor(player2Color);
		frame.remove(optionsPanel);
		frame.add(optionsPanel, BorderLayout.SOUTH);
		frame.repaint();
		frame.setVisible(true);
	}
}