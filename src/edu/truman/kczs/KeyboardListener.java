/**
 * 
 */
package edu.truman.kczs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Kyler
 *
 */
public class KeyboardListener extends KeyAdapter {
	private int upKey;
	private int downKey;
	private boolean upPressed = false;
	private boolean downPressed = false;
	
	public KeyboardListener(int upKey, int downKey){
		this.upKey = upKey;
		this.downKey = downKey;
	}
	
	public void keyPressed(KeyEvent e){
		System.out.println("test");
		if (e.getKeyCode() == upKey){
			upPressed = true;
			System.out.println("up pressed");
		}
		if (e.getKeyCode() == downKey){
			downPressed = true;
		}
	}
	
	public void keyReleased(KeyEvent e){
		if (e.getKeyCode() == upKey){
			upPressed = false;
		}
		if (e.getKeyCode() == downKey){
			downPressed = false;
		}
	}
	
	public boolean getUpPressed(){
		return upPressed;
	}
	
	public boolean getDownPressed(){
		return downPressed;
	}
}
