/**
 * 
 */
package edu.truman.kczs;

/**
 * @author Kyler
 *
 */
public class RunnableGame implements Runnable {

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				
				Thread.sleep(Constants.THREAD_DELAY);
			}
			catch (InterruptedException exception) {
				System.out.println("Ball Thread Exception");
			}
		
		}
	}

}
