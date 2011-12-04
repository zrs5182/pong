import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import edu.truman.kczs.Ball;
import edu.truman.kczs.Field;
import edu.truman.kczs.GoalWall;
import edu.truman.kczs.Paddle;
import edu.truman.kczs.SceneComponent;
import edu.truman.kczs.Wall;


/**
 * @author Kyler Carlson and Zach Schwartz
 *
 */
public class KCZS {

	public static final int WIN_WIDTH = 900;
	public static final int WIN_HEIGHT = 600;
	public static final int WALL_THICKNESS = 20;
	public static final int PADDLE_HEIGHT = 150;
	public static final Color BACKGROUND = Color.black;
	public static final Color LINE_COLOR = Color.white;
	public static final Color WALL_COLOR = Color.white;
	public static final Color PADDLE1_COLOR = Color.red;
	public static final Color PADDLE2_COLOR = Color.blue;
	public static final Color BALL_COLOR = Color.green;
	
   /**
    * @param args
    */
   public static void main(String[] args) {
	   JFrame frame = new JFrame();
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   final SceneComponent scene = new SceneComponent();
	   frame.add(scene, BorderLayout.CENTER);
	   frame.setSize(WIN_WIDTH, WIN_HEIGHT);
	   frame.setVisible(true);

	   
	   final Field field = new Field(scene, BACKGROUND, LINE_COLOR, WALL_THICKNESS);
	   final GoalWall leftWall = new GoalWall(0, 0+WALL_THICKNESS, WALL_THICKNESS, scene.getHeight() - WALL_THICKNESS*2);
	   final GoalWall rightWall = new GoalWall(scene.getWidth()- WALL_THICKNESS, 0+WALL_THICKNESS, WALL_THICKNESS, scene.getHeight() - WALL_THICKNESS*2);
	   final Wall topWall = new Wall(0, 0, scene.getWidth() ,WALL_THICKNESS, WALL_COLOR);
	   final Wall botWall = new Wall(0, scene.getHeight() - WALL_THICKNESS, scene.getWidth() ,WALL_THICKNESS, WALL_COLOR);
	   final Paddle paddle1 = new Paddle(0,(scene.getHeight()-PADDLE_HEIGHT) / 2 ,WALL_THICKNESS, PADDLE_HEIGHT, PADDLE1_COLOR);
	   final Paddle paddle2 = new Paddle(scene.getWidth()-WALL_THICKNESS,(scene.getHeight()-PADDLE_HEIGHT) / 2 ,WALL_THICKNESS, PADDLE_HEIGHT, PADDLE2_COLOR);
	   final Ball ball = new Ball((scene.getWidth()-WALL_THICKNESS)/2, (scene.getHeight()-WALL_THICKNESS)/2, WALL_THICKNESS, WALL_THICKNESS, BALL_COLOR);
	   
	   scene.add(field);
	   scene.add(topWall);
	   scene.add(botWall);
	   scene.add(paddle1);
	   scene.add(paddle2);
	   scene.add(ball);
	   
   }

}
