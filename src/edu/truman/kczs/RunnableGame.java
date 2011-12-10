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
	public static final int WALL_THICKNESS_DEFAULT = 20;
	public static final int PADDLE_HEIGHT_DEFAULT = 100;//150;
	public static final int PADDLE_DIFFUCULTY_CHANGE = 50;
	public static final Color BACKGROUND_DEFAULT = Color.black;
	public static final Color LINE_COLOR_DEFAULT = Color.gray;
	public static final Color WALL_COLOR_DEFAULT = Color.white;
	public static final Color BALL_COLOR_DEFAULT = Color.green;
	public static final double PADDLE_INIT_SPEED_DEFAULT = 0.0;

	public static final double BALL_SPEED_DEFAULT = 1.0; //will change

	private final SceneComponent scene;
	private final Field field;
	private final GoalWall leftWall;
	private final GoalWall rightWall;
	private final Wall topWall;
	private final Wall botWall;
	private final RunnablePaddle paddle1;
	private final RunnablePaddle paddle2;
	private final RunnableBall ball;
	
	private Color playerOneColor;
	private boolean playerOneHuman;
	private SkillLevel playerOneSkill;
	private int playerOnePaddleHeight = PADDLE_HEIGHT_DEFAULT;
	private Color playerTwoColor;
	private boolean playerTwoHuman;
	private SkillLevel playerTwoSkill;
	private int playerTwoPaddleHeight = PADDLE_HEIGHT_DEFAULT;

	public RunnableGame(SceneComponent scene, boolean p1Hum, SkillLevel p1Skill, Color p1Col, boolean p2Hum, SkillLevel p2Skill, Color p2Col){
		this.scene = scene;
		this.playerOneColor = p1Col;
		this.playerTwoColor = p2Col;
		if (p1Skill == SkillLevel.BEGINNER) {
			playerOnePaddleHeight += PADDLE_DIFFUCULTY_CHANGE;
		} else if (p1Skill == SkillLevel.EXPERT) {
			playerOnePaddleHeight -= PADDLE_DIFFUCULTY_CHANGE;
		}
		if (p2Skill == SkillLevel.BEGINNER) {
			playerTwoPaddleHeight += PADDLE_DIFFUCULTY_CHANGE;
		} else if (p2Skill == SkillLevel.EXPERT) {
			playerTwoPaddleHeight -= PADDLE_DIFFUCULTY_CHANGE;
		}
		field = new Field(scene, BACKGROUND_DEFAULT, LINE_COLOR_DEFAULT, WALL_THICKNESS_DEFAULT);
		leftWall = new GoalWall(0, 0+WALL_THICKNESS_DEFAULT, WALL_THICKNESS_DEFAULT, scene.getHeight() - WALL_THICKNESS_DEFAULT*2);
		rightWall = new GoalWall(scene.getWidth()- WALL_THICKNESS_DEFAULT, 0+WALL_THICKNESS_DEFAULT, WALL_THICKNESS_DEFAULT, scene.getHeight() - WALL_THICKNESS_DEFAULT*2);
		topWall = new Wall(0, 0, scene.getWidth() ,WALL_THICKNESS_DEFAULT, WALL_COLOR_DEFAULT);
		botWall = new Wall(0, scene.getHeight() - WALL_THICKNESS_DEFAULT, scene.getWidth() ,WALL_THICKNESS_DEFAULT, WALL_COLOR_DEFAULT);
		paddle1 = new RunnablePaddle(0,(scene.getHeight()-playerOnePaddleHeight) / 2 ,WALL_THICKNESS_DEFAULT, playerOnePaddleHeight, playerOneColor, PADDLE_INIT_SPEED_DEFAULT, 0.0, 1.0);
		paddle2 = new RunnablePaddle(scene.getWidth()-WALL_THICKNESS_DEFAULT,(scene.getHeight()-playerTwoPaddleHeight) / 2 ,WALL_THICKNESS_DEFAULT, playerTwoPaddleHeight, playerTwoColor, PADDLE_INIT_SPEED_DEFAULT, 0.0, 1.0);
		ball = new RunnableBall((scene.getWidth()-WALL_THICKNESS_DEFAULT)/2, (scene.getHeight()-WALL_THICKNESS_DEFAULT)/2, WALL_THICKNESS_DEFAULT, WALL_THICKNESS_DEFAULT, BALL_COLOR_DEFAULT, BALL_SPEED_DEFAULT, 1.0, 0.0);
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
	
	public void pause(boolean b){
		if (b) {
			ball.pause();
			paddle1.pause();
			paddle2.pause();
		} else {
			ball.unpause();
			paddle1.unpause();
			paddle2.unpause();
		}
	}
}
