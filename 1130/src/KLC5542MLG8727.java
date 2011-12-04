import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.truman.klc5542mlg8727.CarShape;
import edu.truman.klc5542mlg8727.TruckShape;
import edu.truman.klc5542mlg8727.SceneComponent;

/**
 * Main class that makes a frame, buttons, and randomizes shapes created
 * @author Kyler Carlson
 * @author Missy Greene
 * @date 1102
 */
public class KLC5542MLG8727 {
	
	public static final int WIN_WIDTH = 300;
	public static final int WIN_HEIGHT = 300;
	public static final int MIN_WIDTH = 10;
	
	/**
	 * The main method that runs the program
	 * @param args command line arguments - none expected
	 */
   public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final SceneComponent scene = new SceneComponent();

		JButton houseButton = new JButton("Truck");
		houseButton.addActionListener(new
				ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				int sceneWidth = scene.getWidth();
				int sceneHeight = scene.getHeight();
				int xPos = randomizeX(sceneWidth);
				int yPos = randomizeY(sceneHeight);
				int width = randomizeWidth(xPos, sceneWidth);
				Color color = randomizeColor();
				scene.add(new TruckShape(xPos, yPos, width, color));
			}
		});

		JButton carButton = new JButton("Car");
		carButton.addActionListener(new
				ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				int sceneWidth = scene.getWidth();
				int sceneHeight = scene.getHeight();
				int xPos = randomizeX(sceneWidth);
				int yPos = randomizeY(sceneHeight);
				int width = randomizeWidth(xPos, sceneWidth);
				Color color = randomizeColor();
				scene.add(new CarShape(xPos, yPos, width, color));
			}
		});

		JPanel buttons = new JPanel();
		buttons.add(houseButton);
		buttons.add(carButton);
		frame.add(buttons, BorderLayout.NORTH);
		
		frame.add(scene, BorderLayout.CENTER);
		frame.setSize(WIN_WIDTH, WIN_HEIGHT);
		frame.setVisible(true);
	}
	
   /**
    * A randomly generated color
    * @return randomly generated color
    */
	public static Color randomizeColor(){
		int red = (int) (Math.random() * 255);
		int green = (int) (Math.random() * 255);
		int blue = (int) (Math.random() * 255);
		
		return new Color(red, green, blue);
	}
	
	/**
	 * Returns a random width that must be greater than MIN_WIDTH but that will attempt to minimize screen overhang
	 * @param currentX the current x position of the shape
	 * @param aSceneWidth the width of the scene
	 * @return randomly generated int between MIN_WIDTH and aSceneWidth - currentX
	 */
	public static int randomizeWidth(int currentX, int aSceneWidth){
		int tempWidth = (int) (Math.random() * (aSceneWidth - currentX));
		if (tempWidth < MIN_WIDTH) tempWidth = MIN_WIDTH;
		return tempWidth;
	}
	
	/**
	 * Returns a randomly generated x position between 0 and aSceneWidth - MIN_WIDTH
	 * @param aSceneWidth the width of the scene
	 * @return random x position between 0 and aSceneWidth - MIN_WIDTH
	 */
	public static int randomizeX(int aSceneWidth){
		int tempX = (int) (Math.random() * aSceneWidth);
		if (aSceneWidth - tempX < MIN_WIDTH) tempX = aSceneWidth - MIN_WIDTH;
		return tempX;
	}
	
	/**
	 * Returns a randomly generated height
	 * @param aSceneHeight the height of the scene
	 * @return randomly generated height between 0 and aSceneHeight
	 */
	public static int randomizeY(int aSceneHeight){
		return (int) (Math.random() * aSceneHeight);
	}
}
