package edu.truman.kczs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * 
 */

/**
 * @author Zach
 * 
 */
@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {
	JRadioButton playerHuman;
	JRadioButton playerComputer;
	JRadioButton beginnerButton;
	JRadioButton intermediateButton;
	JRadioButton expertButton;
	ArrayList<Color> colors;
	ArrayList<String> colorNames;
	JComboBox<String> colorList;


	public PlayerPanel(int playerNumber) {
		// Create the Radio Buttons
		playerHuman = new JRadioButton("Human", true);
		playerComputer = new JRadioButton("Computer");

		// creates skill level buttons
		beginnerButton = new JRadioButton("Beginner", true);
		intermediateButton = new JRadioButton("Intermediate");
		expertButton = new JRadioButton("Expert");

		// creates a color drop down box
		colors = new ArrayList<Color>();
		colorNames = new ArrayList<String>();
		colors.add(Color.red);
		colorNames.add("Red");
		colors.add(Color.blue);
		colorNames.add("Blue");
		colors.add(Color.green);
		colorNames.add("Green");
		colorList = new JComboBox<String>();
		for (String color : colorNames) {
			colorList.addItem(color);
		}

		// Add buttons to player group
		ButtonGroup playerTypeGroup = new ButtonGroup();
		playerTypeGroup.add(playerHuman);
		playerTypeGroup.add(playerComputer);

		// Creates a panel for player type and adds buttons to a panel
		JPanel typeMenu = new JPanel();
		typeMenu.setLayout(new BoxLayout(typeMenu, BoxLayout.PAGE_AXIS));
		typeMenu.setBorder(new EtchedBorder());
		typeMenu.add(playerHuman);
		typeMenu.add(playerComputer);
		typeMenu.setAlignmentX(Component.CENTER_ALIGNMENT);

		// groups skill level buttons for player
		ButtonGroup skillGroup = new ButtonGroup();
		skillGroup.add(beginnerButton);
		skillGroup.add(intermediateButton);
		skillGroup.add(expertButton);

		// puts all the skill buttons in a panel for player
		JPanel skillMenu = new JPanel();
		skillMenu.setBorder(new TitledBorder("Skill Level"));
		skillMenu.setLayout(new BoxLayout(skillMenu, BoxLayout.PAGE_AXIS));
		skillMenu.add(beginnerButton);
		skillMenu.add(intermediateButton);
		skillMenu.add(expertButton);
		skillMenu.setAlignmentX(Component.CENTER_ALIGNMENT);

		// creates the panel for the drop box to go in for player
		JPanel colorMenu = new JPanel();
		colorMenu.setBorder(new TitledBorder("Color"));
		colorMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
		colorMenu.add(colorList);

		// Makes the playerPanel and adds the menu panels
		JPanel playerPanel = new JPanel();
		playerPanel.setBorder(new TitledBorder("Player " + playerNumber
				+ " Options"));
		playerPanel.setLayout(new BorderLayout());
		playerPanel.add(typeMenu, BorderLayout.WEST);
		playerPanel.add(skillMenu, BorderLayout.EAST);
		playerPanel.add(colorMenu, BorderLayout.SOUTH);
		playerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(playerPanel);
	}

	public Color getColor() {
		int selectedIndex = colorList.getSelectedIndex();
		return colors.get(selectedIndex);
	}

	public boolean isHuman() {
		if (playerHuman.isSelected()) {
			return true;
		} else {
			return false;
		}
	}

	public String getSkillLevel() {
		if (beginnerButton.isSelected()) {
			return "Beginner";
		} else if (intermediateButton.isSelected()) {
			return "Intermediate";
		} else {
			return "Expert";
		}
	}
}
