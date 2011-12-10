import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
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
   /**
    * @param args
    */
   public static void main(String[] args) {
	   JFrame frame = new JFrame();
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   final SceneComponent scene = new SceneComponent();
	   final PlayerPanel player1Options = new PlayerPanel(1);
	   final PlayerPanel player2Options = new PlayerPanel(2);
	   JButton startButton = new JButton("New Game");
	   JPanel optionsPanel = new JPanel();
	   optionsPanel.add(player1Options, BorderLayout.WEST);
	   optionsPanel.add(startButton, BorderLayout.CENTER);
	   optionsPanel.add(player2Options, BorderLayout.EAST);
	   frame.add(optionsPanel, BorderLayout.SOUTH);
	   frame.add(scene, BorderLayout.CENTER);
	   frame.setSize(WIN_WIDTH, WIN_HEIGHT);
	   frame.setVisible(true);
	   
	   RunnableGame game = new RunnableGame(scene, player1Options.isHuman(), player1Options.getSkillLevel(), player1Options.getColor(), player2Options.isHuman(), player2Options.getSkillLevel(), player2Options.getColor());
	   gameThread = new Thread(game);
	   gameThread.start();
	   
	   startButton.addActionListener(new
			   ActionListener() {
			    public void actionPerformed(ActionEvent event) {
			    	gameThread.interrupt();
					   RunnableGame game = new RunnableGame(scene, player1Options.isHuman(), player1Options.getSkillLevel(), player1Options.getColor(), player2Options.isHuman(), player2Options.getSkillLevel(), player2Options.getColor());
					   gameThread = new Thread(game);
					   gameThread.start();
			    }
			  });
   }

}
