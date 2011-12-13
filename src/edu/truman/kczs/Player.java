package edu.truman.kczs;

import java.awt.Color;

/**
 * Defines a player
 * @author Kyler Carlson
 * @author Zach Schwartz
 */
public class Player {
	private boolean isHuman;
	private SkillLevel skillLevel;
	private Color color;
	private int paddleHeight = Constants.PADDLE_HEIGHT_DEFAULT;
	private int score = 0;
	private double maxTarget = 0.0;

	/**
	 * Makes a new player
	 * @param isHuman stores if the player is human or AI
	 * @param skillLevel the skill level of the player
	 * @param color color the player's paddle and score will use
	 */
	public Player(boolean isHuman, SkillLevel skillLevel, Color color){
		this.isHuman = isHuman;
		this.skillLevel = skillLevel;
		this.color = color;

		if (getSkillLevel() == SkillLevel.BEGINNER || !getHuman()) {
			paddleHeight += Constants.PADDLE_DIFFUCULTY_CHANGE;
		} else if (getSkillLevel() == SkillLevel.EXPERT) {
			paddleHeight -= Constants.PADDLE_DIFFUCULTY_CHANGE;
		}

		if (getSkillLevel() == SkillLevel.BEGINNER) {
			maxTarget = Constants.MAX_BEGINNER_AI_SPEED;
		} else if (getSkillLevel() == SkillLevel.INTERMEDIATE) {
			maxTarget = Constants.MAX_INTERMEDIATE_AI_SPEED;
		} else if (getSkillLevel() == SkillLevel.EXPERT) {
			maxTarget = Constants.MAX_EXPERT_AI_SPEED;
		}
	}

	/**
	 * Returns boolean if player is human
	 * @return if human player
	 */
	public boolean getHuman(){
		return isHuman;
	}

	/**
	 * Returns the skill level of the player
	 * @return the skill level of the player
	 */
	public SkillLevel getSkillLevel() {
		return skillLevel;
	}

	/**
	 * Returns the color of a player
	 * @return the color of a player
	 */
	public Color getColor(){
		return color;
	}

	/**
	 * Returns the height of a player's paddle as set in the constuctor according to skillLevel
	 * @return the height of a plyaer's paddle
	 */
	public int getPaddleHeight() {
		return paddleHeight;
	}

	/**
	 * Returns the player's score
	 * @return the player's score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets the player's score
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Gets the maximum translation distance of an AI player
	 * @return the maximum translation distance of an AI player
	 */
	public double getMaxTarget(){
		return maxTarget;
	}

}