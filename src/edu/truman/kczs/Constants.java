package edu.truman.kczs;

import java.awt.event.KeyEvent;

import java.awt.Color;

public abstract class Constants {
	public static final int THREAD_DELAY = 5;
	public static final boolean SPEED_INCREMENT_ENABLED = true;
	public static final double SPEED_INCREMENT = 0.05;
	public static final int PLAYER_ONE_UP_KEY = KeyEvent.VK_A;
	public static final int PLAYER_ONE_DOWN_KEY = KeyEvent.VK_Q;
	public static final int PLAYER_TWO_UP_KEY = KeyEvent.VK_DOWN;
	public static final int PLAYER_TWO_DOWN_KEY = KeyEvent.VK_UP;
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.BLACK;
	public static final Color DEFAULT_FOREGROUND_COLOR = Color.WHITE;
	public static final Color DEFAULT_PLAYER_ONE_COLOR = Color.RED;
	public static final Color DEFAULT_PLAYER_TWO_COLOR = Color.BLUE;
}
