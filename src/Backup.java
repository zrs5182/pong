import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import edu.truman.kczs.ScorePanel;


public class Backup {
	
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

}
