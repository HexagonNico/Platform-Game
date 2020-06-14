package platform.game;

import java.awt.EventQueue;

import javax.swing.Timer;

import platform.framework.DisplayManager;
import platform.framework.GameLoop;
import platform.framework.gamestates.GameStateManager;
import platform.framework.render.ModelManager;

public class Game {

	private static Timer timer;
	private static boolean running = false;
	
	private static GameStateManager stateManager;
	
	public static void main(String[] args) {
		System.out.println("[Main][Game]: Starting...");
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				ModelManager.init();
				DisplayManager.createDisplay();
				
				startGame();
				
				System.out.println("[Main][Game]: Started!");
			}
			
		});
	}

	/**Starts game<br>
	 * Called at the end of main
	 */
	private static void startGame() {
		stateManager = new GameStateManager();
		
		running = true;
		timer = new Timer(20, new GameLoop());
		timer.start();
	}
	
	/**Checks if the game is running*/
	public static boolean isRunning() {
		return running;
	}
	
	/**Gets game state manager*/
	public static GameStateManager getStateManager() {
		return stateManager;
	}
}
