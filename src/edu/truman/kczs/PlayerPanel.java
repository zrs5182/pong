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
import javax.swing.border.TitledBorder;

/**
 * 
 */

/**
 * @author Kyler Carlson
 * @author Zach Schwartz
 * 
 */
@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {
	static int id=1;
	JRadioButton playerHuman;
	JRadioButton playerComputer;
	JRadioButton beginnerButton;
	JRadioButton intermediateButton;
	JRadioButton expertButton;
	ArrayList<Color> colors;
	ArrayList<String> colorNames;
	JComboBox colorList;


	public PlayerPanel(int playerNumber) {
		// Create the Radio Buttons
		boolean isSelected = true;
		if(id % 2 == 0)
			isSelected = false;
		playerHuman = new JRadioButton("Human", isSelected);
		playerComputer = new JRadioButton("Computer", !isSelected);

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
		colorList = new JComboBox();
		for (String color : colorNames) {
			colorList.addItem(color);
		}
		
		colorList.setSelectedIndex(id-1);

		// Add buttons to player group
		ButtonGroup playerTypeGroup = new ButtonGroup();
		playerTypeGroup.add(playerHuman);
		playerTypeGroup.add(playerComputer);

		

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

		// Creates a panel for player type and adds buttons to a panel
				JPanel typeMenu = new JPanel();
				typeMenu.setLayout(new BoxLayout(typeMenu, BoxLayout.PAGE_AXIS));
				typeMenu.setBorder(new TitledBorder("Type"));
				typeMenu.add(playerHuman);
				typeMenu.add(playerComputer);
				typeMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
				
		//makes a panel to contain the color drop down box and the player types
				JPanel playerStatus = new JPanel();
				playerStatus.setLayout(new BoxLayout(playerStatus, BoxLayout.PAGE_AXIS));
				playerStatus.add(typeMenu);
				playerStatus.add(colorMenu);
				
		//adds the menu panels
		setBorder(new TitledBorder("Player " + playerNumber
				+ " Options"));
		setLayout(new BorderLayout());
		add(playerStatus, BorderLayout.WEST);
		add(skillMenu, BorderLayout.EAST);
		setAlignmentX(Component.CENTER_ALIGNMENT);
		
		id++;
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

	public SkillLevel getSkillLevel() {
		if (beginnerButton.isSelected()) {
			return SkillLevel.BEGINNER;
		} else if (intermediateButton.isSelected()) {
			return SkillLevel.INTERMEDIATE;
		} else {
			return SkillLevel.EXPERT;
		}
	}
}
