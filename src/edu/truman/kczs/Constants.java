package edu.truman.kczs;

import java.awt.event.KeyEvent;

import java.awt.Color;

/**
 * Defines common constants the system uses
 * @author Kyler Carlson
 * @auhtor Zach Schwartz
 *
 */
public abstract class Constants {
	public static final int THREAD_DELAY = 5;
	public static final boolean SPEED_INCREMENT_ENABLED = true;
	public static final double SPEED_INCREMENT = 0.1; // amount to increase ball speed after each collision
	public static final int PLAYER_ONE_UP_KEY = KeyEvent.VK_A;
	public static final int PLAYER_ONE_DOWN_KEY = KeyEvent.VK_Q;
	public static final int PLAYER_TWO_UP_KEY = KeyEvent.VK_DOWN;
	public static final int PLAYER_TWO_DOWN_KEY = KeyEvent.VK_UP;
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.BLACK;
	public static final Color DEFAULT_FOREGROUND_COLOR = Color.WHITE;
	public static final Color DEFAULT_PLAYER_ONE_COLOR = Color.RED;
	public static final Color DEFAULT_PLAYER_TWO_COLOR = Color.BLUE;
	public static final double MAX_BEGINNER_AI_SPEED = 2.0;
	public static final double MAX_INTERMEDIATE_AI_SPEED = 5.0;
	public static final double MAX_EXPERT_AI_SPEED = Double.MAX_VALUE;

	public static final int WALL_THICKNESS_DEFAULT = 20;
	public static final int GOAL_WALL_THICKNESS = 20;
	public static final int PADDLE_HEIGHT_DEFAULT = 100;//150;
	public static final int PADDLE_DIFFUCULTY_CHANGE = 50;
	public static final Color BACKGROUND_DEFAULT = Color.black;
	public static final Color LINE_COLOR_DEFAULT = Color.gray;
	public static final Color WALL_COLOR_DEFAULT = Color.white;
	public static final Color BALL_COLOR_DEFAULT = Color.green;
	public static final double PADDLE_INIT_SPEED_DEFAULT = 0.0;
	public static final int NEW_BALL_DELAY = 2500; // time to pause thread for new ball

	public static final double BALL_SPEED_DEFAULT = 2.0; //will change
	public static final double BALL_DX_DEFAULT = 1.0;
	public static final double BALL_DY_DEFAULT = 0.5;
	public static final int AI_SEARCH_DISTANCE = 600; // range the AI can "see" the ball from

	public static final double PADDLE_SPEED = 2.0; // initial paddle speed before acceleration
	public static final double PADDLE_ACCEL = 3.0; // amount added to paddle speed each time it is held, will reset to 0 of released
	public static final double PADDLE_ACCEL_POWER = 0.3; // power the count of accel is raised to
	public static final double MIN_RANDOM_DY = 0.3; // minimum DY when it is randomly set
	public static final int NUMBER_OF_PLAYERS = 2;

	public static final int WIN_WIDTH = 900;
	public static final int WIN_HEIGHT = 760;
}
