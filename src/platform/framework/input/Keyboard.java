package platform.framework.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import platform.game.Game;

public class Keyboard implements KeyListener {

	@Override
	public void keyPressed(KeyEvent key) {
		if(Game.isRunning()) {
			Game.getStateManager().keyPressed(key.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {
		if(Game.isRunning()) {
			Game.getStateManager().keyReleased(key.getKeyCode());
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
