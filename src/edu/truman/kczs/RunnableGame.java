package edu.truman.kczs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import javax.swing.Timer;

public class RunnableGame implements Runnable{
	public static final int WALL_THICKNESS = 20;
	public static final int PADDLE_HEIGHT = 520;//150;
	public static final Color BACKGROUND = Color.black;
	public static final Color LINE_COLOR = Color.gray;
	public static final Color WALL_COLOR = Color.white;
	public static final Color PADDLE1_COLOR = Color.red;
	public static final Color PADDLE2_COLOR = Color.blue;
	public static final Color BALL_COLOR = Color.green;
	public static final double PADDLE_INIT_SPEED = 0.0;

	public static final double BALL_SPEED = 3.0; //will change

	private final SceneComponent scene;
	private final Field field;
	private final GoalWall leftWall;
	private final GoalWall rightWall;
	private final Wall topWall;
	private final Wall botWall;
	private final RunnablePaddle paddle1;
	private final RunnablePaddle paddle2;
	private final RunnableBall ball;

	public RunnableGame(SceneComponent scene){
		this.scene = scene;
		field = new Field(scene, BACKGROUND, LINE_COLOR, WALL_THICKNESS);
		leftWall = new GoalWall(0, 0+WALL_THICKNESS, WALL_THICKNESS, scene.getHeight() - WALL_THICKNESS*2);
		rightWall = new GoalWall(scene.getWidth()- WALL_THICKNESS, 0+WALL_THICKNESS, WALL_THICKNESS, scene.getHeight() - WALL_THICKNESS*2);
		topWall = new Wall(0, 0, scene.getWidth() ,WALL_THICKNESS, WALL_COLOR);
		botWall = new Wall(0, scene.getHeight() - WALL_THICKNESS, scene.getWidth() ,WALL_THICKNESS, WALL_COLOR);
		paddle1 = new RunnablePaddle(0,(scene.getHeight()-PADDLE_HEIGHT) / 2 ,WALL_THICKNESS, PADDLE_HEIGHT, PADDLE1_COLOR, PADDLE_INIT_SPEED, 0.0, 0.0);
		paddle2 = new RunnablePaddle(scene.getWidth()-WALL_THICKNESS,(scene.getHeight()-PADDLE_HEIGHT) / 2 ,WALL_THICKNESS, PADDLE_HEIGHT, PADDLE2_COLOR, PADDLE_INIT_SPEED, 0.0, 0.0);
		ball = new RunnableBall((scene.getWidth()-WALL_THICKNESS)/2, (scene.getHeight()-WALL_THICKNESS)/2, WALL_THICKNESS, WALL_THICKNESS, BALL_COLOR, BALL_SPEED, 1.0, 0.5);
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
	}

	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
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

				Thread.sleep(Constants.THREAD_DELAY);
			}
			catch (InterruptedException exception) {
				System.out.println("Ball Thread Exception");
			}

		}
	}
}
