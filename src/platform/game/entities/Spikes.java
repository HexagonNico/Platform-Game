package platform.game.entities;

import platform.framework.gamestates.GameStateManager;
import platform.framework.render.ModelManager;
import platform.game.utils.Direction;

public class Spikes extends StaticEntity {

	public Spikes(int posX, int posY, Direction facing) {
		super(posX, posY, null);
		switch(facing) {
		case DOWN:
			this.setModel(ModelManager.model(ModelManager.SPIKES_DOWN));
			break;
		case LEFT:
			this.setModel(ModelManager.model(ModelManager.SPIKES_LEFT));
			break;
		case RIGHT:
			this.setModel(ModelManager.model(ModelManager.SPIKES_RIGHT));
			break;
		case UP:
		default:
			this.setModel(ModelManager.model(ModelManager.SPIKES_UP));
			break;
		}
	}
	
	@Override
	public void onEntityCollision(Entity entity, GameStateManager gsm) {
		entity.setDead(true);
	}

}
