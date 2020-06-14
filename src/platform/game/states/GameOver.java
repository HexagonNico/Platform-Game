package platform.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import platform.framework.gamestates.GameState;
import platform.framework.gamestates.GameStateManager;

public class GameOver extends GameState {

	public GameOver(GameStateManager gsm) {
		super(gsm);
		System.out.println("[GameStates][GameOver]: Creating game over state...");
	}

	@Override
	public void init() {}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("Arial", Font.PLAIN, 30));
		graphics.drawString("Game Over!", 200, 100);
		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("Arial", Font.PLAIN, 15));
		graphics.drawString("Press any button", 200, 150);
	}

	@Override
	public void keyPressed(int key) {}

	@Override
	public void keyReleased(int key) {
		this.gsm.clearStack();
		this.gsm.addState(new MenuState(gsm));
	}

}
