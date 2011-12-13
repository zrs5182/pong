package edu.truman.kczs;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import edu.truman.kczs.Direction;
import edu.truman.kczs.Field;
import edu.truman.kczs.GoalWall;
import edu.truman.kczs.RunnableBall;
import edu.truman.kczs.RunnablePaddle;
import edu.truman.kczs.SceneComponent;
import edu.truman.kczs.Wall;

/**
 * Makes the thread for a runnable game that will draw the field, paddles, and ball as well as control the AI
 * @author Kyler Carlson
 * @author Zach Schwartz
 */
public class RunnableGame implements Runnable{
	private SceneComponent scene;
	private final Field field;
	private final GoalWall leftWall;
	private final GoalWall rightWall;
	private final Wall topWall;
	private final Wall botWall;
	private final RunnablePaddle paddle1;
	private final RunnablePaddle paddle2;
	private final RunnableBall ball;
	private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private ArrayList<RunnablePaddle> paddles = new ArrayList<RunnablePaddle>();
	private Player p1;
	private Player p2;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Thread> threads = new ArrayList<Thread>();
	private boolean paused = false;
	private int playerOneAccelUpCount = 0;
	private int playerOneAccelDownCount = 0;
	private int playerTwoAccelUpCount = 0;
	private int playerTwoAccelDownCount = 0;
	private boolean threadIsAlive = true;

	/**
	 * The constructor for a runnable game that will make all required objects and threads
	 * @param p1Hum boolean if player one is human
	 * @param p1Skill SkillLevel of player one
	 * @param p1Col Color of player one
	 * @param p2Hum boolean if player two is human
	 * @param p2Skill SkillLevel of player two
	 * @param p2Col Color of player two
	 */
	public RunnableGame(boolean p1Hum, SkillLevel p1Skill, Color p1Col, boolean p2Hum, SkillLevel p2Skill, Color p2Col){
		// make scene and add it to the frame
		scene = new SceneComponent();
		MainClass.addSceneComponent(scene);
		// make players and add them to arraylist
		p1 = new Player(p1Hum, p1Skill, p1Col);
		p2 = new Player(p2Hum, p2Skill, p2Col);
		players.add(p1);
		players.add(p2);
		// make field, paddles, ball and add to arraylist, then add them to scene, all widths, heights, x positions, and y positions are redone in resize()
		field = new Field(scene.getWidth(), scene.getHeight(), Constants.BACKGROUND_DEFAULT, Constants.LINE_COLOR_DEFAULT, Constants.WALL_THICKNESS_DEFAULT);
		leftWall = new GoalWall(0, 0 + Constants.WALL_THICKNESS_DEFAULT, Constants.GOAL_WALL_THICKNESS, scene.getHeight() - Constants.WALL_THICKNESS_DEFAULT*2);
		rightWall = new GoalWall(scene.getWidth() - Constants.GOAL_WALL_THICKNESS, Constants.WALL_THICKNESS_DEFAULT, Constants.GOAL_WALL_THICKNESS, scene.getHeight() - Constants.WALL_THICKNESS_DEFAULT*2);
		topWall = new Wall(0, 0, scene.getWidth() , Constants.WALL_THICKNESS_DEFAULT, Constants.WALL_COLOR_DEFAULT);
		botWall = new Wall(0, scene.getHeight() - Constants.WALL_THICKNESS_DEFAULT, scene.getWidth() , Constants.WALL_THICKNESS_DEFAULT, Constants.WALL_COLOR_DEFAULT);
		paddle1 = new RunnablePaddle(0,(scene.getHeight()-p1.getPaddleHeight()) / 2 , Constants.WALL_THICKNESS_DEFAULT, p1.getPaddleHeight(), p1.getColor(), Constants.PADDLE_INIT_SPEED_DEFAULT, 0.0, 1.0, this.getTop(), this.getBot(), getLeft(), Constants.WALL_THICKNESS_DEFAULT);
		paddle2 = new RunnablePaddle(scene.getWidth() - Constants.WALL_THICKNESS_DEFAULT,(scene.getHeight()-p2.getPaddleHeight()) / 2 , Constants.WALL_THICKNESS_DEFAULT, p2.getPaddleHeight(), p2.getColor(), Constants.PADDLE_INIT_SPEED_DEFAULT, 0.0, 1.0, this.getTop(), this.getBot(), this.getRight() - Constants.WALL_THICKNESS_DEFAULT, this.getRight());
		ball = new RunnableBall((scene.getWidth() - Constants.WALL_THICKNESS_DEFAULT)/2, (scene.getHeight() - Constants.WALL_THICKNESS_DEFAULT)/2, Constants.WALL_THICKNESS_DEFAULT, Constants.WALL_THICKNESS_DEFAULT, Constants.BALL_COLOR_DEFAULT, Constants.BALL_SPEED_DEFAULT, Constants.BALL_DX_DEFAULT, Constants.BALL_DY_DEFAULT, this.getTop(), this.getBot(), this.getLeft(), this.getRight());
		drawables.add(field);
		drawables.add(topWall);
		drawables.add(botWall);
		drawables.add(paddle1);
		drawables.add(paddle2);
		drawables.add(ball);
		for (Drawable d: drawables){
			scene.add(d);
		}
		// add paddles to paddle array
		paddles.add(paddle1);
		paddles.add(paddle2);
		// make threads and add them to arraylist, then start them
		threads.add(new Thread(ball));
		threads.add(new Thread(paddle1));
		threads.add(new Thread(paddle2));
		for (Thread t: threads){
			t.start();
		}
		// listens for window resize and calls resize()
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
	}

