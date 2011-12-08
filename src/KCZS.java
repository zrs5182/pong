import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import edu.truman.kczs.Ball;
import edu.truman.kczs.Direction;
import edu.truman.kczs.Field;
import edu.truman.kczs.GoalWall;
import edu.truman.kczs.Paddle;
import edu.truman.kczs.PlayerPanel;
import edu.truman.kczs.RunnableBall;
import edu.truman.kczs.RunnablePaddle;
import edu.truman.kczs.SceneComponent;
import edu.truman.kczs.Wall;


/**
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
public class KCZS {

	public static final int WIN_WIDTH = 900;
	public static final int WIN_HEIGHT = 800;
	public static final int WALL_THICKNESS = 20;
	public static final int PADDLE_HEIGHT = 560;//150;
	public static final Color BACKGROUND = Color.black;
	public static final Color LINE_COLOR = Color.gray;
	public static final Color WALL_COLOR = Color.white;
	public static final Color PADDLE1_COLOR = Color.red;
	public static final Color PADDLE2_COLOR = Color.blue;
	public static final Color BALL_COLOR = Color.green;
	public static final double PADDLE_INIT_SPEED = 0.0;
	
	public static final double BALL_SPEED = 2.0; //will change
	

	
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
	   
	   final Field field = new Field(scene, BACKGROUND, LINE_COLOR, WALL_THICKNESS);
	   final GoalWall leftWall = new GoalWall(0, 0+WALL_THICKNESS, WALL_THICKNESS, scene.getHeight() - WALL_THICKNESS*2);
	   final GoalWall rightWall = new GoalWall(scene.getWidth()- WALL_THICKNESS, 0+WALL_THICKNESS, WALL_THICKNESS, scene.getHeight() - WALL_THICKNESS*2);
	   final Wall topWall = new Wall(0, 0, scene.getWidth() ,WALL_THICKNESS, WALL_COLOR);
	   final Wall botWall = new Wall(0, scene.getHeight() - WALL_THICKNESS, scene.getWidth() ,WALL_THICKNESS, WALL_COLOR);
	   final RunnablePaddle paddle1 = new RunnablePaddle(0,(scene.getHeight()-PADDLE_HEIGHT) / 2 ,WALL_THICKNESS, PADDLE_HEIGHT, PADDLE1_COLOR, PADDLE_INIT_SPEED, 0.0, 0.0);
	   final RunnablePaddle paddle2 = new RunnablePaddle(scene.getWidth()-WALL_THICKNESS,(scene.getHeight()-PADDLE_HEIGHT) / 2 ,WALL_THICKNESS, PADDLE_HEIGHT, PADDLE2_COLOR, PADDLE_INIT_SPEED, 0.0, 0.0);
	   final RunnableBall ball = new RunnableBall((scene.getWidth()-WALL_THICKNESS)/2, (scene.getHeight()-WALL_THICKNESS)/2, WALL_THICKNESS, WALL_THICKNESS, BALL_COLOR, BALL_SPEED, 1.0, 0.5);
	   Thread ballThread = new Thread(ball);
	   Thread paddle1Thread = new Thread(paddle1);
	   Thread paddle2Thread = new Thread(paddle2);
	   
	   ballThread.start();
	   paddle1Thread.start();
	   paddle2Thread.start();
	   
	   scene.add(field);
	   scene.add(topWall);
	   scene.add(botWall);
	   scene.add(paddle1);
	   scene.add(paddle2);
	   scene.add(ball);
	   
	   ActionListener listener = new
		         ActionListener() {
		            public void actionPerformed(ActionEvent event) {
		               Direction dir1 = paddle2.isColliding(ball);
		               Direction dir2 = paddle1.isColliding(ball);
		               Direction dir3 = topWall.isColliding(ball);
		               Direction dir4 = botWall.isColliding(ball);
		            	if (dir1 != Direction.NONE){
		            	   if (ball.getColliding() == false) ball.flipDirection(dir1);
		            	   //System.out.println(dir1);
		               } else if (dir2 != Direction.NONE){
		            	   if (ball.getColliding() == false) ball.flipDirection(dir2);
		            	   //System.out.println(dir2);
		               } else if (dir3 != Direction.NONE){
		            	   if (ball.getColliding() == false) ball.flipDirection(dir3);
		            	   //System.out.println(dir3);
		               } else if (dir4 != Direction.NONE){
		            	   if (ball.getColliding() == false) ball.flipDirection(dir4);
		            	   //System.out.println(dir4);
		               }
		            	
		            	if (dir1 != Direction.NONE || dir2 != Direction.NONE || dir3 != Direction.NONE || dir4 != Direction.NONE) {
		            		ball.setColliding(true);
		            	} else {
		            		ball. setColliding(false);
		            	}
		               scene.repaint();
		            }
		      };
	   
	   Timer t = new Timer(5, listener);
	   t.start();
	   
   }

}
