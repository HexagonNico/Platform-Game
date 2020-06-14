package platform.framework.gamestates;

import java.awt.Graphics;
import java.util.Stack;

import platform.game.states.MenuState;

public class GameStateManager {

	private Stack<GameState> states;
	
	/**Creates the game state manager*/
	public GameStateManager() {
		this.states = new Stack<GameState>();
		this.states.add(new MenuState(this));
	}
	
	public void addState(GameState state) {
		this.states.add(state);
	}
	
	public void clearStack() {
		this.states.clear();
	}
	
	public GameState getActiveState() {
		return this.states.peek();
	}
	
	/**Calls tick() for the first state in the stack<br>
	 * Called in GameLoop
	 */
	public void tick() {
		this.states.peek().tick();
	}
	
	/**Calls render(Graphics) for the first state in the stack<br>
	 * Called in GameScreen after checking Game.isRunning()
	 */
	public void render(Graphics graphics) {
		this.states.peek().render(graphics);
	}
	
	/**Calls keyPressed(int) for the first state in the stack<br>
	 * Called in Keyboard after checking Game.isRunning()
	 * @param key - KeyEvent#getKeyCode()
	 */
	public void keyPressed(int key) {
		this.states.peek().keyPressed(key);
	}
	
	/**Calls keyReleased(int) for the first state in the stack<br>
	 * Called in Keyboard after checking Game.isRunning()
	 * @param key - KeyEvent#getKeyCode()
	 */
	public void keyReleased(int key) {
		this.states.peek().keyReleased(key);
	}
}
