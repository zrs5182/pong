import edu.truman.kczs.MainClass;

/**
 * Main class for the game of Pong
 * Player 1 controls are "Q" to move up and "A" to move down
 * Player 2 Controls are Up Arrow to move up and Down Arrow to move down
 * A new game must me made for any AI/Color/Difficulty changes to take effect
 * @author Kyler Carlson
 * @author Zach Schwartz
 *
 */
public class KCZS {


	/**
	 * Will pass control to MainClass
	 * @param args none expected
	 */
	public static void main(String[] args) {
		MainClass mainClass = new MainClass();
		mainClass.go();
	}
}