	/**
	 * Method called when a game thread starts
	 * Contains the main game loop
	 */
	public void run() {
		// rezero scores
		p1.setScore(0);
		p2.setScore(0);
		MainClass.setPlayerScores(p1.getScore(), p2.getScore());
		resize(); // allows the field to be drawn with the scene's width and height since they are initialized to 0
		// adds keyboard listener to control paddles
		scene.addKeyListener(new 
				KeyListener() {
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == Constants.PLAYER_ONE_UP_KEY && p1.getHuman()) {
					paddle1.setSpeed(Constants.PADDLE_SPEED + Math.pow(playerOneAccelUpCount, Constants.PADDLE_ACCEL_POWER) * Constants.PADDLE_ACCEL);
					playerOneAccelUpCount++;
				}
				if (e.getKeyCode() == Constants.PLAYER_ONE_DOWN_KEY && p1.getHuman()) {
					paddle1.setSpeed(-1 * (Constants.PADDLE_SPEED + Math.pow(playerOneAccelDownCount, Constants.PADDLE_ACCEL_POWER) * Constants.PADDLE_ACCEL));
					playerOneAccelDownCount++;
				}
				if (e.getKeyCode() == Constants.PLAYER_TWO_UP_KEY && p2.getHuman()){
					paddle2.setSpeed(Constants.PADDLE_SPEED + Math.pow(playerTwoAccelUpCount, Constants.PADDLE_ACCEL_POWER) * Constants.PADDLE_ACCEL);
					playerTwoAccelUpCount++;
				}
				if (e.getKeyCode() == Constants.PLAYER_TWO_DOWN_KEY && p2.getHuman()) {
					paddle2.setSpeed(-1 * (Constants.PADDLE_SPEED + Math.pow(playerTwoAccelDownCount, Constants.PADDLE_ACCEL_POWER) * Constants.PADDLE_ACCEL));
					playerTwoAccelDownCount++;
				}
			}
			public void keyReleased(KeyEvent e){
				if (e.getKeyCode() == Constants.PLAYER_ONE_UP_KEY && p1.getHuman()) {
					playerOneAccelUpCount = 0;
					paddle1.setSpeed(0.0);
				}
				if (e.getKeyCode() == Constants.PLAYER_ONE_DOWN_KEY && p1.getHuman()) {
					playerOneAccelDownCount = 0;
					paddle1.setSpeed(0.0);
				}
				if (e.getKeyCode() == Constants.PLAYER_TWO_UP_KEY && p2.getHuman()) {
					playerTwoAccelUpCount = 0;
					paddle2.setSpeed(0.0);
				}
				if (e.getKeyCode() == Constants.PLAYER_TWO_DOWN_KEY && p2.getHuman()) {
					playerTwoAccelDownCount = 0;
					paddle2.setSpeed(0.0);
				}
			}
			public void keyTyped(KeyEvent e){
			}
		});
		scene.requestFocus();

		// Main Game Loop
		while (threadIsAlive) {
			try {
				//Don't need to move things if paused
				if (!paused) {
					// player scoring
					boolean p1Scored = rightWall.isColliding(ball) == Direction.LEFT;
					boolean p2Scored = leftWall.isColliding(ball) == Direction.RIGHT;
					Direction dir1 = paddle2.isColliding(ball);
					Direction dir2 = paddle1.isColliding(ball);
					Direction dir3 = topWall.isColliding(ball);
					Direction dir4 = botWall.isColliding(ball);

					// if there is a score
					if ((p1Scored  && dir1 == Direction.NONE) || (p2Scored && dir2 == Direction.NONE)){
						pause(true);
						scene.repaint(); // updates field to show how close you were to hitting
						ball.setSpeed(Constants.BALL_SPEED_DEFAULT); // removes any increase in speed from the last game
						double randomDy = Math.random();
						if (randomDy < Constants.MIN_RANDOM_DY) randomDy = Constants.MIN_RANDOM_DY;
						ball.setDy(randomDy);
						//increment the correct score
						if (p1Scored) {
							p1.setScore(p1.getScore()+1);
						} else if (p2Scored) {
							p2.setScore(p2.getScore()+1);
						}
						MainClass.setPlayerScores(p1.getScore(), p2.getScore());
						Thread.sleep(Constants.NEW_BALL_DELAY);
						resize();
						pause(false);
					}

					//sets the AI's translate amounts
					for (int i=0; i < Constants.NUMBER_OF_PLAYERS; i++){
						if (!players.get(i).getHuman()) {
							if (Math.abs(ball.getX() - paddles.get(i).getX()) < Constants.AI_SEARCH_DISTANCE) {
								double target;
								if (players.get(i).getSkillLevel() == SkillLevel.EXPERT){
									target = (((ball.getX() - (paddles.get(i).getX() + paddles.get(i).getWidth()/2)) * ((-1 * ball.getDy()) / ball.getDx())) + ball.getY() - paddles.get(i).getHeight()/2) - paddles.get(i).getY();
								} else {
									target = ball.getY() - paddles.get(i).getY() - paddles.get(i).getHeight() / 2 + ball.getHeight() / 2;
								}
								if (Math.abs(target) > players.get(i).getMaxTarget()) target = players.get(i).getMaxTarget() * (Math.abs(target)/target);
								paddles.get(i).setSpeed(target);
							} else {
								paddles.get(i).setSpeed(0.0);
							}
						}
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

	/**
	 * Changes the paused state of the game
	 * @param b if the game is to be paused or not
	 */
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
			scene.requestFocus(); // return focus to scene for key listener
		}
	}

	/**
	 * Will allow the while loop under run() to end and thus end the thread
	 * Also removes drawable shapes and calls the end() methods of all other threads
	 */
	public void end(){
		for (Drawable d: drawables){
			scene.remove(d);
		}
		ball.end();
		paddle1.end();
		paddle2.end();
		threadIsAlive = false;
	}

	/**
	 * Allows the field to adjust its size to fit a window size
	 * Is called before before the game's main loop begins
	 * will recenter paddles and ball when window is resized
	 */
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
		leftWall.setWidth(Constants.GOAL_WALL_THICKNESS);
		leftWall.setY(scene.getX() + topWall.getHeight());
		leftWall.setHeight(scene.getHeight() - topWall.getHeight() - botWall.getHeight());
		rightWall.setX(scene.getWidth() - Constants.GOAL_WALL_THICKNESS);
		rightWall.setWidth(Constants.GOAL_WALL_THICKNESS);
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
		paddle1.setY((scene.getHeight() - botWall.getHeight() - paddle1.getHeight() - topWall.getHeight()) / 2);
		paddle2.setX(scene.getWidth() - paddle2.getWidth());
		paddle2.setY((scene.getHeight() - botWall.getHeight() - paddle2.getHeight() - topWall.getHeight()) / 2);
	}

	/**
	 * The maximum top legal position of the ball on the field
	 * @return maximum top legal position of the ball on the field
	 */
	public double getTop() {
		return topWall.getHeight();
	}

	/**
	 * The maximum bottom legal position of the ball on the field
	 * @return maximum bottom legal position of the ball on the field
	 */
	public double getBot() {
		return field.getHeight() - botWall.getHeight();
	}

	/**
	 * The maximum left legal position of the ball on the field
	 * @return maximum left legal position of the ball on the field
	 */
	public double getLeft() {
		return Constants.GOAL_WALL_THICKNESS;
	}

	/**
	 * The maximum right legal position of the ball on the field
	 * @return maximum right legal position of the ball on the field
	 */
	public double getRight() {
		return field.getWidth() - Constants.GOAL_WALL_THICKNESS;
	}	
}
