package platform.game.entities;

import platform.framework.gamestates.GameStateManager;
import platform.framework.render.ModelManager;
import platform.game.states.LevelOneState;
import platform.game.utils.MathHelper;

public class Fruit extends Entity {

	private int score;
	
	public Fruit(int posX, int posY) {
		super(posX, posY, null);
		switch(MathHelper.randomInt(4)) {
		case 0:
			this.setModel(ModelManager.model(ModelManager.GRAPE));
			this.score = 100;
			break;
		case 1:
			this.setModel(ModelManager.model(ModelManager.MELON));
			this.score = 1000;
			break;
		case 2:
			this.setModel(ModelManager.model(ModelManager.STRAWBERRY));
			this.score = 500;
			break;
		case 3:
			this.setModel(ModelManager.model(ModelManager.APPLE));
			this.score = 200;
			break;
		}
	}

	@Override
	public void onEntityCollision(Entity entity, GameStateManager gsm) {
		if(entity instanceof Player) {
			LevelOneState.totalScore += this.score;
			this.setDead(true);
		}
	}
}
