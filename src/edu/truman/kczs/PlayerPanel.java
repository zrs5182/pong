import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.BevelBorder;
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
	JComboBox colorList;
	ButtonGroup playerTypeGroup;
	JPanel typeMenu;
	ButtonGroup skillGroup;
	JPanel skillMenu;
	JPanel colorMenu;
	JPanel playerPanel;
	public PlayerPanel(){		
		 //Create the Radio Buttons 
 		playerHuman = new JRadioButton("Human", true);
 		playerComputer = new JRadioButton("Computer");

 		//creates skill level buttons
 		beginnerButton = new JRadioButton("Beginner", true);
 		intermediateButton = new JRadioButton("Intermediate");
 		expertButton = new JRadioButton("Expert");

 		//creates a color drop down box
 		colors = new ArrayList<Color>();
 		colorNames = new ArrayList<String>();
 		colors.add(Color.red);
 		colorNames.add("Red");
 		colors.add(Color.blue);
 		colorNames.add("Blue");
 		colors.add(Color.green);
 		colorNames.add("Green");
 		colorList = new JComboBox();
 		for(String color : colorNames){
 			colorList.addItem(color);
 		}
 		
 		//Add buttons to player  group
 		playerTypeGroup = new ButtonGroup();
 		playerTypeGroup.add(playerHuman);
 		playerTypeGroup.add(playerComputer);
 		
 		//Creates a panel for player type and adds buttons to a panel
 		typeMenu = new JPanel();
 		typeMenu.setLayout(new BoxLayout(typeMenu,BoxLayout.PAGE_AXIS));
 		typeMenu.setBorder(new EtchedBorder());
 		typeMenu.add(playerHuman);
 		typeMenu.add(playerComputer);
 		typeMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
 		
 		//groups skill level buttons for player
 		skillGroup = new ButtonGroup();
 		skillGroup.add(beginnerButton);
 		skillGroup.add(intermediateButton);
 		skillGroup.add(expertButton);
 		
 		//puts all the skill buttons in a panel for player
 		skillMenu = new JPanel();
 		skillMenu.setBorder(new TitledBorder("Skill Level"));
 		skillMenu.setLayout(new BoxLayout(skillMenu, BoxLayout.PAGE_AXIS));
 		skillMenu.add(beginnerButton);
 		skillMenu.add(intermediateButton);
 		skillMenu.add(expertButton);
 		skillMenu.setAlignmentX(Component.CENTER_ALIGNMENT);

 		//creates the panel for the drop box to go in for player
 		colorMenu = new JPanel();
 		colorMenu.setBorder(new TitledBorder("Color"));
 		colorMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
 		colorMenu.add(colorList); 		

 		//Makes the playerPanel and adds the menu panels
 		playerPanel = new JPanel();
 		playerPanel.setBorder(new TitledBorder("player Options"));
 		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.PAGE_AXIS));
 		playerPanel.add(typeMenu);
 		playerPanel.add(skillMenu);
 		playerPanel.add(colorMenu);
 		playerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
 		add(playerPanel);
 	}	
}
