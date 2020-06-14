package platform.framework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import platform.game.Game;

public class GameLoop implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Game.getStateManager().tick();
	}

}
