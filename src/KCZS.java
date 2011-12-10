import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.truman.kczs.PlayerPanel;
import edu.truman.kczs.RunnableGame;
import edu.truman.kczs.SceneComponent;




/**
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
public class KCZS {

	public static final int WIN_WIDTH = 900;
	public static final int WIN_HEIGHT = 760;
	private static Thread gameThread;
	private static boolean paused = false;
	private static RunnableGame game;
	private static SceneComponent scene;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scene = new SceneComponent();
		final PlayerPanel player1Options = new PlayerPanel(1);
		final PlayerPanel player2Options = new PlayerPanel(2);
		final JLabel score1 = new JLabel("0");
		Font font = new Font("Serif", 0, 120);
		score1.setFont(font);
		final JLabel score2 = new JLabel("0");
		score2.setFont(font);
		JButton startButton = new JButton("New Game");
		JButton pauseButton = new JButton("Pause Game");
		JPanel optionsPanel = new JPanel();
		optionsPanel.add(score1, BorderLayout.WEST);
		optionsPanel.add(player1Options, BorderLayout.WEST);
		optionsPanel.add(startButton, BorderLayout.CENTER);
		optionsPanel.add(pauseButton, BorderLayout.CENTER);
		optionsPanel.add(player2Options, BorderLayout.EAST);
		optionsPanel.add(score2, BorderLayout.WEST);
		frame.add(optionsPanel, BorderLayout.SOUTH);
		frame.add(scene, BorderLayout.CENTER);
		frame.setSize(WIN_WIDTH, WIN_HEIGHT);
		frame.setVisible(true);

		game = new RunnableGame(scene, player1Options.isHuman(), player1Options.getSkillLevel(), player1Options.getColor(), player2Options.isHuman(), player2Options.getSkillLevel(), player2Options.getColor());
		gameThread = new Thread(game);
		gameThread.start();


		startButton.addActionListener(new
				ActionListener() {
			public void actionPerformed(ActionEvent event) {
				paused = false; // unpause a game if making a new one
				game.end();
				gameThread.interrupt();
				game = new RunnableGame(scene, player1Options.isHuman(), player1Options.getSkillLevel(), player1Options.getColor(), player2Options.isHuman(), player2Options.getSkillLevel(), player2Options.getColor());
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
		
		scene.addComponentListener(new 
				ComponentListener() {
			public void componentResized(ComponentEvent e){
				game.resize();
			}
			public void componentHidden(ComponentEvent e) {}
			public void componentMoved(ComponentEvent e) {
				game.resize();
			}
			public void componentShown(ComponentEvent e) {
				game.resize();
			}
		});
	}


}
