package platform.framework.gamestates;

import java.awt.Graphics;

public abstract class GameState {

	protected GameStateManager gsm;
	
	/**Creates game state*/
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
		this.init();
	}
	
	public abstract void init();
	
	public abstract void tick();
	
	public abstract void render(Graphics graphics);
	
	public abstract void keyPressed(int key);
	
	public abstract void keyReleased(int key);
}
