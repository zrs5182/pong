package edu.truman.kczs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
	public static final int GOAL_WALL_THICKNESS = 20; // slightly thinner to allow paddles to hit first
	public static final int PADDLE_HEIGHT_DEFAULT = 100;//150;
	public static final int PADDLE_DIFFUCULTY_CHANGE = 50;
	public static final Color BACKGROUND_DEFAULT = Color.black;
	public static final Color LINE_COLOR_DEFAULT = Color.gray;
	public static final Color WALL_COLOR_DEFAULT = Color.white;
	public static final Color BALL_COLOR_DEFAULT = Color.green;
	public static final double PADDLE_INIT_SPEED_DEFAULT = 0.0;
	public static final int NEW_BALL_DELAY = 1500; // time to pause thread for new ball

	public static final double BALL_SPEED_DEFAULT = 3.0; //will change
	public static final double BALL_DX_DEFAULT = 1.0;
	public static final double BALL_DY_DEFAULT = 0.5;
	public static final int AI_SEARCH_DISTANCE = 600;

	private SceneComponent scene;
	private final Field field;
	private final GoalWall leftWall;
	private final GoalWall rightWall;
	private final Wall topWall;
	private final Wall botWall;
	private final RunnablePaddle paddle1;
	private final RunnablePaddle paddle2;
	private final RunnableBall ball;
	private final Thread ballThread;
	private final Thread paddle1Thread;
	private final Thread paddle2Thread;
	
	private Color playerOneColor;
	private boolean playerOneHuman;
	private SkillLevel playerOneSkill;
	private int playerOnePaddleHeight = PADDLE_HEIGHT_DEFAULT;
	private Color playerTwoColor;
	private boolean playerTwoHuman;
	private SkillLevel playerTwoSkill;
	private int playerTwoPaddleHeight = PADDLE_HEIGHT_DEFAULT;
	private boolean paused = false;
	
	private int player1Score;
	private int player2Score;
	
	private static double PADDLE_SPEED = 6.0;
	

	public RunnableGame(boolean p1Hum, SkillLevel p1Skill, Color p1Col, boolean p2Hum, SkillLevel p2Skill, Color p2Col){
		scene = new SceneComponent();
		MainClass.addSceneComponent(scene);
		this.playerOneColor = p1Col;
		this.playerOneHuman = p1Hum;
		this.playerOneSkill = p1Skill;
		this.playerTwoColor = p2Col;
		this.playerTwoHuman = p2Hum;
		this.playerTwoSkill = p2Skill;
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
		field = new Field(scene.getWidth(), scene.getHeight(), BACKGROUND_DEFAULT, LINE_COLOR_DEFAULT, WALL_THICKNESS_DEFAULT);
		leftWall = new GoalWall(0, 0+WALL_THICKNESS_DEFAULT, GOAL_WALL_THICKNESS, scene.getHeight() - WALL_THICKNESS_DEFAULT*2);
		rightWall = new GoalWall(scene.getWidth() - GOAL_WALL_THICKNESS, WALL_THICKNESS_DEFAULT, GOAL_WALL_THICKNESS, scene.getHeight() - WALL_THICKNESS_DEFAULT*2);
		topWall = new Wall(0, 0, scene.getWidth() ,WALL_THICKNESS_DEFAULT, WALL_COLOR_DEFAULT);
		botWall = new Wall(0, scene.getHeight() - WALL_THICKNESS_DEFAULT, scene.getWidth() ,WALL_THICKNESS_DEFAULT, WALL_COLOR_DEFAULT);
		paddle1 = new RunnablePaddle(0,(scene.getHeight()-playerOnePaddleHeight) / 2 ,WALL_THICKNESS_DEFAULT, playerOnePaddleHeight, playerOneColor, PADDLE_INIT_SPEED_DEFAULT, 0.0, 1.0, this.getTop(), this.getBot(), getLeft(), WALL_THICKNESS_DEFAULT);
		paddle2 = new RunnablePaddle(scene.getWidth()-WALL_THICKNESS_DEFAULT,(scene.getHeight()-playerTwoPaddleHeight) / 2 ,WALL_THICKNESS_DEFAULT, playerTwoPaddleHeight, playerTwoColor, PADDLE_INIT_SPEED_DEFAULT, 0.0, 1.0, this.getTop(), this.getBot(), this.getRight() - WALL_THICKNESS_DEFAULT, this.getRight());
		ball = new RunnableBall((scene.getWidth()-WALL_THICKNESS_DEFAULT)/2, (scene.getHeight()-WALL_THICKNESS_DEFAULT)/2, WALL_THICKNESS_DEFAULT, WALL_THICKNESS_DEFAULT, BALL_COLOR_DEFAULT, BALL_SPEED_DEFAULT, BALL_DX_DEFAULT, BALL_DY_DEFAULT, this.getTop(), this.getBot(), this.getLeft(), this.getRight());
		ballThread = new Thread(ball);
		paddle1Thread = new Thread(paddle1);
		paddle2Thread = new Thread(paddle2);
		

		ballThread.start();
		paddle1Thread.start();
		paddle2Thread.start();

		scene.add(field);
		scene.add(topWall);
		scene.add(botWall);
		scene.add(paddle1);
		scene.add(paddle2);
		scene.add(ball);
		
		scene.addComponentListener(new 
				ComponentListener() {
			public void componentResized(ComponentEvent e){
				resize();
			}
			public void componentHidden(ComponentEvent e) {}
			public void componentMoved(ComponentEvent e) {
				resize();
			}
			public void componentShown(ComponentEvent e) {
				resize();
			}
		});
		
		scene.addKeyListener(new 
				KeyListener() {
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == Constants.PLAYER_ONE_UP_KEY && playerOneHuman) paddle1.setSpeed(PADDLE_SPEED);
				if (e.getKeyCode() == Constants.PLAYER_ONE_DOWN_KEY && playerOneHuman) paddle1.setSpeed(-1 * PADDLE_SPEED);
				if (e.getKeyCode() == Constants.PLAYER_TWO_UP_KEY && playerTwoHuman) paddle2.setSpeed(PADDLE_SPEED);
				if (e.getKeyCode() == Constants.PLAYER_TWO_DOWN_KEY && playerTwoHuman) paddle2.setSpeed(-1 * PADDLE_SPEED);
			}
			public void keyReleased(KeyEvent e){
				if (e.getKeyCode() == Constants.PLAYER_ONE_UP_KEY && playerOneHuman) paddle1.setSpeed(0.0);
				if (e.getKeyCode() == Constants.PLAYER_ONE_DOWN_KEY && playerOneHuman) paddle1.setSpeed(0.0);
				if (e.getKeyCode() == Constants.PLAYER_TWO_UP_KEY && playerTwoHuman) paddle2.setSpeed(0.0);
				if (e.getKeyCode() == Constants.PLAYER_TWO_DOWN_KEY && playerTwoHuman) paddle2.setSpeed(0.0);
			}
			public void keyTyped(KeyEvent e){
			}
		});
		scene.requestFocus();
		
	}

	public void run() {
		// TODO Auto-generated method stub
		player1Score = 0;
		player2Score = 0;
		MainClass.setPlayer1Score(player1Score);
		MainClass.setPlayer2Score(player2Score);
		
		resize(); // allows the field to be drawn with the scene's width and height since they are initialized to 0
		
		scene.addKeyListener(new 
				KeyListener() {
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == Constants.PLAYER_ONE_UP_KEY && playerOneHuman) paddle1.setSpeed(PADDLE_SPEED);
				if (e.getKeyCode() == Constants.PLAYER_ONE_DOWN_KEY && playerOneHuman) paddle1.setSpeed(-1 * PADDLE_SPEED);
				if (e.getKeyCode() == Constants.PLAYER_TWO_UP_KEY && playerTwoHuman) paddle2.setSpeed(PADDLE_SPEED);
				if (e.getKeyCode() == Constants.PLAYER_TWO_DOWN_KEY && playerTwoHuman) paddle2.setSpeed(-1 * PADDLE_SPEED);
			}
			public void keyReleased(KeyEvent e){
				if (e.getKeyCode() == Constants.PLAYER_ONE_UP_KEY && playerOneHuman) paddle1.setSpeed(0.0);
				if (e.getKeyCode() == Constants.PLAYER_ONE_DOWN_KEY && playerOneHuman) paddle1.setSpeed(0.0);
				if (e.getKeyCode() == Constants.PLAYER_TWO_UP_KEY && playerTwoHuman) paddle2.setSpeed(0.0);
				if (e.getKeyCode() == Constants.PLAYER_TWO_DOWN_KEY && playerTwoHuman) paddle2.setSpeed(0.0);
			}
			public void keyTyped(KeyEvent e){
			}
		});
		scene.requestFocus();
		
		
		while (true) {
			try {
				if (!paused) {
					// player scoring
					boolean p1Scored = rightWall.isColliding(ball) == Direction.LEFT;
					boolean p2Scored = leftWall.isColliding(ball) == Direction.RIGHT;
					Direction dir1 = paddle2.isColliding(ball);
					Direction dir2 = paddle1.isColliding(ball);
					Direction dir3 = topWall.isColliding(ball);
					Direction dir4 = botWall.isColliding(ball);
					
					
					if ((p1Scored  && dir1 == Direction.NONE) || (p2Scored && dir2 == Direction.NONE)){
						pause(true);
						ball.setSpeed(BALL_SPEED_DEFAULT); // removes any increase in speed from the last game
						//increment the correct score
						if (p1Scored) {
							player1Score++;
							MainClass.setPlayer1Score(player1Score);
						} else if (p2Scored) {
							player2Score++;
							MainClass.setPlayer2Score(player2Score);
						}
	
						Thread.sleep(NEW_BALL_DELAY);
						resize();
						pause(false);
					}
					
					
					if (!playerOneHuman && Math.abs(ball.getX() - paddle1.getX()) < AI_SEARCH_DISTANCE) {
						double target = ball.getY() - paddle1.getHeight() / 2 + WALL_THICKNESS_DEFAULT/2;
						paddle1.setBoundedY(target, paddle1.getHeight(), this.getTop(), this.getBot());
					}
					if (!playerTwoHuman && Math.abs(ball.getX() - paddle2.getX()) < AI_SEARCH_DISTANCE) {
						double target = ball.getY() - paddle2.getHeight() / 2 + WALL_THICKNESS_DEFAULT/2;
						paddle2.setBoundedY(target, paddle2.getHeight(), this.getTop(), this.getBot());
					}
					
					if (dir1 != Direction.NONE){
						ball.flipDirection(dir1);
					} else if (dir2 != Direction.NONE){
						ball.flipDirection(dir2);
					} else if (dir3 != Direction.NONE){
						ball.flipDirection(dir3);
					} else if (dir4 != Direction.NONE){
						ball.flipDirection(dir4);
					}
			
					scene.repaint();
				}

				Thread.sleep(Constants.THREAD_DELAY);
			}
			catch (InterruptedException exception) {
				System.out.println("Game Thread Interrupted");
			}

		}
	}
	
	public void pause(boolean b){
		this.paused = b;
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
	
	public void end(){
		scene.remove(field);
		scene.remove(topWall);
		scene.remove(botWall);
		scene.remove(paddle1);
		scene.remove(paddle2);
		scene.remove(ball);
		ballThread.interrupt();
		paddle1Thread.interrupt();
		paddle2Thread.interrupt();
	}
	
	public void resize(){
		field.resize(scene.getWidth(), scene.getHeight());
		topWall.setX(0);
		topWall.setWidth(scene.getWidth());
		topWall.setY(0);
		topWall.setHeight(topWall.getHeight());
		botWall.setX(0);
		botWall.setWidth(scene.getWidth());
		botWall.setY(scene.getHeight() - botWall.getHeight());
		botWall.setHeight(botWall.getHeight());
		leftWall.setX(scene.getX());
		leftWall.setWidth(GOAL_WALL_THICKNESS);
		leftWall.setY(scene.getX() + topWall.getHeight());
		leftWall.setHeight(scene.getHeight() - topWall.getHeight() - botWall.getHeight());
		rightWall.setX(scene.getWidth() - GOAL_WALL_THICKNESS);
		rightWall.setWidth(GOAL_WALL_THICKNESS);
		rightWall.setY(scene.getX() + topWall.getHeight());
		rightWall.setHeight(scene.getHeight() - topWall.getHeight() - botWall.getHeight());
		
		ball.setTop(getTop() - 1); // allows for collision with wall
		ball.setBot(getBot() + 1); // allows for collision with wall
		ball.setLeft(getLeft() - 1); // allows for collision with boundary
		ball.setRight(getRight() + 1); // allows for collision with boundary
		paddle1.setTop(getTop());
		paddle1.setBot(getBot());
		paddle1.setLeft(getLeft() - paddle1.getWidth());
		paddle1.setRight(paddle1.getWidth());
		paddle2.setTop(getTop());
		paddle2.setBot(getBot());
		paddle2.setLeft(getRight());
		paddle2.setRight(getRight() + paddle2.getWidth());
		
		ball.setX(scene.getWidth() / 2);
		ball.setY(scene.getHeight() / 2);
		paddle1.setX(0);
		if (paddle1.getY() + paddle1.getHeight()> scene.getHeight() - botWall.getHeight()) paddle1.setY(scene.getHeight() - botWall.getHeight() - paddle1.getHeight());
		paddle2.setX(scene.getWidth() - paddle2.getWidth());
		if (paddle2.getY() + paddle2.getHeight()> scene.getHeight() - botWall.getHeight()) paddle2.setY(scene.getHeight() - botWall.getHeight() - paddle2.getHeight());
	}
	
	public double getTop() {
		return topWall.getHeight();
	}
	
	public double getBot() {
		return field.getHeight() - botWall.getHeight();
	}
	
	public double getLeft() {
		return GOAL_WALL_THICKNESS;
	}
	
	public double getRight() {
		return field.getWidth() - GOAL_WALL_THICKNESS;
	}
}
