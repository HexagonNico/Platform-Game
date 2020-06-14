package platform.game.entities;

import platform.framework.gamestates.GameStateManager;
import platform.framework.render.ModelManager;
import platform.game.states.LevelOneState;

public class ExitDoor extends StaticEntity {

	public ExitDoor(int posX, int posY) {
		super(posX, posY, ModelManager.model(ModelManager.EXIT_DOOR));
	}
	
	@Override
	public void onEntityCollision(Entity entity, GameStateManager gsm) {
		if(entity instanceof Player) {
			gsm.addState(new LevelOneState(gsm));
		}
	}

}
