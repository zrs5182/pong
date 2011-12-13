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
 * This class creates a Player's Options Panel that includes: color, skill level, and player type
 * @author Kyler Carlson
 * @author Zach Schwartz
 */
@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {
	private static int id=1;
	private JRadioButton playerHuman;
	private JRadioButton playerComputer;
	private JRadioButton beginnerButton;
	private JRadioButton intermediateButton;
	private JRadioButton expertButton;
	private ArrayList<Color> colors;
	private ArrayList<String> colorNames;
	private JComboBox colorList;
	private Color backgroundColor = Color.BLACK;
	private Color foregroundColor = Color.WHITE;

	/**
	 * Constructs a Player Option's Panel
	 * @param playerNumber The number of the player being created
	 * @param foregroundColor The color of objects in the foreground of the panel
	 * @param backgroundColor The color of objects in the background of the panel
	 */
	public PlayerPanel(int playerNumber, Color foregroundColor, Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		this.foregroundColor = foregroundColor;

		//makes panel opaque and sets the background color
		setOpaque(true);
		setBackground(backgroundColor);

		//Used to determine which radio button is selected by default
		boolean isSelected = true;
		if(id % 2 == 0)
			isSelected = false;

		// Create the Radio Buttons
		playerHuman = new JRadioButton("Human", isSelected);
		playerComputer = new JRadioButton("Computer", !isSelected);

		//sets their background and foreground colors and makes them opaque
		playerHuman.setOpaque(true);
		playerHuman.setBackground(backgroundColor);
		playerHuman.setForeground(foregroundColor);
		playerComputer.setOpaque(true);
		playerComputer.setBackground(backgroundColor);
		playerComputer.setForeground(foregroundColor);

		// creates skill level buttons
		beginnerButton = new JRadioButton("Beginner", true);
		intermediateButton = new JRadioButton("Intermediate");
		expertButton = new JRadioButton("Expert");

		// Sets the background and foreground colors and makes them opaque
		beginnerButton.setOpaque(true);
		intermediateButton.setOpaque(true);
		expertButton.setOpaque(true);
		beginnerButton.setBackground(backgroundColor);
		beginnerButton.setForeground(foregroundColor);
		intermediateButton.setBackground(backgroundColor);
		intermediateButton.setForeground(foregroundColor);
		expertButton.setBackground(backgroundColor);
		expertButton.setForeground(foregroundColor);

		// creates a color drop down box
		colors = new ArrayList<Color>();
		colorNames = new ArrayList<String>();
		colors.add(Color.red);
		colorNames.add("Red");
		colors.add(Color.blue);
		colorNames.add("Blue");
		colors.add(Color.green);
		colorNames.add("Green");
		colors.add(Color.PINK);
		colorNames.add("Pink");
		colors.add(Color.WHITE);
		colorNames.add("White");
		colors.add(Color.CYAN);
		colorNames.add("Cyan");
		colors.add(Color.MAGENTA);
		colorNames.add("Magenta");
		colors.add(Color.ORANGE);
		colorNames.add("Orange");
		colors.add(Color.YELLOW);
		colorNames.add("Yellow");
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
		TitledBorder title = new TitledBorder("Skill Level");
		title.setTitleColor(foregroundColor);
		skillMenu.setBorder(new TitledBorder(title));
		skillMenu.setLayout(new BoxLayout(skillMenu, BoxLayout.PAGE_AXIS));
		skillMenu.add(beginnerButton);
		skillMenu.add(intermediateButton);
		skillMenu.add(expertButton);
		skillMenu.setAlignmentX(Component.CENTER_ALIGNMENT);

		// makes the skill menu opaque and does the background/foreground setting
		skillMenu.setOpaque(true);
		skillMenu.setBackground(backgroundColor);
		skillMenu.setForeground(foregroundColor);

		//makes colorList opaque/bg/fg
		colorList.setOpaque(true);
		colorList.setBackground(Constants.DEFAULT_BACKGROUND_COLOR);
		colorList.setForeground(Constants.DEFAULT_FOREGROUND_COLOR);

		// creates the panel for the drop box to go in for player
		JPanel colorMenu = new JPanel();
		title = new TitledBorder("Color");
		title.setTitleColor(foregroundColor);
		colorMenu.setBorder(title);
		colorMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
		colorMenu.add(colorList);

		//Sets the colorMenu opaque and does the bg/fg stuff
		colorMenu.setOpaque(true);
		colorMenu.setBackground(backgroundColor);
		colorMenu.setForeground(foregroundColor);

		// Creates a panel for player type and adds buttons to a panel
		JPanel typeMenu = new JPanel();
		typeMenu.setLayout(new BoxLayout(typeMenu, BoxLayout.PAGE_AXIS));
		title = new TitledBorder("Type");
		title.setTitleColor(foregroundColor);
		typeMenu.setBorder(title);
		typeMenu.add(playerHuman);
		typeMenu.add(playerComputer);
		typeMenu.setAlignmentX(Component.CENTER_ALIGNMENT);

		//Sets typeMenu opaque/bg/fg
		typeMenu.setOpaque(true);
		typeMenu.setBackground(backgroundColor);
		typeMenu.setForeground(foregroundColor);

		//makes a panel to contain the color drop down box and the player types
		JPanel playerStatus = new JPanel();
		playerStatus.setLayout(new BoxLayout(playerStatus, BoxLayout.PAGE_AXIS));
		playerStatus.add(typeMenu);
		playerStatus.add(colorMenu);

		//sets the playerStatus opaque/bg/fg
		playerStatus.setOpaque(true);
		playerStatus.setBackground(backgroundColor);
		playerStatus.setForeground(foregroundColor);

		//adds the menu panels
		title = new TitledBorder("Player " + playerNumber
				+ " Options");
		title.setTitleColor(foregroundColor);
		setBorder(title);
		setLayout(new BorderLayout());
		add(playerStatus, BorderLayout.WEST);
		add(skillMenu, BorderLayout.EAST);
		setAlignmentX(Component.CENTER_ALIGNMENT);

		//sets entire panel opaque/bg/fg
		setOpaque(true);
		setBackground(backgroundColor);
		setForeground(foregroundColor);

		id++;
	}
	/**
	 * Gets the current color selected from the Colors JComboBox
	 * @return The current selected color from the JComboBox
	 */
	public Color getColor() {
		int selectedIndex = colorList.getSelectedIndex();
		return colors.get(selectedIndex);
	}

	/**
	 * Determines if the currently selected player is a human player
	 * @return Returns true if the human option is selected
	 */
	public boolean isHuman() {
		if (playerHuman.isSelected()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Determines the skill level being used
	 * @return Returns an enumerated type of SkillLevel based on the selected Skill Level
	 */
	public SkillLevel getSkillLevel() {
		if (beginnerButton.isSelected()) {
			return SkillLevel.BEGINNER;
		} else if (intermediateButton.isSelected()) {
			return SkillLevel.INTERMEDIATE;
		} else {
			return SkillLevel.EXPERT;
		}
	}

	/**
	 * Sets the background color of the components within the Player Panel
	 * @param bgColor Color you wish to set the background to
	 */
	public void setBackgroundColor(Color bgColor){
		backgroundColor = bgColor;
	}

	/**
	 * Sets the foreground color of the components within the Player Panel
	 * @param fgColor
	 */
	public void setForegroundColor(Color fgColor){
		foregroundColor = fgColor;
	}
}
